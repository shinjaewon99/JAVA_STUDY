package ch8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Identity_Function_EX {
    public static void main(String[] args) {


        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);


        System.out.println(
                map(numbers, i -> i * 2)
        );

        System.out.println("------------------");
        // list 원소의 값에 대해 아무런 작업을 하고 싶지 않을 경우
        System.out.println(
                mapOld(numbers, i -> i)
        );

        System.out.println(
                mapOld(numbers, Function.identity())
        );
    }

    private static <T, R> List<R> map(final List<T> list, final Function<T, R> mapper) {
        final Function<T, R> function;
        if (mapper != null) {
            function = mapper;
        } else {
            function = x -> (R) x;
        }
        final List<R> result = new ArrayList<>();

        for (final T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }


    // List에 들어있는 원소를 다른 타입의 원소로 변환 (매퍼) 해주는 메소드
    private static <T, R> List<R> mapOld(final List<T> list, final Function<T, R> mapper) {
        final List<R> result;

        if (mapper != null) {
            result = new ArrayList<>();
        } else {
            result = new ArrayList<>((List<R>) list);
        }
        if (result.isEmpty()) {
            for (final T t : list) {
                result.add(mapper.apply(t));
            }
        }
        return result;
    }
}
