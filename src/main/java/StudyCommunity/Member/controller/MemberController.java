package StudyCommunity.Member.controller;

import StudyCommunity.Member.dto.MemberPostDto;
import StudyCommunity.Member.entity.Member;
import StudyCommunity.Member.mapper.MemberMapper;
import StudyCommunity.Member.service.MemberService;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    // 회원 등록(Post)
    @PostMapping
    public ResponseEntity<?> postMember(@Valid @RequestBody MemberPostDto requestBody) {
        Member member = memberService.createMember(memberMapper.memberPostDtotoMember(requestBody));
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, member.getMemberId());

        return ResponseEntity.created(location).build();
    }
}
