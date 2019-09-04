package com.codefountain.utils;

import io.java.collections.prelims.task.Priority;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.function.Consumer;

/**
 * @author musib on 4/09/2019
 * @project java-collections
 */
public class PriorityQueue<E> extends AbstractQueue<E> implements java.io.Serializable {

    private static final long serialVersionUID = -7720805057305804111L;

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    transient Object[] queue;

    int size;

    private final Comparator<? super E> comparator;

    transient int modCount;

    public PriorityQueue(){
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public PriorityQueue(int initialCapacity){
        this(initialCapacity, null);
    }

    public PriorityQueue(Comparator<? super E> comparator){
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public PriorityQueue(int initialCapacity, Comparator<? super E> comparator){
        if(initialCapacity < 1){
            throw new IllegalArgumentException();
        }
        this.queue = new Object[initialCapacity];
        this.comparator = comparator;
    }

    public PriorityQueue(Collection<? extends  E> c){
        if(c instanceof SortedSet<?>){
            throw new UnsupportedOperationException();
        }
        else if(c instanceof PriorityQueue<?>){
            PriorityQueue<? extends E> pq = (PriorityQueue<? extends  E>) c;
            this.comparator = (Comparator<? super E>) pq.comparator();
            initFromPriorityQueue();
        }
        else {
            this.comparator = null;
            initFromCollection(c);
        }
    }

    public PriorityQueue(PriorityQueue<? extends E> c){
        this.comparator = (Comparator<? super E>) c.comparator();
        initFromPriorityQueue(c);
    }

    public PriorityQueue(SortedSet<? extends E> c){
        throw new UnsupportedOperationException();
    }

    private static Object[] ensureNonEmpty(Object[] es){
        return es.length > 0 ? es : new Object[1];
    }

    private void initFromPriorityQueue(PriorityQueue<? extends E> c){
        if(c.getClass() == PriorityQueue.class){
            this.queue = ensureNonEmpty(c.toArray());
            this.size = c.size();
        }
        else{
            initFromCollection(c);
        }
    }

    private void initElementsFromCollection(Collection<? extends E> c){
        Object[] es = c.toArray();
        int length = es.length;

        if(es.getClass() != Object[].class){
            es = Arrays.copyOf(es, length, Object[].class);
        }

        if(es.length == 1 || this.comparator != null){
            for(Object e : es){
                if(Objects.isNull(e)) {
                    throw new NullPointerException();
                }
            }
        }

        this.queue = ensureNonEmpty(es);
        this.size = length;
    }

    private void initFromCollection(Collection<? extends E> c){
        initElementsFromCollection(c);
        heapify();
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void grow(int minCapacity) {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E peek() {
        return (E) queue[0];
    }

    private int indexOf(Object o){
        if(Objects.nonNull(o)){
            final Object[] es = queue;
            for(int i=0, n=size; i<n; i++){
                if(o.equals(es[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean remove(Object o) {
        int i = indexOf(o);
        if( i== -1){
            return false;
        }
        else{
            removeAt(i);
            return true;
        }
    }

    private void removeAt(int i){

    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(queue, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        final int size = this.size;
        if(a.length < size){
            return (T[]) Arrays.copyOf(queue, size, a.getClass());
        }

        System.arraycopy(queue, 0, a, 0, size);
        if(a.length > size){
            a[size] = null;
        }
        return a;
    }

    private final class Itr implements Iterator<E>{


        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {

        }
    }
}
