package ch9;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ParallelProgrammingEx {
    public static void main(String[] args) {

        // 익명클래스의 접근을 위해 0으로 초기화
        final int[] sum = {0};

        IntStream.range(0, 100)
                .forEach(i -> sum[0] += i);

        System.out.println("sum = " + sum[0]);


        final int[] parallelSum = {0};
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> {
                    synchronized (parallelSum) {
                        parallelSum[0] += i;
                        // 인위적인 지연 추가
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

        System.out.println("parallelSum = " + parallelSum[0]);

        System.out.println(
                IntStream.range(0, 100)
                        .sum()
        );

        System.out.println(
                IntStream.range(0, 100)
                        .parallel()
                        .sum()
        );

        System.out.print("\n======================================\n");

        final long start1 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));

        System.out.println(System.currentTimeMillis() - start1);

        System.out.print("\n=====================================\n");

        final long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i;
                })
                .forEach(i -> System.out.println(i));

        System.out.println(System.currentTimeMillis() - start2);
        
        // core 를 몇개를 사용할건지 지정 할 수 있음
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
    }
}
