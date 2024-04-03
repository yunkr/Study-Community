package StudyCommunity.member.mapper;

import StudyCommunity.member.dto.MemberPatchDto;
import StudyCommunity.member.dto.MemberPostDto;
import StudyCommunity.member.dto.MemberResponseDto;
import StudyCommunity.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    default Member memberPostDtoToMember(MemberPostDto memberPostDto) {

        Member member = new Member();

        member.setEmail(memberPostDto.getEmail());
        member.setPassword(memberPostDto.getPassword());
        member.setNickname(memberPostDto.getNickname());

        return member;
    }

    default Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {

        Member member = new Member();

        member.setMemberId(memberPatchDto.getMemberId());
        member.setEmail(memberPatchDto.getEmail());
        member.setNickname(memberPatchDto.getNickname());

        return member;
    }

    default MemberResponseDto memberToMemberResponseDto(Member member) {

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        memberResponseDto.setMemberId(member.getMemberId());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setNickname(member.getNickname());
        memberResponseDto.setCreatedAt(member.getCreatedAt());
        memberResponseDto.setLastModifiedAt(member.getLastModifiedAt());

//        memberResponseDto.setCreatedBy(memberResponseDto.getCreatedBy());
//        memberResponseDto.setLastModifiedBy(member.getLastModifiedBy());

        return memberResponseDto;

    }

    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

}
