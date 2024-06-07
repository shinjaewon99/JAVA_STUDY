package ch9;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Stream_API_Basic2 {
    public static void main(String[] args) {

        final List<Product> products = Arrays.asList(
                new Product(1L, "A", 100),
                new Product(2L, "B", 200),
                new Product(3L, "C", 300),
                new Product(4L, "D", 400)
        );

        List<Product> result1 = products.stream()
                .filter(product -> product.getPrice() >= 300)
                .collect(toList());

        String result2 = products.stream()
                .filter(product -> product.getPrice() >= 300)
                .map(product -> product.toString())
                .collect(joining(","));

        // Product의 각 price의 합을 구하고 싶은 경우
        // .reduce() -> 원소를 하나씩 줄여나가서 하나만 남기는 메소드
        // 파라미터를 2개 지정하고, 초기값 = 0을 지정한다. price1은 이전값이 되고, price2는 새로받은값을 파라미터로 받는다.
        Object sumResult = products.stream()
                .map(product -> product.getPrice())
                .reduce(0, (price1, price2) -> price1 + price2);

        System.out.println(sumResult);

    }

    private static class Product {
        private long index;
        private String name;
        private int price;

        public Product(final long index, final String name, final int price) {
            this.index = index;
            this.name = name;
            this.price = price;
        }

        public long getIndex() {
            return index;
        }

        public void setIndex(final long index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(final int price) {
            this.price = price;
        }
    }


}
