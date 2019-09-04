package io.java.collections.queue;

import io.java.collections.prelims.task.CodingTask;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;

public class DelayQueueExplorer {

    public static void main(String[] args) {


        DelayQueue<DelayedTask> reminderQueue = new DelayQueue<>();
        //ArrayBlockingQueue
        reminderQueue.offer(new DelayedTask(1, new CodingTask("db")));
        reminderQueue.offer(new DelayedTask(2, new CodingTask("gui")));

        Set<DelayedTask> remainingTasks = new HashSet<DelayedTask>();
        remainingTasks.addAll(reminderQueue);

        Set<DelayedTask> remainingActualTasks = new HashSet<DelayedTask>();
        reminderQueue.drainTo(remainingActualTasks);

        System.out.println("Remaining all tasks "+remainingTasks);
        System.out.println("Remaining actual tasks "+remainingActualTasks);



        DelayedTask task1 = reminderQueue.poll();
        if(Objects.nonNull(task1)){
            System.out.println(task1);
        }
        else{
            System.out.println("No task available");
        }
    }
}
