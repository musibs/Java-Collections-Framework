package io.java.custom.collections.core;

import java.util.Objects;
import java.util.function.Consumer;

public interface Iterator<E> {

    boolean hasNext();

    E next();

    default boolean remove(){
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(Consumer<? super E> action){
        Objects.nonNull(action);
        while(hasNext()){
            action.accept(next());
        }

    }
}
