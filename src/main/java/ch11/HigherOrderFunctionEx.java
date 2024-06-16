package ch11;

import java.util.function.Function;

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
    }
}
