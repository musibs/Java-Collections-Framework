package io.java.collections.prelims;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Counter implements Iterable<Integer>{

    private int count;

    public Counter(int count){
        this.count = count;
    }


    @Override
    public void forEach(Consumer<? super Integer> action) {
        action.accept(count);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        throw new UnsupportedOperationException("spliterator");
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private int i = 0;
            @Override
            public boolean hasNext() {
                return i<count;
            }

            @Override
            public Integer next() {
                ++i;
                return i;
            }
        };
    }
}
