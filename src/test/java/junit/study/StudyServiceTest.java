package junit.study;

import junit.domain.Member;
import junit.domain.Study;
import junit.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void createStudyStubService(@Mock MemberService memberService,
                                @Mock StudyRepository studyRepository) {

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jw.com");

        /**
         * 1L를 가진 memberId를 찾고나서, member를 리턴해라
         */
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        Study study = new Study(10, "java");

        studyService.createNewStudy(1L, study);

        /**
         * 체인 형식으로 아래처럼 작성할수도 있다.
         * 한번에 실행되는 코드가 아니라, 호출횟수에 따라 return하는것
         */
        when(any())
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        // 첫번째 호출
        Optional<Member> byId = memberService.findById(1L);
        assertEquals("jw.com", byId.get().getEmail());

        // 두번째 호출
        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        // 세번째 호출
        assertEquals(Optional.empty(), memberService.findById(3L));

    }

    @Test
    void createTodoStudyService(@Mock MemberService memberService,
                                @Mock StudyRepository studyRepository) {

        /**
         *   필드에서 사용
         *   MemberService memberService = mock(MemberService.class);
         *   StudyRepository studyRepository = mock(StudyRepository.class);
         */
        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("jw.com");

        Study study = new Study(10, "테스트");

        /**
         * TODO
         * memberService 객체에 findById 메소드를 1L 값으로 호출하면, Optional.of(member) 객체를 리턴하도록 stubbing
         */
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        /**
         * TODO
         * studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 그대로 리턴하도록 stubbing
         */
        when(studyRepository.save(study)).thenReturn(study);

        /**
         * verify + times
         * 메소드 호출을 예상하고 카운트를 주는 테스트
         * 메소드의 호출을 예상하고 카운트를 주었지만, 호출이 일어나지 않으면 에러 발생
         */
        verify(memberService, times(1)).notify(study);

        /**
         * 한번도 호출이 되지 않는것을 예상
         */
        // verify(memberService, never()).validate(any());

        /**
         * 메소드 호출 순서를 예상
         */
        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);


        /**
         * 더이상 호출이 없는 것을 예상
         * 어떠한 액션 이후에 해당 Mock 객체를 사용하지 않아야함
         */
        verifyNoMoreInteractions(memberService);


    }
}