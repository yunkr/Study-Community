package StudyCommunity.studycategory.controller;

import StudyCommunity.studycategory.dto.StudyCategoryPostDto;
import StudyCommunity.studycategory.entity.StudyCategory;
import StudyCommunity.studycategory.mapper.StudyCategoryMapper;
import StudyCommunity.studycategory.service.StudyCategoryService;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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

}
