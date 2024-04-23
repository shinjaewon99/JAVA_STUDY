package ch2;

public class OOPExam {
    public static void main(String[] args) {
        final CalculateService calculateService = new CalculateService();
        final int addition = calculateService.sum('+', 1, 2);
        System.out.println(addition);

        final int subtraction = calculateService.sum('-', 1, 2);
        System.out.println(subtraction);

        final int multiplication = calculateService.sum('*', 1, 2);
        System.out.println(multiplication);

        final int division = calculateService.sum('/', 8, 2);
        System.out.println(division);
    }
}

class CalculateService {
    public int sum(final char calculation, final int first, final int second) {
        if (calculation == '+') {
            return first + second;
        } else if (calculation == '-') {
            return first - second;
        } else if (calculation == '*') {
            return first * second;
        } else if (calculation == '/') {
            return first / second;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
