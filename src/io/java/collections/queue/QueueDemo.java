package io.java.collections.queue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueDemo {

    public static void main(String[] args) {


        Queue<String> myQueue = new ArrayBlockingQueue<>(2);

        System.out.println(" ************ Adding Element in Queue ******************");
        myQueue.add("Somnath");
        myQueue.offer("Musib");

        // myQueue.add("Shiva"); --> Throws Exception when Queue is full, can't add
        if(myQueue.offer("Lord")){
            System.out.println("Added");
        }
        else{
            System.out.println("Failed, queue full");
        }


        System.out.println(" ****************** Viewing Elements from Queue *************");

        myQueue.remove();
        myQueue.remove();
        // System.out.println("Element "+myQueue.element());
        System.out.println("Peek: "+myQueue.peek());

        System.out.println(" ****************** Removing Elements ***********************");
        System.out.println(myQueue.poll());
        // System.out.println(myQueue.remove()); --> Throws exception




    }
}
