package junit.member;

import junit.domain.Member;
import junit.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(final Long memberId);

    void notify(final Study newStudy);

    void notify(final Member member);
}
