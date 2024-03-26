package StudyCommunity.Member.service;

import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.Member.entity.Member;
import StudyCommunity.Member.mapper.MemberMapper;
import StudyCommunity.Member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    // 회원 등록
    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        verifyExistsNickName(member.getNickname());

        return memberRepository.save(member);
    }

    // 회원 수정
    public Member updateMember(Member member) {
        Member findMember = findMember(member.getMemberId());
        findMember.setEmail(member.getEmail());
        findMember.setNickname(member.getNickname());

        return memberRepository.save(findMember);
    }

    // 회원 조회
    public Member findMember(long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    // 모든 회원 조회
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }


    // NickName 가져오기
    private void verifyExistsNickName(String nickname) {
        Optional<Member> member = memberRepository.findByNickname(nickname);
        if (member.isPresent()) throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

    // Email 가져오기
    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
