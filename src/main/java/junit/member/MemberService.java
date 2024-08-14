package junit.member;

import junit.domain.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(final Long memberId);
}
