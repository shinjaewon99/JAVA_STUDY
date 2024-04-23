package ch1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ch1 {
    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        final StringBuilder stringBuilder = new StringBuilder();

        // 문제 : 1 : 2 : 3 : ~~ 으로 출력하고 싶음

        // 1. 결과 1 : 2 : 3 : ~~ 10 : 으로 마지막에 ':' 이 붙음
        for (Integer number : numbers) {
            stringBuilder.append(number).append(" : ");
        }

        // 2. 아래의 코드로 해결 할 수 있지만, 신경써야할 부분과 실수 할 부분이 많아짐
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        }

        // 3. 자바 8의 함수형 프로그램으로 해결
        // 순서
        // 1. stream 호출
        // 2. map을 호출, HashMap의 <key, value> 쌍이 아닌 하나의 객체를 다른 객체로 mapping 한다
        // numbers에 들어있는 값이 Integer인데 String으로 mapping
        // 3. "1" "2" ~ "10" 으로 반환된 String을 join해라 -> "1 : 2 : 3 ~ : 10"
        final String result = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" : "));

        System.out.println(stringBuilder.toString());

        System.out.println(result);
    }
}
