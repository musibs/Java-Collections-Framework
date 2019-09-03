package io.java.collections.queue;

import io.java.collections.prelims.task.*;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueExplorer {

    public static void main(String[] args) {

        Queue<String> myQueue = new ArrayBlockingQueue<>(2);
        /*myQueue.add("1");
        myQueue.add("2");
        myQueue.add("3");*/

        myQueue.offer("1");
        myQueue.offer("2");
        myQueue.offer("3");

        System.out.println(myQueue.remove());
        System.out.println(myQueue.remove());
        //System.out.println(myQueue.remove());
        //System.out.println(myQueue.element());
        System.out.println("Peek: "+myQueue.peek());
        System.out.println("Poll "+myQueue.poll());

        System.out.println(myQueue);


        PhoneTask mikePhone = new PhoneTask("Mike", "9876543");
        PhoneTask paulPhone = new PhoneTask("Paul", "1234567");

        CodingTask databaseCode = new CodingTask("db");
        CodingTask interfaceCode = new CodingTask("gui");
        CodingTask logicCode = new CodingTask("logic");

        PriorityTask mikePhonePriorityTask = new PriorityTask(Priority.MEDIUM, mikePhone);
        PriorityTask paulPhonePriorityTask = new PriorityTask(Priority.HIGH, paulPhone);
        PriorityTask databaseCodePriorityTask = new PriorityTask(Priority.MEDIUM, databaseCode);
        PriorityTask interfaceCodePriorityTask = new PriorityTask(Priority.LOW, interfaceCode);

        Queue<PriorityTask> priorityTaskQueue = new PriorityQueue<>(10, (task1, task2) -> task1.getPriority().compareTo(task2.getPriority()));

        priorityTaskQueue.add(mikePhonePriorityTask);
        priorityTaskQueue.add(paulPhonePriorityTask);

        System.out.println(priorityTaskQueue.poll());
    }


}
