package effective_java.item13;

public class item13 {
    public static void main(String[] args) {
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
