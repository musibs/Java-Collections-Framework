package io.java.custom.collections.core;

public class SynchronizedArrayStack implements Stack {

    private final Stack stack;

    public SynchronizedArrayStack(Stack stack){
        this.stack = stack;
    }
    @Override
    public synchronized boolean push(Object element) {
        return stack.push(element);
    }

    @Override
    public synchronized Object pop() {
        return stack.pop();
    }

    @Override
    public synchronized boolean isEmpty() {
        return stack.isEmpty();
    }
}
