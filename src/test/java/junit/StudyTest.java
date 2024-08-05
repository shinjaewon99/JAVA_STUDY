package junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 언더 스코어 "_" 를 빈 공백으로 치환하는 전략
 *
 * @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
 */
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        Study study = new Study();


        /**
         * 두개 이상의 assert 검증 일경우, 처음 assert가 실패하게 되면 두번째에 있는 assert는 검증을 할 수 조차 없음으로,
         * assertAll을 사용해 묶어준다.
         */
        assertAll(
                () -> assertNotNull(study),
                // 첫번째 파라미터에는 기대하는값 (expected), 두번째 파라미터에는 실제값 (actual)
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값은 DRAFT 이어야 한다."),

                /**
                 *
                 아래와 같이 람다식으로 메시지를 작성하게되면, 적어도 테스트가 실패할때만 메시지를 만들게 되면서, 불필요한 연산을 줄일 수 있다.
                 assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값은 DRAFT 이어야 한다.");
                 */

                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );


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