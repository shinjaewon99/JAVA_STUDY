package ch6;

import java.math.BigDecimal;

public class CustomFunctionExam {

    public static void main(String[] args) {
        customPrint(1, 2, 3, (i1, i2, i3) -> String.valueOf(i1 + i2 + i3));

        customPrint("넓이 : ", 12, 10, (message, width, length) -> message + (width * length));

        customPrint(1L, "shin", "test@aaaa.com",
                (userId, name, email) -> "User Id = " + userId + "\n" + "User Name = " + name + "\n" + "User email = " + email);

        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();

        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));

        final InvalidFunctionalInterface anonymousClass = new InvalidFunctionalInterface() {

            @Override
            public <T> String mkString(final T value) {
                return value.toString();
            }
        };

        System.out.println("익명 클래스 " + anonymousClass.mkString(123));

        // 람다 메소드가 되는 타겟 메소드가 제네릭이라 에러 발생
        // 타입에 대한 정보 (I, O) 가 없음 -> value.toString()을 작성할 시점에는 타입에 대해 알 수가 없어 에러 발생
//        final InvalidFunctionalInterface invalidFunctionalInterface = value -> value.toString();
//        System.out.println(invalidFunctionalInterface.mkString(123));
    }

    private static <T1, T2, T3> void customPrint(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }
}

// Custom 한 Function 인터페이스 제네릭 T1,T2,T3 를 매개변수로 받고 R 이라는 리턴값을 가짐
@FunctionalInterface // FunctionalInterface 어노테이션 작성시 어노테이션에서 에러가 발생 (컴파일 에러) -> 개발자가 쉽게 발견
        // FunctionalInterface를 사용할 경우 어노테이션은 필수 적으로 작성해서 사용 하자
interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);

//    void print(); // abstract 메소드가 하나만 존재해야 하는데 2개 이상일 경우 에러
}

@FunctionalInterface
interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface {
    <T> String mkString(T value);
}