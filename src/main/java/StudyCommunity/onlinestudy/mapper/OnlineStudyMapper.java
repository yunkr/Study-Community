package StudyCommunity.onlinestudy.mapper;

import StudyCommunity.member.entity.Member;
import StudyCommunity.onlinestudy.dto.OnlineStudyPatchDto;
import StudyCommunity.onlinestudy.dto.OnlineStudyPostDto;
import StudyCommunity.onlinestudy.dto.OnlineStudyResponseDto;
import StudyCommunity.onlinestudy.entity.OnlineStudy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OnlineStudyMapper {

    default OnlineStudy onlineStudyPostDtoToOnlineStudy(OnlineStudyPostDto onlineStudyPostDto) {

        Member member = new Member();
        OnlineStudy onlineStudy = new OnlineStudy();

        member.setMemberId(member.getMemberId());

        onlineStudy.setMember(onlineStudyPostDto.getMember());
        onlineStudy.setTitle(onlineStudyPostDto.getTitle());
        onlineStudy.setTopic(onlineStudyPostDto.getTopic());
        onlineStudy.setSchedule(onlineStudyPostDto.getSchedule());
        onlineStudy.setCurriculum(onlineStudyPostDto.getCurriculum());
        onlineStudy.setPersonnel(onlineStudyPostDto.getPersonnel());
        onlineStudy.setIntroduction(onlineStudyPostDto.getIntroduction());
        onlineStudy.setPrecautions(onlineStudyPostDto.getPrecautions());
        onlineStudy.setApply(onlineStudyPostDto.getApply());

        return onlineStudy;
    }

    default OnlineStudy onlineStudyPatchDtoToOnlineStudy(OnlineStudyPatchDto onlineStudyPatchDto) {

        OnlineStudy onlineStudy = new OnlineStudy();

        onlineStudy.setOnlineStudyId(onlineStudyPatchDto.getOnlineStudyId());

        onlineStudy.setTitle(onlineStudyPatchDto.getTitle());
        onlineStudy.setTopic(onlineStudyPatchDto.getTopic());
        onlineStudy.setSchedule(onlineStudyPatchDto.getSchedule());
        onlineStudy.setCurriculum(onlineStudyPatchDto.getCurriculum());
        onlineStudy.setPersonnel(onlineStudyPatchDto.getPersonnel());
        onlineStudy.setIntroduction(onlineStudyPatchDto.getIntroduction());
        onlineStudy.setPrecautions(onlineStudyPatchDto.getPrecautions());
        onlineStudy.setApply(onlineStudyPatchDto.getApply());

        return onlineStudy;
    }

    default OnlineStudyResponseDto onlineStudyToOnlineStudyResponseDto(OnlineStudy onlineStudy) {

        OnlineStudyResponseDto onlineStudyResponseDto = new OnlineStudyResponseDto();

        onlineStudyResponseDto.setMemberId(onlineStudy.getMember().getMemberId());
        onlineStudyResponseDto.setOnlineStudyId(onlineStudy.getOnlineStudyId());

        onlineStudyResponseDto.setTitle(onlineStudy.getTitle());
        onlineStudyResponseDto.setTopic(onlineStudy.getTopic());
        onlineStudyResponseDto.setSchedule(onlineStudy.getSchedule());
        onlineStudyResponseDto.setCurriculum(onlineStudy.getCurriculum());
        onlineStudyResponseDto.setPersonnel(onlineStudy.getPersonnel());
        onlineStudyResponseDto.setIntroduction(onlineStudy.getIntroduction());
        onlineStudyResponseDto.setPrecautions(onlineStudy.getPrecautions());
        onlineStudyResponseDto.setApply(onlineStudy.getApply());

        onlineStudyResponseDto.setCreatedAt(onlineStudy.getCreatedAt());
        onlineStudyResponseDto.setLastModifiedAt(onlineStudy.getLastModifiedAt());

        return onlineStudyResponseDto;
    }

}
