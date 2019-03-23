package com.edwardsong.examples.java.lambdas;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Streams {

    @Test
    public void findMaxValue() {
        List<Integer> intList = new ArrayList<>();
        intList.add(0);intList.add(2);intList.add(3);
        assertTrue(intList.stream().mapToInt(s -> s.intValue()).max().orElse(0) == 3);
    }


    @Test
    public void filter(){
        List<Integer> intList = new ArrayList<>();
        intList.add(0);intList.add(2);intList.add(3);

        List<Integer> filteredToZero = intList.stream().filter(s -> s.intValue() != 0).collect(Collectors.toList());
        assertTrue(filteredToZero.size() == 1);
    }

    @Test
    public void partitionByProperty()
    {
        List<Person> personList = Arrays.asList(new Person("Edward", "Song", 1),new Person("Lisa", "Song", 2), new Person("Nathaniel", "Song", 1),new Person("Edward", "Song", 1));
        Map<String, List<Person>> lastNamePersonMap = personList
                .stream()
                .collect(Collectors.groupingBy(Person::getLastName,
                      Collectors.toList()
                ));

        lastNamePersonMap.entrySet().stream().forEach(s -> {
            List<String> values = s.getValue().stream()
                    .collect(Collectors.toMap(p -> p.getFirstName(), p -> p.getFirstName(), (first, second) -> first))
                    .values()
                    .stream().collect(Collectors.toList());
            assertTrue(s.getKey().equals("Song"));
            assertTrue(values.size() == 3);
        });
    }
}
