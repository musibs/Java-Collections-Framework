package com.codefountain.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author musib on 4/09/2019
 * @project java-collections
 */
public abstract class AbstractCollection<E> implements Collection<E> {

    protected AbstractCollection(){}


    public abstract Iterator<E> iterator();
    public abstract int size();

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;


    @Override
    public boolean isEmpty(){
        return size() == 0;
    }

    @Override
    public boolean contains(Object o){
        Iterator<E> iterator = iterator();
        if(Objects.isNull(o)){
            while(iterator.hasNext()){
                if(Objects.isNull(iterator.next())){
                    return true;
                }
            }
        }
        else{
            while(iterator.hasNext()){
                if(Objects.equals(o, iterator.next())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object[] toArray(){
        Object[] r = new Object[size()];
        Iterator<E> iterator = iterator();

        for(int i=0; i<r.length; i++){
            if(!iterator.hasNext()){
                return Arrays.copyOf(r, i);
            }
            r[i] = iterator.next();
        }

        return iterator.hasNext() ? finishToArray(r, iterator) : r;
    }


    private static <T> T[] finishToArray(T[] r, Iterator<?> iterator){

        int i = r.length;
        while(iterator.hasNext()){
            int cap = r.length;
            if(i== cap){
                int newCap = cap + (cap >> 1) + 1;

                if(newCap - MAX_ARRAY_SIZE > 0){
                    newCap = hugeCapacity(cap + 1);
                }

                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T) iterator.next();
        }

        // Trim if over allocated
        return (i== r.length) ? r : Arrays.copyOf(r,i);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) { // Overflow
            throw new OutOfMemoryError("Required array size is too large");
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    // Modification Operations

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("add");
    }

    @Override
    public boolean remove(Object o) {
        Iterator<E> iterator = iterator();
        if(Objects.isNull(o)){
            while(iterator.hasNext()){
                if(iterator.next() == null){
                    iterator.remove();
                    return true;
                }
            }
        }
        else{
            while(iterator.hasNext()){
                if(o.equals(iterator.next())){
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    // Bulk Operations


    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()){
            if(!contains(iterator.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator iterator = c.iterator();
        while(iterator.hasNext()){
            add((E)iterator.next());
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator iterator = c.iterator();
        while(iterator.hasNext()){
            iterator.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator iterator = c.iterator();
        while(iterator.hasNext()){
            if(!contains(iterator.next())){
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        Iterator iterator = iterator();
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
    }

    @Override
    public String toString() {
        Iterator<E> iterator = iterator();
        if(!iterator.hasNext()){
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for(;;){
            E e = iterator.next();
            sb.append(e == this ? " (this collection) " : e);
            if(!iterator.hasNext()){
                return sb.append(" ]").toString();
            }
            sb.append(", ").append(" ");
        }
    }
}
