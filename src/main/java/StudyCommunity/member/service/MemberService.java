package StudyCommunity.member.service;

import StudyCommunity.auth.utils.CustomAuthorityUtils;
import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.mapper.MemberMapper;
import StudyCommunity.member.repository.MemberRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;


    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    // 회원 등록
    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        verifyExistsNickName(member.getNickname());

        // Password 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        // DB에 User Role 저장
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

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
//    public List<Member> getAllMembers() {
//        return memberRepository.findAll();
//    }

    public Page<Member> findAllMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }

    // 회원 삭제
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
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
