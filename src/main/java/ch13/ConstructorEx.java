package ch13;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.function.Function;

public class ConstructorEx {
    public static void main(String[] args) {
        final Section section = new Section(1);

        // Section이라는 클래스를 파라미터로 받고, Integer를 반환
        final Function<Integer, Section> sectionFactory = number -> new Section(number);
        final Section sectionWithFunction = sectionFactory.apply(1);

        // 이때 Section 객체가 생성이 된것이 아님, Function만 만들어진것
        final Function<Integer, Section> sectionFactoryWithMethodRef =
                Section::new;
        Section result = sectionFactoryWithMethodRef.apply(1);

        System.out.println(section);
        System.out.println(sectionWithFunction);
        System.out.println(result);

        final Product product = new Product(1L, "A", new BigDecimal("100"));
        System.out.println(product);

        final ProductCreator productCreator = Product::new;
        System.out.println(productCreator.create(1L, "A", new BigDecimal("100")));
    }

    // 아직 반환받을 객체가 정해지지 않았을 경우를 가정하여 제네릭 타입 사용
    private <T extends Product> T createProduct(Long id, String name, BigDecimal price, ProductCreator productCreator) {
        if(id == null || id < 1L){
            throw new IllegalArgumentException("id 값은 양수이어야 합니다.");
        }

        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름이 주어지지 않았습니다.");
        }

        if(price == null || BigDecimal.ZERO.compareTo(price) <= 0) {
        }
    }
}

// 커스텀 함수형 인터페이스
@FunctionalInterface
interface ProductCreator {
    Product create(Long id, String name, BigDecimal price);
}

@AllArgsConstructor
@Data
class Section {
    private int number;
}

@AllArgsConstructor
@Data
class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}

class ProductA extends Product {

    public ProductA(final Long id, final String name, final BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "A=" + super.toString();
    }
}

class ProductB extends Product {

    public ProductB(final Long id, final String name, final BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "B=" + super.toString();
    }
}