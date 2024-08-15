package junit.study;

import junit.domain.Member;
import junit.domain.Study;
import junit.member.MemberService;

import java.util.Optional;

public class StudyService {

    private final MemberService memberService;
    private final StudyRepository studyRepository;

    public StudyService(final MemberService memberService, final StudyRepository studyRepository) {
        /**
         * assert : 특정 조건이 true 인지 false인지 확인하는 키워드
         */
        assert memberService != null;
        assert studyRepository != null;

        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(final Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwnerId(member.orElseThrow(() -> new IllegalArgumentException("회원 아이디" + memberId + "가 존재하지 않습니다.")).getId());
        Study newStudy = studyRepository.save(study);
        memberService.notify(newStudy);
        memberService.notify(member.get());

        return studyRepository.save(study);
    }

    public Study openStudy(final Study study) {
        study.open();
        Study openedStudy = studyRepository.save(study);
        memberService.notify(openedStudy);

        return openedStudy;
    }
}
