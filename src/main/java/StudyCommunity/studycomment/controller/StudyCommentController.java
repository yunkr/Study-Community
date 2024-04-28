package StudyCommunity.studycomment.controller;

import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.postcomment.dto.PostCommentResponseDto;
import StudyCommunity.studycomment.dto.StudyCommentPatchDto;
import StudyCommunity.studycomment.dto.StudyCommentPostDto;
import StudyCommunity.studycomment.dto.StudyCommentResponseDto;
import StudyCommunity.studycomment.entity.StudyComment;
import StudyCommunity.studycomment.mapper.StudyCommentMapper;
import StudyCommunity.studycomment.service.StudyCommentService;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/studyComments")
public class StudyCommentController {

    private final static String STUDY_COMMENT_DEFAULT_URL = "/studyComments";

    private final StudyCommentService studyCommentService;
    private final StudyCommentMapper studyCommentMapper;

    public StudyCommentController(StudyCommentService studyCommentService, StudyCommentMapper studyCommentMapper) {
        this.studyCommentService = studyCommentService;
        this.studyCommentMapper = studyCommentMapper;
    }


    // 댓글 등록(Post)
    @PostMapping
    public ResponseEntity<?> postStudyComment(@Valid @RequestBody StudyCommentPostDto requestBody) {
        StudyComment studyComment = studyCommentService.createStudyComment(studyCommentMapper.studyCommentPostDtoToStudyComment(requestBody));
        URI location = UriCreator.createUri(STUDY_COMMENT_DEFAULT_URL, studyComment.getStudyCommentId());

        return ResponseEntity.created(location).build();
    }

    // 댓글 수정(Patch)
    @PatchMapping("/{studyComment-id}")
    public ResponseEntity<?> patchStudyComment(@PathVariable("studyComment-id") @Positive long studyCommentId,
                                          @Valid @RequestBody StudyCommentPatchDto requestBody) {

        requestBody.setStudyCommentId(studyCommentId);
        StudyComment studyComment = studyCommentService.updateStudyComment(studyCommentMapper.studyCommentPatchDtoToStudyComment(requestBody));
        StudyCommentResponseDto response = studyCommentMapper.studyCommentToStudyCommentResponseDto(studyComment);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 댓글 찾기(Get)
    @GetMapping("/{studyComment-id}")
    public ResponseEntity<?> getStudyComment(@PathVariable("studyComment-id") @Positive long studyCommentId) {
        StudyComment studyComment = studyCommentService.findStudyComment(studyCommentId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(studyCommentMapper.studyCommentToStudyCommentResponseDto(studyComment))
                , HttpStatus.OK);
    }

    // 모든 댓글 찾기(Get)
    @GetMapping
    public ResponseEntity<?> getStudyComments() {
        List<StudyComment> studyComments = studyCommentService.getAllStudyComments();

        return new ResponseEntity<>(studyComments, HttpStatus.OK);
    }

    // 한 게시물에 달린 댓글들 찾기(Get)
    @GetMapping("study/{study-id}")
    public ResponseEntity<?> getCommentsByStudyId(@PathVariable("study-id") @Positive long studyId) {

        List<StudyCommentResponseDto> response = studyCommentService.findCommentsByStudyId(studyId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 댓글 삭제(Delete)
    @DeleteMapping("/{studyComment-id}")
    public ResponseEntity<?> deleteStudyComment(@PathVariable("studyComment-id") @Positive long studyCommentId) {
        studyCommentService.deleteStudyComment(studyCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
