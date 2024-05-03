package StudyCommunity.studycomment.mapper;

import StudyCommunity.member.entity.Member;
import StudyCommunity.study.entity.Study;
import StudyCommunity.studycomment.dto.StudyCommentPatchDto;
import StudyCommunity.studycomment.dto.StudyCommentPostDto;
import StudyCommunity.studycomment.dto.StudyCommentResponseDto;
import StudyCommunity.studycomment.entity.StudyComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.stream.Collectors;


@Mapper
public interface StudyCommentMapper {

    default StudyComment studyCommentPostDtoToStudyComment(StudyCommentPostDto studyCommentPostDto) {

        Member member = new Member();
        Study study = new Study();
        StudyComment studyComment = new StudyComment();

        member.setMemberId(member.getMemberId());
        study.setStudyId(study.getStudyId());

        studyComment.setMember(studyCommentPostDto.getMember());
        studyComment.setStudy(studyCommentPostDto.getStudy());

        studyComment.setContent(studyCommentPostDto.getContent());

        return studyComment;
    }

    default StudyComment studyCommentPatchDtoToStudyComment(StudyCommentPatchDto studyCommentPatchDto) {

        StudyComment studyComment = new StudyComment();

        studyComment.setStudyCommentId(studyCommentPatchDto.getStudyCommentId());

        studyComment.setContent(studyCommentPatchDto.getContent());

        return studyComment;
    }

    default StudyCommentResponseDto studyCommentToStudyCommentResponseDto(StudyComment studyComment) {

        StudyCommentResponseDto studyCommentResponseDto = new StudyCommentResponseDto();

        studyCommentResponseDto.setMemberId(studyComment.getMember().getMemberId());
        studyCommentResponseDto.setStudyId(studyComment.getStudy().getStudyId());

        studyCommentResponseDto.setStudyCommentId(studyComment.getStudyCommentId());

        studyCommentResponseDto.setContent(studyComment.getContent());

        studyCommentResponseDto.setCreatedAt(studyComment.getCreatedAt());
        studyCommentResponseDto.setLastModifiedAt(studyComment.getLastModifiedAt());

        return studyCommentResponseDto;
    }

    default List<StudyCommentResponseDto> studyCommentToStudyCommentResponseDtos(List<StudyComment> studyComments) {
        return studyComments.stream()
                .map(this::studyCommentToStudyCommentResponseDto)
                .collect(Collectors.toList());
    }

}
