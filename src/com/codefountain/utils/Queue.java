package com.codefountain.utils;

/**
 * @author musib on 4/09/2019
 * @project java-collections
 */
public interface Queue<E> extends Collection<E> {

    /**
     * Adds an element into the queue.
     * @return true, if added successfully
     * @throws Exception if unable to add due to capacity
     */
    boolean add(E e);

    /**
     * Adds and element into the queue
     * @param e
     * @return true, if added successfully. False otherwise
     */

    boolean offer(E e);

    /**
     * Returns the element at the head of the queue
     * @return
     * @throws Exception, if queue is empty
     */
    E element();

    /**
     * Returns the element at the head of the queue
     * Retrns null if the queue is empty
     * @return
     */
    E peek();

    /**
     * Removes the element from the head of the queue
     * Throws exception of there are no element in the head
     * @return
     */
    E remove();

    /**
     * Returns the element from the head of the queue
     * Returns null if queue is empty
     * @return
     */
    E poll();
}
