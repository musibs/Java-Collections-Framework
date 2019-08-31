package io.java.custom.collections.core;

public class ArrayStack implements Stack<Integer>{

    private final int[] ARRAY = new int[10];
    private int top = -1;


    @Override
    public boolean push(Integer element) {

        if(top != ARRAY.length-1){
            top++;
            ARRAY[top] = element;
            return true;
        }
        else{
            throw new IllegalArgumentException("Stack Overflow");
        }

    }

    @Override
    public Integer pop() {
        if(top == -1){
            throw new IllegalArgumentException("Stack Underflow");
        }
        else{
            int element = ARRAY[top];
            top--;
            return element;
        }
    }

    @Override
    public boolean isEmpty() {
        return (top == -1);
    }
}
