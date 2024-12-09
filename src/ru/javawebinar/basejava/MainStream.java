package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(minValue(new int[]{9, 8}));

        System.out.println(oddOrEven(Arrays.asList(1, 1, 1, 2, 2, 1)));
        System.out.println(oddOrEven(Arrays.asList(1, 1, 1, 2, 2)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (num, digit) -> num * 10 + digit);

    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();

        return integers.stream().filter(num -> (sum % 2 == 0) == (num % 2 != 0))
                .collect(Collectors.toList());
    }
}
