package org.kniit.stream;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreatorsExample {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> streamFromList = list.stream();

        Integer[] arr = {1, 2, 3, 4, 5};
        Stream<Integer> streamFromArray = Arrays.stream(arr);

        Stream<Double> infiniteStream = Stream.generate(Math::random).limit(5);
        infiniteStream.forEach(System.out::println);

        Stream.iterate(2,n -> n <= 20,n -> n + 2).forEach(System.out::println);

        Stream<Integer> builderStream = Stream.<Integer>builder().add(10).add(20).add(30).build();
        builderStream.forEach(System.out::println);

        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        Stream<Integer> stream2 = Stream.of(4, 5);
        Stream<Integer> combinedStream = Stream.concat(stream1, stream2);
        combinedStream.forEach(System.out::println);

        String text = "hello";
        IntStream charStream = text.chars();
        charStream.forEach(System.out::println);

    }
}
