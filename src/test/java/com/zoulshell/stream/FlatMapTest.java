package com.zoulshell.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTest {
    @Test
    public void flatMap1() {
        List<String> list = Arrays.asList("hello world", "hello welcome", "welcome world");
        // List<String[]> collect = list.stream().map(e -> e.split(" ")).collect(Collectors.toList());
        list.stream()
                .map(e -> e.split(" "))
                .flatMap(Stream::of).distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    public void flatMap2() {
        List<String> list1 = Arrays.asList("hello", "hi", "你好");
        List<String> list2 = Arrays.asList("张三", "李四", "王五", "John", "Jack");
        list1.stream().flatMap(e1 -> list2.stream().map(e2 -> e1 + "::" + e2))
                .collect(Collectors.toList()).forEach(System.out::println);
    }
}
