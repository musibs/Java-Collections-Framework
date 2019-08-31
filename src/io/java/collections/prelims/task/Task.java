package io.java.collections.prelims.task;

public abstract class Task implements Comparable<Task>{

    protected Task(){

    }

    @Override
    public boolean equals(Object task) {
        if(task instanceof Task){
            return toString().equals(task.toString());
        }
        return false;
    }

    @Override
    public int compareTo(Task task) {
        return toString().compareTo(task.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public abstract String toString();
}
