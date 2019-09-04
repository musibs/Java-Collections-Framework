package io.java.collections.queue;

import io.java.collections.prelims.task.Priority;
import io.java.collections.prelims.task.PriorityTask;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

public class StoppableTaskQueue {

    private final int MAXIMUM_PENDING_OFFERS = Integer.MAX_VALUE;
    private final BlockingQueue<PriorityTask> taskQueue = new PriorityBlockingQueue<>();
    private volatile boolean isStopped = false;
    private final Semaphore semaphore = new Semaphore(MAXIMUM_PENDING_OFFERS);

    public boolean addTask(PriorityTask newTask){
        synchronized (this){
            if(isStopped){
                return false;
            }
        }

        if(!semaphore.tryAcquire()){
            throw new Error("Too many threads");
        }

        try{
            return taskQueue.offer(newTask);
        }
        finally {
            semaphore.release();
        }
    }

    public PriorityTask getTask(){
        return taskQueue.poll();
    }

    public Collection<PriorityTask> shutdown(){
        synchronized (this){
            isStopped = true;
        }

        semaphore.acquireUninterruptibly(MAXIMUM_PENDING_OFFERS);
        Set<PriorityTask> remainingTasks = new HashSet<>();
        taskQueue.drainTo(remainingTasks);
        return remainingTasks;
    }
}
