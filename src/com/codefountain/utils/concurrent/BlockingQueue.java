package com.codefountain.utils.concurrent;

import com.codefountain.utils.Collection;
import com.codefountain.utils.Queue;

import java.util.concurrent.TimeUnit;

/**
 * @author musib on 4/09/2019
 * @project java-collections
 */
public interface BlockingQueue<E> extends Queue<E> {

    boolean add(E element);

    boolean offer(E element);

    boolean offer(E element, long timeout, TimeUnit unit) throws InterruptedException;

    void put(E element) throws InterruptedException;

    E take() throws InterruptedException;

    E poll(long timeout, TimeUnit unit) throws InterruptedException;

    int remainingCapacity();

    boolean remove(Object o);

    boolean contains(Object o);

    boolean drainTo(Collection<? super E> c);

    boolean drainTo(Collection<? super E> c, int maxElements);
}
