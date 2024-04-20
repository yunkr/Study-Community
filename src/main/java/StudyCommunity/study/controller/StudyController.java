package StudyCommunity.study.controller;

import StudyCommunity.dto.MultiResponseDto;
import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.post.entity.Post;
import StudyCommunity.study.dto.StudyPatchDto;
import StudyCommunity.study.dto.StudyPostDto;
import StudyCommunity.study.dto.StudyResponseDto;
import StudyCommunity.study.entity.Study;
import StudyCommunity.study.mapper.StudyMapper;
import StudyCommunity.study.service.StudyService;
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
@RequestMapping("/studies")
@Validated
public class StudyController {

    private final static String STUDY_DEFAULT_URL = "/studies";

    private final StudyMapper studyMapper;
    private final StudyService studyService;

    public StudyController(StudyMapper studyMapper, StudyService studyService) {
        this.studyMapper = studyMapper;
        this.studyService = studyService;
    }

    // 스터디 등록(Post)
    @PostMapping
    public ResponseEntity<?> postStudy(@Valid @RequestBody StudyPostDto requestBody) {
        Study study = studyService.createStudy(studyMapper.studyPostDtoToStudy(requestBody));
        URI location = UriCreator.createUri(STUDY_DEFAULT_URL, study.getStudyId());

        return ResponseEntity.created(location).build();
    }

    // 스터디 수정(Patch)
    @PatchMapping("/{study-id}")
    public ResponseEntity<?> patchStudy(@PathVariable("study-id") @Positive long studyId,
                                              @Valid @RequestBody StudyPatchDto requestBody) {

        requestBody.setStudyId(studyId);
        Study study = studyService.updateStudy(studyMapper.studyPatchDtoToStudy(requestBody));
        StudyResponseDto response = studyMapper.studyToStudyResponseDto(study);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 스터디 조회(Get)
    @GetMapping("/{study-id}")
    public ResponseEntity<?> getStudy(@PathVariable("study-id") @Positive long studyId) {

        Study study = studyService.findStudy(studyId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(studyMapper.studyToStudyResponseDto(study))
                , HttpStatus.OK);
    }

    // 모든 스터디 조회(Get)
    @GetMapping
    public ResponseEntity<?> getStudies(@RequestParam int page, @RequestParam int size) {
        Page<Study> pageStudies = studyService.findAllStudies(page-1, size);
        List<Study> studies = pageStudies.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(studyMapper.studyToStudyResponseDtos(studies), pageStudies)
                , HttpStatus.OK);
    }

    // 스터디 삭제(Delete)
    @DeleteMapping("/{study-id}")
    public ResponseEntity<?> deleteStudy(@PathVariable("study-id") @Positive long studyId) {
        studyService.deleteStudy(studyId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* 검색 기능 - pagination(페이지네이션) */
    @GetMapping("/search")
    public ResponseEntity<?> searchStudiesByTitleContaining(
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "1") @Positive int page,
            @RequestParam(defaultValue = "10") @Positive int size,
            @RequestParam(defaultValue = "descending") String sort) {

        boolean ascendingSort = sort.equalsIgnoreCase("ascending");

        Page<Study> studiesPage = studyService.searchStudies(page - 1, size, searchKeyword, ascendingSort);
        List<Study> content = studiesPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(studyMapper.studyToStudyResponseDtos(content), studiesPage),
                HttpStatus.OK
        );
    }

}
