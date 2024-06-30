package effective_java.item13;

import lombok.Getter;
import lombok.Setter;

public class item13 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Struct struct = new Struct("1", "jw");

        System.out.println(struct.key);
        System.out.println(struct.value);

        // try - catch로 감싸야함 혹은 Lombok의 @SneakyThrows사용 (권장 X) -> 예외에 알맞은 분기 처리 식별이 어려움
        try {
            Struct clone = (Struct) struct.clone();
            System.out.println(clone.key);
            System.out.println(clone.value);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        // 복사된 객체와 원본이랑 같은 주소를 가지면 안된다.
        // 즉 다른 주소를 가져야 한다.
        System.out.println(struct != struct.clone());

        // 복사된 객체가 같은 클래스이어야 한다.
        System.out.println(struct.getClass() == struct.getClass());


        Person original = new Person("John", 30);
        Person otherPerson = Person.otherPerson(original);

        System.out.println("Original: " + original);
        System.out.println("Copy: " + otherPerson);
    }

}

// Object 객체에 clone() 메소드가 있으며, Cloneable을 구현(implements) 하지 않고 clone()을 호출하면
// CloneNotSupportedException 예외 발생
class Struct implements Cloneable {
    String key;
    String value;

    public Struct(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

// 정리
// 인터페이스를 만들때 절대 Cloneable을 implements 하면 안된다 -> 믹스인(사용) 의도로 만들어 진것
// final 클래스라면 Cloneable을 구현해도 상관없지만 성능 최적화를 검토
// 복제 기능은 생성자와 팩토리를 이용하는게 최선
@Getter
@Setter
class Person {
    private String name;
    private int age;

    // 기본 생성자
    public Person() {

    }

    // 매개변수를 받는 생성자
    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    // 복사 생성자 (접근자 메소드를 사용하는것이 권장)
    public Person(final Person other) {
        this.name = other.getName();
        this.age = other.getAge();
    }

    public static Person otherPerson(final Person other) {
        return new Person(other);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
