package com.codefountain.utils;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * An Iterator to iterate over the elements of a collection
 * @param <E>
 */
public interface Iterator<E> {

    /**
     * Returns true if there exists elements in the collection
     *
     * @return
     */
    boolean hasNext();

    /**
     * Rturns next element from the collection
     * @return
     */
    E next();

    /**
     * Removes an element
     */
    default void remove(){
        throw new UnsupportedOperationException("remove");
    }

    /**
     * Performs the specified action on each element of the collection
     * until all elements are processed or the action throws exception
     * @param action
     */
    default void forEachRemaining(Consumer<? super E> action){
        Objects.requireNonNull(action);
        while (hasNext()){
            action.accept(next());
        }
    }

}
