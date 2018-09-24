package com.zoulshell.stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamTest {

    private static List<String> UUIDs = Stream.generate(UUID::randomUUID)
            .limit(5000000L).map(UUID::toString).collect(Collectors.toList());

    @Test
    public void performanceTest() {
        stream();
        parallelStream();
    }

    private void stream() {
        Instant start = Instant.now();
        UUIDs.stream().sorted().findFirst().ifPresent(System.out::println);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }

    private void parallelStream() {
        Instant start = Instant.now();
        UUIDs.parallelStream().sorted().findFirst().ifPresent(System.out::println);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }

    @Test
    public void internalOperation() {
        List<String> list1 = Arrays.asList("hello", "world", "hello world");

        // only print hello
        list1.stream().map(e -> {
            int length = e.length();
            System.out.println(e);
            return length;
        }).filter(e -> e == 5).findFirst().ifPresent(System.out::println);

        System.out.println("===============");
        List<String> list2 = Arrays.asList("hello1", "world1", "hello world");

        list2.stream().map(e -> {
            int length = e.length();
            System.out.println(e);
            return length;
        }).filter(e -> e == 5).findFirst().ifPresent(System.out::println);
    }


}
