package io.java.collections.Set;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.TreeSet;

public class NavigableSetDemo {

    public static void main(String[] args) {


        NavigableSet<String> stringSet = new TreeSet<>();
        Collections.addAll(stringSet, "abc", "cde", "x-ray", "zed");
        String last = stringSet.floor("x-ray");
        System.out.println(last);
        String secondToLast = stringSet.lower(last);
        System.out.println(secondToLast);
        String thirdToLast = stringSet.lower(secondToLast);
        System.out.println(thirdToLast);

        NavigableSet<String> headSet = stringSet.headSet(last, true);
        NavigableSet<String> reverseHashSet = headSet.descendingSet();
        System.out.println("Headset: "+headSet);
        System.out.println("Reverse Set: "+reverseHashSet);
    }
}
