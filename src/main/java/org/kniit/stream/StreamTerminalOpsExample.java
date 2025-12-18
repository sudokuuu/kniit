package org.kniit.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTerminalOpsExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 6, 8, 2, 4, 6, 8);

        numbers.stream().forEach(System.out::println);

        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println(evenNumbers);

        long count = numbers.stream().distinct().count();
        System.out.println(count);


        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        min.ifPresent(System.out::println);

        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        max.ifPresent(System.out::println);

        Optional<Integer> first = numbers.stream().filter(n -> n > 5).findFirst();
        first.ifPresent(System.out::println);

        System.out.println(numbers.stream().anyMatch(n -> n == 4));
        System.out.println(numbers.stream().allMatch(n -> n > 0));
        System.out.println(numbers.stream().noneMatch(n -> n < 0));

        Integer[] array = numbers.stream().toArray(Integer[]::new);
        System.out.println(Arrays.toString(array));

    }

}
