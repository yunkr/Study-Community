package StudyCommunity.member.mapper;

import StudyCommunity.member.dto.MemberPatchDto;
import StudyCommunity.member.dto.MemberPostDto;
import StudyCommunity.member.dto.MemberResponseDto;
import StudyCommunity.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

        return memberResponseDto;

    }

    // Java 17부터 Mapper부분은 default문을 사용해서 해야하나? default문을 사용하지 않으면 Postman에서 오류 뜸 500에러로
    default List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members) {
        return members.stream()
                .map(this::memberToMemberResponseDto)
                .collect(Collectors.toList());
    }
}
