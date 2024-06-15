package ch10;

public class ClosureEx {
    private int number = 999;

    public static void main(String[] args) {

        new ClosureEx().test1();
        // 자바 7 버전에서는 final을 붙이지 않을 경우 컴파일 에러 발생
        // Effective final = 결국에는 final 키워드가 앞에 붙여 있음
        int number = 100;

        /*
        number 변수를 변경 하려고 하면 컴파일 에러
        number = 1;
         */

        // 기존방식
        testClosure("Annoymous Class", new Runnable() {
            @Override
            public void run() {

                // this.number는 지역변수 number = 100에 대한 접근이 아니라, Runnable 객체안에 있는 number라는 변수에 대한 접근
                System.out.println(number);
            }
        });

        // 람다 방식
        testClosure("Lambda Expression", () -> System.out.println(number));

    }

    private void test1() {
        int number = 100;
        testClosure("Annoymous Class", new Runnable() {
            @Override
            public void run() {
                // 전역 변수에 있는 number = 999에 접근 할 수 있는 방법은 Clouser.this.number로 접근 (깔끔하지 않음)
                System.out.println(ClosureEx.this.number);
            }
        });

        // 람다 방식에서는 클래스명.this.변수를 하지 않아도 된다.
        // 람다 방식에서는 Runnable 객체에 대한 number가 아닌 클래스에 대한 number 접근이 가능해짐
        testClosure("Lambda Expression", () -> System.out.println(this.number));
    }

    private void test2() {
        testClosure("Annoymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println(this.toString());
            }
        });

        testClosure("Annoymous Class2", new Runnable() {
            @Override
            public void run() {
                System.out.println(ClosureEx.this.toString());
            }
        });

        testClosure("Lambda Expression", () -> System.out.println(this.toString()));
    }

    private void test3() {
        int number = 100;
        testClosure("Annoymous Class", new Runnable() {
            @Override
            public void run() {
                int number = 50;
                System.out.println(number);
            }
        });

        testClosure("Lambda Expression", () -> {
            // 람다 함수에서는 이미 scope 안에 해당 number 변수가 정의 되어 있다. (전역 변수로 number가 선언)
            // int number = 50;
            System.out.println(number);
        });

    }


    private static void testClosure(final String name, final Runnable runnable) {
        System.out.println("============================");
        System.out.println(name + " ");
        runnable.run();
        System.out.println("==========================");
    }
}
