package junit;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 해당 어노테이션의 전략을 RUNTIME이 끝날때까지 어노테이션 설정
 * 사용할수 있는 위치는 METHOD로 설정
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 아래의 두개의 어노테이션을 META 어노테이션으로 사용해서 다른 어노테이션을 조합해서 사용
 */
@Test
@Tag("fast")
public @interface FastTest {

}
