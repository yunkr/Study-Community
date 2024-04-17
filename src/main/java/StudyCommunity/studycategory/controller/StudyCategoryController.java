package StudyCommunity.studycategory.controller;

import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.studycategory.dto.StudyCategoryPostDto;
import StudyCommunity.studycategory.entity.StudyCategory;
import StudyCommunity.studycategory.mapper.StudyCategoryMapper;
import StudyCommunity.studycategory.service.StudyCategoryService;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/studyCategories")
public class StudyCategoryController {

    private final static String STUDY_CATEGORY_DEFAULT_URL = "/studyCategories";
    private final StudyCategoryMapper studyCategoryMapper;
    private final StudyCategoryService studyCategoryService;

    public StudyCategoryController(StudyCategoryMapper studyCategoryMapper, StudyCategoryService studyCategoryService) {
        this.studyCategoryMapper = studyCategoryMapper;
        this.studyCategoryService = studyCategoryService;
    }

    @PostMapping
    public ResponseEntity<?> postStudyCategory(@Valid @RequestBody StudyCategoryPostDto requestBody) {
        StudyCategory studyCategory = studyCategoryService.createStudyCategory(
                studyCategoryMapper.studyCategoryPostDtoStudyCategory(requestBody));

        URI location = UriCreator.createUri(STUDY_CATEGORY_DEFAULT_URL, studyCategory.getStudyCategoryId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{studyCategory-id}")
    public ResponseEntity<?> getStudyCategory(@PathVariable("studyCategory-id") @Positive long studyCategoryId) {

        StudyCategory studyCategory = studyCategoryService.findStudyCategory(studyCategoryId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(studyCategoryMapper.studyCategoryToStudyCategoryResponseDto(studyCategory))
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getStudyCategories() {

        List<StudyCategory> studyCategories = studyCategoryService.findAllStudyCategory();

        return new ResponseEntity<>(studyCategories, HttpStatus.OK);
    }

}
