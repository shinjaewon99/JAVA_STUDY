package junit.study;

import junit.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Mock 어노테이션과 @ExtendWith(MockitoExtenstion.class)는 같이 사용해야한다.
 * @Mock 만 붙일경우 작성한 필드가 null임
 */

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    /**
     * 전역에서 사용
     * @Mock
     * MemberService memberService;
     *
     * @Mock
     * StudyRepository studyRepository;
     */


    /**
     * mocking을 하기 위한 좋은 조건은 개발자가 사용한 클래스에서 구현체가 없이
     * 인터페이스로만 의존하여 코드를 작성한 경우 mocking을 하기 위한 좋은 조건이다.
     */
    @Test
    void createStudyService(@Mock MemberService memberService,
                            @Mock StudyRepository studyRepository) {

        /**
         *   필드에서 사용
         *   MemberService memberService = mock(MemberService.class);
         *   StudyRepository studyRepository = mock(StudyRepository.class);
         */


        StudyService studyService = new StudyService(memberService, studyRepository);


        assertNotNull(studyService);

    }
}