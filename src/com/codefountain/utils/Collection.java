package com.codefountain.utils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/**
 * @author musib on 4/09/2019
 * @project java-collections
 */
public interface Collection<E> extends Iterable<E> {


    // Query Operations

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    default <T> T[] toArray(IntFunction<T[]> generator){
        return toArray(generator.apply(0));
    }


    // Modification Operations

    boolean add(E e);

    boolean remove(Object o);

    // Bulk Operations

    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends E> c);

    boolean removeAll(Collection<?> c);

    default boolean removeIf(Predicate<? super E> filter){
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> iterator = iterator();
        while(iterator.hasNext()){
            if(filter.test(iterator.next())){
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }

    boolean retainAll(Collection<?> c);

    void clear();


    // Comparision and Hashing

    boolean equals(Object o);

    int hashCode();

}
