package io.java.collections.prelims.task;

import java.util.*;

public class TaskExecutor {

    public static void main(String[] args) {

        PhoneTask mikePhone = new PhoneTask("Mike", "9876543");
        PhoneTask paulPhone = new PhoneTask("Paul", "1234567");

        CodingTask databaseCode = new CodingTask("db");
        CodingTask interfaceCode = new CodingTask("gui");
        CodingTask logicCode = new CodingTask("logic");

        Collection<Task> phoneTasks = new ArrayList<>();
        Collection<Task> codingTasks = new ArrayList<>();

        Collection<Task> mondayTasks = new ArrayList<>();
        Collection<Task> tuesdayTasks = new ArrayList<>();

        Collections.addAll(phoneTasks, mikePhone, paulPhone);
        Collections.addAll(codingTasks, databaseCode, interfaceCode, logicCode);

        Collections.addAll(mondayTasks, logicCode, mikePhone);
        Collections.addAll(tuesdayTasks, databaseCode, interfaceCode, paulPhone);

        Set<Task> phoneAndMondayTasks = new TreeSet<>(mondayTasks);
        phoneAndMondayTasks.addAll(phoneTasks);

        System.out.println(phoneAndMondayTasks);

    }
}
