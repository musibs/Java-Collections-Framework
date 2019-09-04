package com.codefountain.utils;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author musib on 4/09/2019
 * @project java-collections
 */
public abstract class AbstractQueue<E> extends AbstractCollection<E> implements Queue<E>{

    protected AbstractQueue(){}

    @Override
    public boolean add(E e) {
        if(offer(e)){
            return true;
        }
        else{
            throw new IllegalStateException("Queue full");
        }
    }

    @Override
    public E remove() {
        E e = poll();
        if(Objects.nonNull(e)){
            return e;
        }
        else{
            throw new NoSuchElementException("Queue empty");
        }
    }

    @Override
    public E element() {
        E e = peek();
        if(Objects.nonNull(e)){
            return e;
        }
        else{
            throw new NoSuchElementException("Queue empty");
        }
    }

    @Override
    public void clear() {
        while(Objects.nonNull(poll()));
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c);
        if(c == this){
            throw new IllegalArgumentException();
        }

        boolean modified = false;
        Iterator<E> iterator = iterator();
        while(iterator.hasNext()){
            add(iterator.next());
            modified = true;
        }
        return modified;
    }
}
