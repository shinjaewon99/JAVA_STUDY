package ch11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HigherOrderFunctionEx {
    public static void main(String[] args) {
        // HigherOrderFunction 이란 함수가 또다른 한수를 리턴하는 것
        final Function<Function<Integer, String>, String> function = g -> g.apply(10);


        System.out.println(
                function.apply(i -> "#" + i)
        );

        final Function<Integer, Function<Integer, Integer>> function2 = i -> i2 -> i + i2;
        System.out.println(
                // return 값이 Function임
                function2.apply(1).apply(9)
        );

        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // 정수(Integer) i값을 받아 문자열(String) 으로 반환 하는 함수
        final List<String> map = map(list, i -> "#" + i);

        System.out.println("map = " + map);

        System.out.println(list.stream().map(i -> "#" + i).collect(Collectors.toList()));


        // 파라미터의 순서에 따라 p1,p2,p3라고 하게 되면, p3는 p2인 함수를 리턴하고, p2는 p1인 함수를 리턴하게 된다.
        Function<Integer, Function<Integer, Function<Integer, Integer>>> f3 =
                i1 -> i2 -> i3 -> i1 + i2 + i3;

        Function<Integer, Function<Integer, Integer>> applied1 = f3.apply(1);
        Function<Integer, Integer> applied2 = applied1.apply(2);


        // 아래처럼 축약해서 사용 할 수 있음
        System.out.println("f3.apply(1).apply(2).apply(3) = " + f3.apply(1).apply(2).apply(3));

    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();

        for (final T t : list) {
            // 파라미터 Function은 T라는 제네릭에 대한 "R" 리턴을 가지게 된다.
            result.add(mapper.apply(t));
        }
        return result;
    }
}
