package ch3;

import java.util.function.Consumer;
import java.util.function.Function;

public class ConsumerExam {
    public static void main(String[] args) {

        final Consumer<String> print1 = value ->  System.out.println(value);
        
        /* Function을 사용하여 Void로 accept 해주게 되면 타입 error가 발생
           Function은 기본적으로 return 값을 가져야함
        final Function<String, Void> print2 = value -> System.out.println(value);  error 발생
         */

        // 출력 결과마다 "hello" 를 붙여줄 수 있다
        final Consumer<String> print3 = value -> System.out.println("hello " + value);

        print1.accept("hello");
        print3.accept("Shin");
    }
}
