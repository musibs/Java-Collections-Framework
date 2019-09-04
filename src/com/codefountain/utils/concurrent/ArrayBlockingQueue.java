package com.codefountain.utils.concurrent;

import com.codefountain.utils.AbstractQueue;
import com.codefountain.utils.Collection;
import com.codefountain.utils.Iterator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author musib on 5/09/2019
 * @project java-collections
 */
public abstract class ArrayBlockingQueue<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {

    private static final long serialVersionUID = -817911632652898426L;

    final Object[] items;

    private int takeIndex;
    private int putIndex;
    private int count;

    final ReentrantLock lock;

    private final Condition notFull;

    private final Condition notEmpty;

    final E itemAt(int index){
        return (E) items[index];
    }

    static <E> E itemAt(Object[] items, int index){
        return (E) items[index];
    }

    private void enqueue(E e){
        final Object[] items = this.items;
        items[putIndex] = e;
        if(++putIndex == items.length){
            putIndex = 0;
        }
        count++;
        notEmpty.signal();
    }

    private E dequeue(){
        final Object[] items = this.items;
        E e = (E) items[takeIndex];
        items[takeIndex] = null;
        if(++takeIndex == items.length){
            takeIndex = 0;
        }
        count--;
        notFull.signal();
        return e;
    }

    void removeAt(final int removeIndex){


        final Object[] items = this.items;
        // Remove from the head

        if(removeIndex == takeIndex){
            items[takeIndex] = null;
            if(++takeIndex == items.length){
                takeIndex=0;
            }
            count--;
        }
        else {
            // Interior Remove

            for(int i=removeIndex, putIndex = this.putIndex;;){
                int pred=i;
                if(++i == items.length){
                    i=0;
                }
                if(i==putIndex){
                    items[pred] = null;
                    this.putIndex=pred;
                    break;
                }
                items[pred] = items[i];
            }
            count--;
        }
        notFull.signal();
    }


    public ArrayBlockingQueue(int capacity){
        this(capacity, false);
    }

    public ArrayBlockingQueue(int capacity, boolean fair){
        items = new Object[capacity];
        lock = new ReentrantLock(fair);
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public ArrayBlockingQueue(int capacity, boolean fair, Collection<? extends E> c){
        this(capacity, fair);
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{

            final Object[] items = this.items;
            int i = 0;
            Iterator<E> iterator = (Iterator<E>) c.iterator();
            try{
                while(iterator.hasNext()){
                    items[i++] = iterator.next();
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                throw new IllegalArgumentException();
            }
            count = i;
            putIndex = (i == capacity) ? 0 : i;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public boolean add(E e) {
        return super.add(e);
    }

    @Override
    public boolean offer(E e) {
        Objects.requireNonNull(e);
        lock.lock();
        try{
            if(count == items.length){
                return false;
            }
            enqueue(e);
            return true;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void put(E element) throws InterruptedException{
        Objects.requireNonNull(element);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try{
            while(count == items.length){
                notFull.await();
            }
            enqueue(element);
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E element, long timeout, TimeUnit unit) throws InterruptedException {
        Objects.requireNonNull(element);
        final ReentrantLock lock = this.lock;
        long nanos = unit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while(count == items.length){
                if(nanos <= 0L){
                    return false;
                }
                nanos = notFull.awaitNanos(nanos);
            }
            enqueue(element);
            return true;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            return (count == 0) ? null : dequeue();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try{
            while(count == 0){
                notEmpty.await();
            }
            return dequeue();
        }
        finally {
            lock.unlock();
        }
    }


    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        long nanos = unit.toNanos(timeout);
        try{
            while(count == 0){
                if(nanos <= 0L){
                    return null;
                }
                nanos = notEmpty.awaitNanos(nanos);
            }
            return dequeue();
        }
        finally {
            lock.lockInterruptibly();
        }
    }

    @Override
    public E peek() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            return itemAt(takeIndex);
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            return count;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public int remainingCapacity() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            return items.length - count;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(Object o) {
        if(Objects.isNull(o)){
            return false;
        }
        lock.lock();
        try{
            if(count > 0){
                final Object[] items = this.items;
                for(int i=takeIndex, end=putIndex, to=(i<end) ? end : items.length; ;i=0, to=end){
                    for(; i<to; i++){
                        if(o.equals(items[i])){
                            removeAt(i);
                            return true;
                        }
                    }
                    if(to == end){
                        break;
                    }
                }
            }
            return false;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public boolean contains(Object o) {
        {
            if(Objects.isNull(o)){
                return false;
            }
            lock.lock();
            try{
                if(count > 0){
                    final Object[] items = this.items;
                    for(int i=takeIndex, end=putIndex, to=(i<end) ? end : items.length; ;i=0, to=end){
                        for(; i<to; i++){
                            if(o.equals(items[i])){
                                return true;
                            }
                        }
                        if(to == end){
                            break;
                        }
                    }
                }
                return false;
            }
            finally {
                lock.unlock();
            }
        }
    }

    @Override
    public Object[] toArray() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try{
            final Object[] items = this.items;
            final int end = takeIndex + count;
            final Object[] a = Arrays.copyOfRange(items, takeIndex, end);
            if(end != putIndex){
                System.arraycopy(items, 0, a, items.length-takeIndex, putIndex);
            }
            return a;
        }
        finally {
            lock.unlock();
        }
    }
}