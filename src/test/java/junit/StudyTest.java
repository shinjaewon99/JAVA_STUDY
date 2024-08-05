package junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 언더 스코어 "_" 를 빈 공백으로 치환하는 전략
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
 */
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        Study study = new Study();
        assertNotNull(study);

        System.out.println("create");
    }

    @Test
    // @Disabled // 해당 테스트를 무시하는 어노테이션
    void create_new_study_again() {
        System.out.println("create1");
    }

    /**
     * 모든 테스트가 실행되기 전에 "한번"만 호출 되는것
     * 클래스 레벨에서 실행되어야 함으로, 반드시 static으로 선언
     * 리턴 타입이 있으면 안됨, default는 가능
     * -> static void 상용구 처럼 사용
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("before All");
    }

    /**
     * 모든 테스트가 실행된 후 "한번"만 호출 되는 것
     */
    @AfterAll
    static void afterAll() {
        System.out.println("after All");
    }

    /**
     * 각각의 테스트의 중간마다 호출 됨
     */
    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }
}