package StudyCommunity.member.mapper;

import StudyCommunity.member.dto.MemberPatchDto;
import StudyCommunity.member.dto.MemberPostDto;
import StudyCommunity.member.dto.MemberResponseDto;
import StudyCommunity.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    Member memberPostDtotoMember(MemberPostDto requestBody);

    Member memberPatchDtotoMember(MemberPatchDto requestBody);

    MemberResponseDto memberToMemberResponseDto(Member member);

    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);

}
