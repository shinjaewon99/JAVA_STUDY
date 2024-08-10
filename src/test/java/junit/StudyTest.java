package junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.*;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.ParameterMetaData;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * 언더 스코어 "_" 를 빈 공백으로 치환하는 전략
 *
 * @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
 */

/**
 * junit-platform.properties 에서 라이프 사이클을 관리
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS) // 하나의 클래스에서 인스턴스를 공유, @BeforeAll @AfterAll이 static일 필요 X
 */

/**
 * 테스트 순서, 단위테스트에서는 많이 사용X, 시나리오 테스트, 유스케이스 테스트 등등에서 사용
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    /**
     * 기본 전략이 테스트 마다 새로운 인스턴스를 생성하므로 공유하는 값이 다름
     * 테스트 마다 새로운 객체
     */
    int value = 1;

    /**
     * 아래의 두개의 테스트 모두 1 출력
     */
    @Test
    @Order(1) // junit의 제공하는 Order 어노테이션 사용
    void value_up_test1() {
        System.out.println(value++);
    }

    @Test
    @Order(2)
    void value_up_test2() {
        System.out.println(value++);
    }

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        Study study = new Study(10);

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


        /**
         * IllegalArgumentException 예외가 발생할 것을 예상하고 작성하는 Assertions
         */
        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit은 0보다 커야 한다.", exception.getMessage());
    }

    @Test
    void create_new_study_again_time() {
        /**
         * 테스트가 정의한 Time안에 끝내야 하는 Assertions
         *
         * assertTimeout(Duration.ofMillis(100), () -> {
         *             new Study(10);
         *             Thread.sleep(300);
         *         });
         */

        /**
         * 정의한 Time 근처에 테스트가 바로 종료 된다. (주의해서 사용, ThreadLocaL 사용시)
         *  assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
         *             new Study(10);
         *             Thread.sleep(300);
         *         });
         */
    }

    @Test

        // @EnabledOnOs(OS.MAC), OS가 MAC 인경우에는 실행
        // @DisabledOnOs({OS.WINDOWS, OS.LINUX}) OS가 WINDOW 일 때는 무시
        // @EnabledOnJre(JRE.JAVA_8) 특정 자바 버전에 따라 핸들링
    void create_assume_test() {
        /**
         * 테스트 환경이 LOCAL 인 경우에만 테스트 실행
         */
        String test_env = System.getenv("TEST ENV");
        System.out.println(test_env);
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));


        /**
         * 환경변수가 LOCAL 일 경우 아래의 테스트가 실행
         */
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            Study study = new Study(100);
            assertEquals(100, study.getLimit());
        });

        /**
         * 환경변수가 JW 일 경우 아래의 테스트가 실행
         */
        assumingThat("JW".equalsIgnoreCase(test_env), () -> {
            Study study = new Study(-10);
            assertEquals(-10, study.getLimit());
        });

    }

    // edit configuration 에서 test 설정을 tag 별로 분리해서 실행
    @FastTest
    /**
     * 생략 가능
     * @Test
     * @Tag("fast")
     */
    void fast_tag_test() {

    }

    @SlowTest
    /**
     * @Test
     * @Tag("slow")
     */
    void slow_tag_test() {

    }

    @DisplayName("반복 테스트")
    // 테스트 name을 pathVariable 처럼 바인딩 할 수 있음
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeat_test(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("파라미터 테스트")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    // 파라미터의 순서에 따라 message 라는 파라미터에 담겨서 각각 한번씩 총 4번 실행된다.
    @ValueSource(strings = {"날씨가", "많이", "더워지고", "있네요"})
    /**
     * @EmptySource
     * @NullSource
     **/
    @NullAndEmptySource
    void parameter_test(String message) {

    }

    /**
     * 반환 타입이 자동으로 변환이 되는 테스트
     */
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    void converter_test(
            // 하나의 Args일때 사용 @ConvertWith(StudyConverter.class) Study study
            // 생성자를 통한 Args 넘기기 Integer limit, String name
            // ArgumentsAccessor argumentsAccessor
            @AggregateWith(StudyAggregator.class) Study study
    ) {
        // System.out.println(new Study(limit, name));
        // Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));

        System.out.println(study);
    }

    /**
     * Aggregate는 반드시 static inner 클래스 이거나, public 이어야한다.
     */
    static class StudyAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(final ArgumentsAccessor accessor, final ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

    /**
     * Args가 하나일때 타입을 변환해주는 converter
     */
    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(final Object source, final Class<?> targetType) throws ArgumentConversionException {
            // (변환해야할 타입, targetType, 메시지)
            assertEquals(Study.class, targetType, "스터디로만 변환 가능");
            return new Study(Integer.parseInt(source.toString()));
        }
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