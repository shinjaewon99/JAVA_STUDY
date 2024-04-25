package ch2;

public class OOPExam {
    public static void main(String[] args) {
        final CalculateService calculateService = new CalculateService(new Addition());
        final int addition = calculateService.calculate(1, 2);
        System.out.println(addition);

        final int subtraction = calculateService.calculate(1, 2);
        System.out.println(subtraction);

        final int multiplication = calculateService.calculate(1, 2);
        System.out.println(multiplication);

        final int division = calculateService.calculate(8, 2);
        System.out.println(division);

        // 행동 자체인 객체를 파라미터로 넘긴다.
        final FpCalculatorService fpCalculatorService = new FpCalculatorService();
        System.out.println("addition : " + fpCalculatorService.calculate((i1, i2) -> i1 + i2, 1, 2));
        System.out.println("subtraction : " + fpCalculatorService.calculate(new Subtraction(), 3, 2));
    }
}

interface Calculation {
    int calculate(int first, int second);
}

class Addition implements Calculation {
    @Override
    public int calculate(int first, int second) {
        return first + second;
    }
}

class Subtraction implements Calculation {
    @Override
    public int calculate(int first, int second) {
        return first - second;
    }
}

class Mutilation implements Calculation {
    @Override
    public int calculate(int first, int second) {
        return first * second;
    }
}

class Division implements Calculation {
    @Override
    public int calculate(int first, int second) {
        return first / second;
    }
}

class CalculateService {
    private final Calculation calculation;

    public CalculateService(final Calculation calculation) {
        this.calculation = calculation;
    }

    public int calculate(int first, int second) {
        return calculation.calculate(first, second);
    }
}

class FpCalculatorService {
    public int calculate(Calculation calculation, int first, int second) {
        // 함수형 프로그래밍, Calculation을 파라미터로 받아 메소드를 선택 할 수 있음
        return calculation.calculate(first, second);
    }
}
