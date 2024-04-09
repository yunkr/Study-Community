package StudyCommunity.study.mapper;

import StudyCommunity.member.entity.Member;
import StudyCommunity.study.dto.StudyPatchDto;
import StudyCommunity.study.dto.StudyPostDto;
import StudyCommunity.study.dto.StudyResponseDto;
import StudyCommunity.study.entity.Study;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudyMapper {

    default Study studyPostDtoToStudy(StudyPostDto studyPostDto) {

        Member member = new Member();
        Study study = new Study();

        member.setMemberId(member.getMemberId());

        study.setMember(studyPostDto.getMember());
        study.setTitle(studyPostDto.getTitle());
        study.setTopic(studyPostDto.getTopic());
        study.setSchedule(studyPostDto.getSchedule());
        study.setCurriculum(studyPostDto.getCurriculum());
        study.setPersonnel(studyPostDto.getPersonnel());
        study.setIntroduction(studyPostDto.getIntroduction());
        study.setPrecautions(studyPostDto.getPrecautions());
        study.setApply(studyPostDto.getApply());

        return study;
    }

    default Study studyPatchDtoToStudy(StudyPatchDto studyPatchDto) {

        Study study = new Study();

        study.setStudyId(studyPatchDto.getStudyId());

        study.setTitle(studyPatchDto.getTitle());
        study.setTopic(studyPatchDto.getTopic());
        study.setSchedule(studyPatchDto.getSchedule());
        study.setCurriculum(studyPatchDto.getCurriculum());
        study.setPersonnel(studyPatchDto.getPersonnel());
        study.setIntroduction(studyPatchDto.getIntroduction());
        study.setPrecautions(studyPatchDto.getPrecautions());
        study.setApply(studyPatchDto.getApply());

        return study;
    }

    default StudyResponseDto studyToStudyResponseDto(Study study) {

        StudyResponseDto studyResponseDto = new StudyResponseDto();

        studyResponseDto.setMemberId(study.getMember().getMemberId());
        studyResponseDto.setStudyId(study.getStudyId());

        studyResponseDto.setTitle(study.getTitle());
        studyResponseDto.setTopic(study.getTopic());
        studyResponseDto.setSchedule(study.getSchedule());
        studyResponseDto.setCurriculum(study.getCurriculum());
        studyResponseDto.setPersonnel(study.getPersonnel());
        studyResponseDto.setIntroduction(study.getIntroduction());
        studyResponseDto.setPrecautions(study.getPrecautions());
        studyResponseDto.setApply(study.getApply());

        studyResponseDto.setCreatedAt(study.getCreatedAt());
        studyResponseDto.setLastModifiedAt(study.getLastModifiedAt());

        return studyResponseDto;
    }

}
