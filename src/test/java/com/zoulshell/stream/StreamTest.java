package com.zoulshell.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void create() {
        // 1 from stream's static method.
        Stream<String> stream1 = Stream.of("aaa", "bbb", "ccc");
        stream1.forEach(System.out::print);

        // 2 from arrays.
        String[] strings1 = {"ddd", "eee", "fff"};
        Stream<String> stream2 = Arrays.stream(strings1);
        stream2.forEach(System.out::print);

        // 3 from list.
        List<String> strings2 = Arrays.asList("ggg", "hhh", "iii");
        Stream<String> stream3 = strings2.stream();
        stream3.forEach(System.out::print);
    }

    @Test
    public void generate() {
        Stream.generate(UUID.randomUUID()::toString).findFirst().ifPresent(System.out::println);
        Stream.generate(UUID::randomUUID).limit(9).map(UUID::toString).forEach(System.out::println);
        Stream.generate(Math::random).limit(9).forEach(System.out::println);
        Stream.iterate(0, e -> e + 2).limit(9).forEach(System.out::print);
        System.out.println(Stream.iterate(0, e -> e + 2).limit(9).map(String::valueOf).collect(Collectors.toList()));
    }

    @Test
    public void compute() {
        // 1,2,3,4,5...->2,4,6,8,10...->0+2+4+6+8+10...
        int sum = IntStream.rangeClosed(1, 10).map(e -> e * 2).reduce(0, Integer::sum);
        System.out.println(sum);

        // generate array
        // int[] ints = IntStream.range(1, 10).toArray();
        // generate list
        ArrayList<Integer> arrayList = IntStream.range(1, 10).collect(ArrayList::new, List::add, ArrayList::addAll);
        System.out.println(arrayList);

        // mapToInt: Reduce performance loss
        // if stream is empty, sum is 0
        int sum2 = Stream.iterate(1, e -> e + 2).limit(6).filter(e -> e > 2)
                .mapToInt(e -> e * 2).skip(2).limit(2).sum();
        System.out.println(sum2);

        // if stream is empty, max is optional
        // The loop is executed only once no matter how many stream is
        Stream.iterate(1, e -> e + 2).limit(6).filter(e -> e > 2)
                .mapToInt(e -> e * 2).skip(2).limit(2).max().ifPresent(System.out::println);

        // if stream is empty
        IntSummaryStatistics intSummaryStatistics = Stream.iterate(1, e -> e + 2).limit(6).filter(e -> e > 200).mapToInt(e -> e * 2)
                .summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());//-2147483648
        System.out.println(intSummaryStatistics.getAverage());//0.0
        System.out.println(intSummaryStatistics.getMin());//2147483647
        System.out.println(intSummaryStatistics.getCount());//0
        System.out.println(intSummaryStatistics.getSum());//0
    }

    @Test
    public void transform() {
        // equals: Arrays.asList("ddd", "eee", "fff").stream()
        // list -> array
        String[] strings = Stream.of("aaa", "bbb", "ccc").toArray(String[]::new);
        // array -> list
        Arrays.asList(strings).forEach(System.out::print);
    }

    @Test
    public void collect() {
        // supplier->accumulator->combiner
        // 1.new container,
        // 2.add element to the result of step 1,
        // 3.combine the result of each loop and return the final container
        // to array list
        ArrayList<String> collect1 = Stream.of("a", "b", "c", "d", "e")
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(collect1);

        // to array list
        List<String> collect2 = Stream.of("a", "b", "c", "d", "e").collect(Collectors.toList());
        System.out.println(collect2);

        // to linked list
        LinkedList<String> collect3 = Stream.of("a", "b", "c", "d", "e")
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(collect3);

        // to tree set
        TreeSet<String> set1 = Stream.of("a", "b", "c", "d", "e").collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set1);

        // to hash set
        Set<String> set2 = Stream.of("a", "b", "c", "d", "e").collect(Collectors.toSet());
        System.out.println(set2);

        // append strings
        String strings1 = Stream.of("a", "b", "c", "d", "e")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        System.out.println(strings1);

        // append strings
        // String strings2 = Stream.of("a", "b", "c", "d", "e").collect(Collectors.joining());
        String strings2 = String.join("", "a", "b", "c", "d", "e");
        System.out.println(strings2);

        Stream.of("a", "b", "c", "d", "e").map(String::toUpperCase)
                .collect(Collectors.toList()).forEach(System.out::print);
    }

    @Test
    public void flatMap() {
        Stream<List<Integer>> streamList = Stream.of(Collections.singletonList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
        streamList.flatMap(List::stream).map(e -> e * e).forEach(System.out::println);
    }

    @Test
    public void status() {
        Stream<String> stream = Stream.of("a", "b", "c", "d", "e");
        System.out.println(stream);
        System.out.println(stream.limit(2));
        // stream cannot be used repeatedly
        // java.lang.IllegalStateException: stream has already been operated upon or closed
        // System.out.println(stream.distinct());
    }

    @Test
    public void problems() {
        // never stop, iterator will always generate element and distinct never stop
        // IntStream.iterate(0, e -> (e + 1) % 2).distinct().limit(9).forEach(System.out::println);
        // should be like this
        IntStream.iterate(0, e -> (e + 1) % 2).limit(9).distinct().forEach(System.out::println);
    }
}