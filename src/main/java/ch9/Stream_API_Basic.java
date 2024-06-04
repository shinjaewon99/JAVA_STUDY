package ch9;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream_API_Basic {
    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 3, 4, 4, 5);

        // intermediate Operation Method 이며, Stream을 리턴하기 때문에 계속 무엇을 해야될지 Stream에게 지시할 수 있음
        // 잘 알려진 filter, map 등
        List<String> collect = numbers
                .stream()
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .toList();


        System.out.println("collect = " + collect);

        System.out.println("toSet = " + numbers
                .stream()
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(Collectors.toSet()));

        System.out.println("joining = " + numbers
                .stream()
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(Collectors.joining(",")));

        System.out.println("joining Distinct = " + numbers
                .stream()
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(Collectors.joining(",")));


        final Integer num = 3;

        // 아래와 같이 "==" 비교를 하게 되면, 값비교가 아닌, 메모리 참조를 비교한다.
        // 아래와 같은 거는 num = 3과 Stream.of에 있는 3은 다른 객체이지만 자동 형변환으로 인해 True(참)이 됨
        // Integer.valueOf(i) 로 형변환 되어 Integer가 리턴됨
        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i == num)
                        .findFirst()
        );


        final Integer number127 = 127;

        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 127)
                        .filter(i -> i == number127)
                        .findFirst()
        );


        // 127까지는 같은 메모리 참조를 가지며, 128부터는 다른 메모리를 가지게 된다.
        final Integer number128 = 128;

        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter(i -> i == number128)
                        .findFirst()
        );

        // equals 비교
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter(i -> i.equals(number128))
                        .findFirst()
        );
    }
}
