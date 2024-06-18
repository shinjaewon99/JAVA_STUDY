package ch12;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodReferenceEx {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5)
                .forEach(System.out::println);
//                .forEach(i -> System.out.println(i));

        Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                .stream()
//              .sorted(BigDecimalUtil::compare)
//              BigDecimal 객체가 comapreTo를 재정의 하므로 가능하다.
//              .sorted((bd1, bd2) -> bd1.compareTo(bd2))
                .sorted(BigDecimal::compareTo)
                .collect(Collectors.toList());

        // 특정한 객체 (값)에 대한 메소드 레퍼런스를 사용함
        final String targetString = "c";
        System.out.println(
                Arrays.asList("a", "b", "c", "d")
                        .stream()
                        .anyMatch(targetString::equals)
//                        .anyMatch(x -> x.equals("c"))
        );

        methodReference03();
    }

    private static void methodReference03() {
        // 람다
        System.out.println(testFirstClassFunction1(3, i -> String.valueOf(i * 2)));

        // 메소드 레퍼런스
        System.out.println(testFirstClassFunction1(3, MethodReferenceEx::doubleThenToString));

        System.out.println(getDoubleThenToStringUsingLambda().apply(3));

        System.out.println(getDoubleThenToStringUsingMethod().apply(3));
    }

    private static String doubleThenToString(int i) {

        return String.valueOf(i * 2);
    }

    private static String testFirstClassFunction1(int n, Function<Integer, String> f) {
        return "The result is" + f.apply(n);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambda() {
        return i -> String.valueOf(i * 2);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingMethod() {
        return MethodReferenceEx::doubleThenToString;
    }
}

class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }
}