package ch7;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExam {

    public static void main(String[] args) {
        final List<Product> products = Arrays.asList(
                new Product(1L, "A", new BigDecimal("10.00")),
                new Product(2L, "B", new BigDecimal("20.00")),
                new Product(3L, "C", new BigDecimal("30.00")),
                new Product(4L, "D", new BigDecimal("40.00")),
                new Product(5L, "E", new BigDecimal("60.00"))

        );

        final BigDecimal twenty = new BigDecimal("20");

        // BigDecimal의 compareTo 메소드에서 0보다 크면은 참, 0 보다 작으면 거짓 이다.
        final List<Product> result = filter(products, product -> product.getPrice().compareTo(twenty) >= 0);


        System.out.println(result);

        final List<Product> expensiveProduct =
                filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) > 0);

        final List<DiscountProduct> discountProduct =
                map(expensiveProduct, product -> new DiscountProduct(product.getId(), product.getName(),
                        product.getPrice().multiply(new BigDecimal("0.5"))));

        final Predicate<Product> lessThenOrEquals30 = product -> product.getPrice().compareTo(new BigDecimal("30")) <= 0;

        System.out.println("expensiveProduct = " + expensiveProduct);
        System.out.println("discountProduct = " + discountProduct);

        System.out.println("discountProduct result" +
                filter(discountProduct, product -> product.getPrice().compareTo(new BigDecimal("30")) <= 0));


        final List<BigDecimal> prices = map(products, product -> product.getPrice());
        BigDecimal total = BigDecimal.ZERO;

        for (final BigDecimal price : prices) {
            total = total.add(price);
        }

        System.out.println(total);

        final BigDecimal newTotal = total(products, product -> product.getPrice());

        System.out.println(newTotal);

        final BigDecimal discountTotal = total(discountProduct, product -> product.getPrice());

        System.out.println("discountTotal = " + discountTotal);

    }


    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        final List<T> result = new ArrayList<>();
        for (final T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;

        for (final T t : list) {
            total = total.add(mapper.apply(t));
        }

        return total;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        final List<R> result = new ArrayList<>();

        for (final T product : list) {
            result.add(function.apply(product));
        }
        return result;
    }
}

@AllArgsConstructor
@Data
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

@ToString(callSuper = true)
class DiscountProduct extends Product {

    public DiscountProduct(final Long id, final String name, final BigDecimal price) {
        super(id, name, price);
    }
}

@AllArgsConstructor
@Data
class OrderItem {
    private Long id;
    private Product product;
    private int quantity;
}

@Data
class Order {
    private Long id;
    private String orderNumber;
    private List<OrderItem> items;
}