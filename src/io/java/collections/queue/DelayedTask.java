package io.java.collections.queue;

import io.java.collections.prelims.task.Task;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed {

    private static final long MILLISECONDS_IN_A_DAY = 60*60*24*1000;
    private final Task task;
    private final long endTime;


    public DelayedTask(int daysDelay, Task task){
        endTime = System.currentTimeMillis() + daysDelay * MILLISECONDS_IN_A_DAY;
        this.task = task;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remainingTime = System.currentTimeMillis() - endTime;
        return unit.convert(remainingTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayedObject) {
        long thisDelay = getDelay(TimeUnit.MILLISECONDS);
        long otherDelay = delayedObject.getDelay(TimeUnit.MILLISECONDS);
        return (thisDelay < otherDelay) ? -1 : (thisDelay > otherDelay) ? 1 : 0;
    }

    public Task getTask() {
        return task;
    }

    public long getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "DelayedTask{" +
                "task=" + task +
                ", endTime=" + endTime +
                '}';
    }
}
