package ch8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stream_Prac {
    public static void main(String[] args) {
        /**
         IntStream.rangeClosed(1, 10).forEach(i -> System.out.println(i + ""));

         // i 는 초기값 1부터 시작해서 2, 3, 4 로 int 배열의 메모리 크기만큼 출력이 된다.
         IntStream.iterate(1, i -> i + 1)
         .forEach(i -> System.out.println(i + " "));
         **/

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final List<Integer> result = new ArrayList<>();

        for (final Integer number : numbers) {
            if (number > 3 && number < 9) {
                final Integer newNumber = number * 2;
                result.add(newNumber);
            }
        }

        System.out.println("result = " + result);


        // Optional[12] 가 출력되는 이유는, null 안정성을 위해서 stream은 Optional을 사용한다.
        System.out.println(
                numbers.stream()
                .filter(number -> number > 3)
                .filter(number -> number < 9)
                .map(number -> number * 2)
                .filter(number -> number > 10)
                .findFirst());
    }
}
