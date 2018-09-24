package com.zoulshell.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupTest {

    private List<Person> list = Arrays.asList(
            new Person("张三", 18, "male"),
            new Person("李四", 20, "female"),
            new Person("John", 23, "male"),
            new Person("Jack", 29, "male"),
            new Person("Rose", 18, "female"));

    @Test
    public void groupingBy() {
        Map<String, List<Person>> collect1 = list.stream().collect(Collectors.groupingBy(Person::getName));
        System.out.println(collect1);
        Map<Integer, List<Person>> collect2 = list.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println(collect2);
        Map<String, List<Person>> collect3 = list.stream().collect(Collectors.groupingBy(Person::getGender));
        System.out.println(collect3);
        Map<Integer, Long> countMap = list.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println(countMap);
        Map<String, Double> averageMap = list.stream().collect(Collectors.groupingBy(Person::getGender, Collectors.averagingInt(Person::getAge)));
        System.out.println(averageMap);
    }

    @Test
    public void partitioningBy() {
        Map<Boolean, List<Person>> collect = list.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 20));
        System.out.println(collect);
        System.out.println(collect.get(true));
    }
}
