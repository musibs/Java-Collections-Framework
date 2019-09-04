package com.codefountain.utils;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Interfaces implementing this interface is target of
 * enhanced for each loop
 * @param <T>
 */
public interface Iterable<T>  {


    Iterator<T> iterator();


    default void forEach(Consumer<? super T> action){
        Objects.requireNonNull(action);
        while(iterator().hasNext()){
            action.accept(iterator().next());
        }
    }


}
