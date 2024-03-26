package StudyCommunity.Member.mapper;

import StudyCommunity.Member.dto.MemberPatchDto;
import StudyCommunity.Member.dto.MemberPostDto;
import StudyCommunity.Member.dto.MemberResponse;
import StudyCommunity.Member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    Member memberPostDtotoMember(MemberPostDto requestBody);

    Member memberPatchDtotoMember(MemberPatchDto requestBody);

    MemberResponse memberToMemberResponseDto(Member member);

    List<MemberResponse> membersToMemberResponseDtos(List<Member> members);

}
