package StudyCommunity.studycomment.service;

import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.service.MemberService;
import StudyCommunity.study.entity.Study;
import StudyCommunity.study.service.StudyService;
import StudyCommunity.studycomment.dto.StudyCommentResponseDto;
import StudyCommunity.studycomment.entity.StudyComment;
import StudyCommunity.studycomment.mapper.StudyCommentMapper;
import StudyCommunity.studycomment.repository.StudyCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudyCommentService {

    private final StudyCommentRepository studyCommentRepository;
    private final MemberService memberService;
    private final StudyService studyService;
    private final StudyCommentMapper studyCommentMapper;

    public StudyCommentService(StudyCommentRepository studyCommentRepository, MemberService memberService,
                               StudyService studyService, StudyCommentMapper studyCommentMapper) {
        this.studyCommentRepository = studyCommentRepository;
        this.memberService = memberService;
        this.studyService = studyService;
        this.studyCommentMapper = studyCommentMapper;
    }

    // 댓글 등록
    public StudyComment createStudyComment(StudyComment studyComment) {

        verifyStudyComment(studyComment);

        return studyCommentRepository.save(studyComment);
    }

    // 댓글 수정
    public StudyComment updateStudyComment(StudyComment studyComment) {

        StudyComment findStudyComment = findverifyStudyComment(studyComment.getStudyCommentId());

        findStudyComment.setContent(studyComment.getContent());

        return studyCommentRepository.save(findStudyComment);
    }

    // 댓글 조회
    public StudyComment findStudyComment(long studyCommentId) {
        StudyComment findStudyComment = studyCommentRepository.findById(studyCommentId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.STUDY_COMMENT_NOT_FOUND));

        return findStudyComment;
    }

    // 모든 댓글 조회
    public List<StudyComment> getAllStudyComments() {
        return studyCommentRepository.findAll();
    }

    public List<StudyCommentResponseDto> findComments() {
        List<StudyComment> comments = studyCommentRepository.findAll();
        return studyCommentMapper.studyCommentToStudyCommentResponseDtos(comments);
    }

    // 한 게시물에 달린 댓글들 찾기
    public List<StudyCommentResponseDto> findCommentsByStudyId(long studyId) {
        List<StudyCommentResponseDto> studyCommentResponseDtos = findComments();
        return studyCommentResponseDtos.stream().filter(d -> d.getStudyId() == studyId).collect(Collectors.toList());
    }

    // 댓글 삭제
    public void deleteStudyComment(long studyCommentId) {
        studyCommentRepository.deleteById(studyCommentId);
    }


    // 댓글을 등록하기 위한 검증
    public void verifyStudyComment(StudyComment studyComment) {

        // member 존재하는지 확인
        Member member = studyComment.getMember();
        if (member == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND); // 적절한 예외 처리 추가
        }

        // member가 null이 아닌 경우에만 setId 메서드를 호출하여 memberId 설정
        studyComment.setMember(memberService.findMember(member.getMemberId()));


        // study 존재하는지 확인
        Study study = studyComment.getStudy();
        if (study == null) {
            throw new BusinessLogicException(ExceptionCode.STUDY_NOT_FOUND); // 적절한 예외 처리 추가
        }

        // study가 null이 아닌 경우에만 setId 메서드를 호출하여 studyId 설정
        studyComment.setStudy(studyService.findStudy(study.getStudyId()));

    }

    // Comment 존재하는지 확인
    public StudyComment findverifyStudyComment(long studyCommentId) {

        Optional<StudyComment> studyComment = studyCommentRepository.findById(studyCommentId);

        StudyComment findStudyComment = studyComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.STUDY_COMMENT_NOT_FOUND));

        return findStudyComment;
    }

}
