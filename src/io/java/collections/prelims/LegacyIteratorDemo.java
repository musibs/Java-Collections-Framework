package io.java.collections.prelims;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LegacyIteratorDemo {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Somnath", "Musib", "John", "Doe", "Disha", "David");

        // This was before Java 1.5
        for(Iterator itr=names.iterator(); itr.hasNext(); ){
            System.out.println(itr.next());
        }

    }
}
