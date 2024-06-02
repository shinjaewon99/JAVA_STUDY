package ch8;

public class Stream_API_EX {
    public static void main(String[] args) {
        final int abs1 = Math.abs(-1);
        final int abs2 = Math.abs(1);


        System.out.println("abs1 = " + abs1);
        System.out.println("abs2 = " + abs2);

        System.out.println("abs1= == abs2 is = " + (abs1 == abs2));

        // 양수가 아닌 음수값이 출력된다.
        // Java 에서는 32비트인 Signed int 여서 음수 2의 31승 양수 2의 31승을 모두 가지게 된다.
        final int minInt = Math.abs(Integer.MIN_VALUE);
        System.out.println("minInt = " + minInt);

        // MAX_VALUE 에서는 2147483647 값이 출력되는데 이유는 0으로 채워진 32비트의 값을 한개 빼줘야 하기 때문
        final int maxInt = Math.abs(Integer.MAX_VALUE);
        System.out.println("maxInt = " + maxInt);
        
    }
}
