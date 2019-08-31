package io.java.collections.prelims.task;

import java.util.NavigableSet;
import java.util.TreeSet;

public class NavigableSetExplorer {

    public static void main(String[] args) {


        PhoneTask mikePhone = new PhoneTask("Mike", "9876543");
        PhoneTask paulPhone = new PhoneTask("Paul", "1234567");

        CodingTask databaseCode = new CodingTask("db");
        CodingTask interfaceCode = new CodingTask("gui");
        CodingTask logicCode = new CodingTask("logic");

        NavigableSet<PriorityTask> priorityTasks = new TreeSet<>();
        PriorityTask mikePhonePriorityTask = new PriorityTask(Priority.MEDIUM, mikePhone);
        PriorityTask paulPhonePriorityTask = new PriorityTask(Priority.HIGH, paulPhone);
        PriorityTask databaseCodePriorityTask = new PriorityTask(Priority.MEDIUM, databaseCode);
        PriorityTask interfaceCodePriorityTask = new PriorityTask(Priority.LOW, interfaceCode);


        priorityTasks.add(mikePhonePriorityTask);
        priorityTasks.add(paulPhonePriorityTask);
        priorityTasks.add(databaseCodePriorityTask);
        priorityTasks.add(interfaceCodePriorityTask);

        System.out.println(priorityTasks);
        System.out.println("First element: "+priorityTasks.first());
        System.out.println("Last Element: "+priorityTasks.last());

        System.out.println("Subset: "+priorityTasks.subSet(mikePhonePriorityTask, interfaceCodePriorityTask));
        System.out.println("Headset: "+priorityTasks.headSet(interfaceCodePriorityTask));
        System.out.println("Tailset: "+priorityTasks.tailSet(paulPhonePriorityTask));

        PriorityTask nextTask = priorityTasks.pollFirst();
        System.out.println("First Task: "+nextTask);
        System.out.println("Remaining Tasks: "+priorityTasks);

        PriorityTask lastTask = priorityTasks.pollLast();
        System.out.println("Last Task: "+lastTask );
        System.out.println("Remaining Tasks: "+priorityTasks);

    }
}
