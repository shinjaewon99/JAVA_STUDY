package ch5;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SupplierExam {
    public static void main(String[] args) throws InterruptedException {
        // Supplier은 매개변수가 존재 하지 않는 메소드인 .get()이 존재
        final Supplier<String> helloSupplier = () -> "hello ";

        System.out.println(helloSupplier.get() + "world");

        // 실제 각각의 printIfValidIndex에 대해서 3초씩 걸려서 9초가 출력된다.
        long start = System.currentTimeMillis();
        printIfValidIndex(0, getVeryExpensiveValue());
        printIfValidIndex(-1, getVeryExpensiveValue());
        printIfValidIndex(-2, getVeryExpensiveValue());

        System.out.println("걸린 시간 : " + (System.currentTimeMillis() - start) / 1000);


        // Supplier 를 사용시 9초가 아닌 3초가 출력
        /**
         Supplier 를 사용시 매개변수 number 값이 0이상일 경우에만
         getVeryExpensiveValue메소드를 실행 하기 때문에 메모리, CPU 낭비 없이 사용이 가능
         number가 -1, -2일 경우 getVeryExpensiveValue메소드를 실행 하지 않음
         **/
        long startSupplier = System.currentTimeMillis();
        printIfValidIndexSupplier(0, () -> getVeryExpensiveValue());
        printIfValidIndexSupplier(-1, () -> getVeryExpensiveValue());
        printIfValidIndexSupplier(-2, () -> getVeryExpensiveValue());

        System.out.println("걸린 시간 : " + (System.currentTimeMillis() - startSupplier) / 1000);
    }

    private static String getVeryExpensiveValue(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "shin";
    }

    private static void printIfValidIndex(int number, String value) {
        if(number >= 0){
            System.out.println("값은 : " + value);
        }else{
            System.out.println("Invalid");
        }
    }

    private static void printIfValidIndexSupplier(int number, Supplier<String> valueSupplier) {
        if(number >= 0){
            System.out.println("값은 : " + valueSupplier.get());
        }else{
            System.out.println("Invalid");
        }
    }
}
