package io.java.collections.prelims;

public class CounterMain {

    public static void main(String[] args) {

        for(int counter : new Counter(3)){
            System.out.println(counter);
        }
    }
}
