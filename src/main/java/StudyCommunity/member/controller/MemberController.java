package StudyCommunity.member.controller;

import StudyCommunity.dto.MultiResponseDto;
import StudyCommunity.member.dto.MemberPatchDto;
import StudyCommunity.member.dto.MemberPostDto;
import StudyCommunity.member.dto.MemberResponseDto;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.mapper.MemberMapper;
import StudyCommunity.member.service.MemberService;
import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/members")
@Validated
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
        Member member = memberService.createMember(memberMapper.memberPostDtoToMember(requestBody));
        URI location = UriCreator.createUri(MEMBER_DEFAULT_URL, member.getMemberId());

        return ResponseEntity.created(location).build();
    }

    // 회원 수정(Patch)
    @PatchMapping("/{member-id}")
    public ResponseEntity<?> patchMember(@PathVariable("member-id") @Positive long memberId,
                                         @Valid @RequestBody MemberPatchDto requestBody) {
        requestBody.setMemberId(memberId);
        Member member = memberService.updateMember(memberMapper.memberPatchDtoToMember(requestBody));
        MemberResponseDto response = memberMapper.memberToMemberResponseDto(member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 회원 조회(Get)
    @GetMapping("/{member-id}")
    public ResponseEntity<?> getMember(@PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponseDto(member))
                , HttpStatus.OK);
    }

    /*
    // 모든 회원 조회(Get)
    @GetMapping
    public ResponseEntity<?> getMembers() {
        List<Member> members = memberService.getAllMembers();

        return new ResponseEntity<>(members, HttpStatus.OK);
    }
     */

    // 모든 회원 조회(Get)
    @GetMapping
    public ResponseEntity<?> getMembers(@RequestParam int page, @RequestParam int size) {
        Page<Member> pageMembers = memberService.findAllMembers(page-1, size);
        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(memberMapper.membersToMemberResponseDtos(members), pageMembers)
                , HttpStatus.OK);
    }

    // 회원 삭제(Delete)
    @DeleteMapping("/{member-id}")
    public ResponseEntity<?> deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
