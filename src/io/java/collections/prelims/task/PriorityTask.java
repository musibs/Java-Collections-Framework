package io.java.collections.prelims.task;

public class PriorityTask implements Comparable<PriorityTask> {

    private final Priority priority;
    private final Task task;

    protected PriorityTask(Priority priority, Task task){
        this.priority = priority;
        this.task = task;
    }

    @Override
    public int compareTo(PriorityTask priorityTask) {
        int result = priority.compareTo(priorityTask.priority);
        return result !=0 ? result : task.compareTo(priorityTask.task);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PriorityTask){
            PriorityTask otherTask = (PriorityTask) obj;
            return priority.equals(otherTask.priority) && task.equals(otherTask.task);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

    @Override
    public String toString() {
        return task +":"+priority;
    }
}
