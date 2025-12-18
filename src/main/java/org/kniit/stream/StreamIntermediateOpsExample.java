package org.kniit.stream;

import java.util.List;
import java.util.stream.Collectors;

public class StreamIntermediateOpsExample {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 2, 4);
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .distinct()
            .map(n -> n * n)
            .sorted()
            .peek(System.out::println)
            .collect(Collectors.toList());

        System.out.println(numbers.stream().takeWhile(x -> x < 4).toList());
        System.out.println(numbers.stream().dropWhile(x -> x < 4).toList());
    }
}
