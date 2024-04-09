package StudyCommunity.study.controller;

import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.study.dto.StudyPatchDto;
import StudyCommunity.study.dto.StudyPostDto;
import StudyCommunity.study.dto.StudyResponseDto;
import StudyCommunity.study.entity.Study;
import StudyCommunity.study.mapper.StudyMapper;
import StudyCommunity.study.service.StudyService;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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

    // 온라인 스터디 등록(Post)
    @PostMapping
    public ResponseEntity<?> postStudy(@Valid @RequestBody StudyPostDto requestBody) {
        Study study = studyService.createStudy(studyMapper.studyPostDtoToStudy(requestBody));
        URI location = UriCreator.createUri(STUDY_DEFAULT_URL, study.getStudyId());

        return ResponseEntity.created(location).build();
    }

    // 온라인 스터디 수정(Patch)
    @PatchMapping("/{study-id}")
    public ResponseEntity<?> patchStudy(@PathVariable("study-id") @Positive long studyId,
                                              @Valid @RequestBody StudyPatchDto requestBody) {

        requestBody.setStudyId(studyId);
        Study study = studyService.updateStudy(studyMapper.studyPatchDtoToStudy(requestBody));
        StudyResponseDto response = studyMapper.studyToStudyResponseDto(study);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 온라인 스터디 조회(Get)
    @GetMapping("/{study-id}")
    public ResponseEntity<?> getStudy(@PathVariable("study-id") @Positive long studyId) {

        Study study = studyService.findStudy(studyId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(studyMapper.studyToStudyResponseDto(study))
                , HttpStatus.OK);
    }

    // 모든 온라인 스터디 조회(Get)
    @GetMapping
    public ResponseEntity<?> getStudies() {
        List<Study> studies = studyService.getAllStudies();

        return new ResponseEntity<>(studies, HttpStatus.OK);
    }

    // 온라인 스터디 삭제(Delete)
    @DeleteMapping("/{study-id}")
    public ResponseEntity<?> deleteStudy(@PathVariable("study-id") @Positive long studyId) {
        studyService.deleteStudy(studyId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
