package ch4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExam {

    public static void main(String[] args) {
        // Function<T,R> 으로 사용 하는 반면, Predicate<T> 로 사용
        // Predicate<T> 는 주어진 값이 ~ 인지 아닌지 판별 하기 위해 많이 사용
        // EX : 주어진 수가 양수인지 아닌지
        Predicate<Integer> isPositive = value -> value > 0;

        System.out.println(isPositive.test(1));
        System.out.println(isPositive.test(0));
        System.out.println(isPositive.test(-1));

        List<Integer> store = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        // Predicate를 사용하지 않았을 때
        // 중복 (반복) 되는 코드가 많아짐
        List<Integer> positiveNumbers = new ArrayList<>();
        for (Integer number : store) {
            if (isPositive.test(number)) {
                positiveNumbers.add(number);
            }
        }
        System.out.println("정수 : " + positiveNumbers);

        Predicate<Integer> lessThen3 = value -> value < 3;
        List<Integer> numbersLessThen3 = new ArrayList<>();
        for (Integer number : store) {
            if (lessThen3.test(number)) {
                numbersLessThen3.add(number);
            }
        }
        System.out.println("3보다 작은 수 : " + numbersLessThen3);


        // -> 위 코드가 아래처럼 바뀔 수 있음
        System.out.println(filter(store, isPositive));
        System.out.println(filter(store, lessThen3));
    }

    // Predicate를 사용하는 이유
    // 제네릭을 사용하여 유연하게 사용 할 수 있음
    private static <T> List<T> filter(List<T> numbers, Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        for (T number : numbers) {
            if(condition.test(number)){
                result.add(number);
            }
        }
        return result;
    }
}
