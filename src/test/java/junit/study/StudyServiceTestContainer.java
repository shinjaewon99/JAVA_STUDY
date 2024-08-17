package junit.study;

import junit.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@TestContainers
@Slf4j
public class StudyServiceTestContainer {

    @Mock
    MemberService memberService;

    /**
     * Mock 객체가 아닌 실제 객체
     */
    @Autowired
    StudyRepository studyRepository;

    /**
     * 사용하는 DB에 따라 컨테이너를 변경 할 수 있음
     * static 필드로 만들어 테스트가 동작할때 하나의 컨테이너만 생성되게 제어
     */
    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer()
            .withDatabaseName("studytest");

    /*

        @BeforeAll
        static void beforeAll() {
            postgreSQLContainer.start();
        }

        @AfterAll
        static void afterAll() {
            postgreSQLContainer.stop();
        }
    */
    @BeforeAll
    static void beforeAll() {
        Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(log);
        postgreSQLContainer.followOutPut(logConsumer);
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("==============");
        System.out.println(postgreSQLContainer.getMappedPort(5432));
        studyRepository.deleteAll();
    }


}
