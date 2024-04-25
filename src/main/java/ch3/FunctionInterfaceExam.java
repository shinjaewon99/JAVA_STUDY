package ch3;

import java.util.function.Function;

public class FunctionInterfaceExam {

    public static void main(String[] args) {
        final Function<String, Integer> toInt = value -> Integer.valueOf(value);

        final Integer number1 = toInt.apply("100");
        System.out.println(number1);

        final Function<Integer, Integer> identity = Function.identity();

        final Integer number2 = identity.apply(100);
        System.out.println(number2);

    }

    // 같은 타입이면서 같은 값을 반환하면 identity Function
    public String identity(final String value) {
        return value;
    }

    // 한 타입에서 다른 타입을 반환하면 Function
    public int toInt(final String value) {
        return Integer.parseInt(value);
    }
}
