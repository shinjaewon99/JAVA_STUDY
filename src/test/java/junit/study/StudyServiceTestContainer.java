package junit.study;

import junit.member.MemberService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@TestContainers
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
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();


    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgreSQLContainer.stop();
    }


}
