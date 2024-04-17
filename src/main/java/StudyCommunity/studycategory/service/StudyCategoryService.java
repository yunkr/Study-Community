package StudyCommunity.studycategory.service;

import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.studycategory.entity.StudyCategory;
import StudyCommunity.studycategory.respository.StudyCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyCategoryService {

    private final StudyCategoryRepository studyCategoryRepository;

    public StudyCategoryService(StudyCategoryRepository studyCategoryRepository) {
        this.studyCategoryRepository = studyCategoryRepository;
    }

    public StudyCategory createStudyCategory(StudyCategory studyCategory) {

        return studyCategoryRepository.save(studyCategory);
    }

    public StudyCategory findStudyCategory(long studyCategoryId) {

        StudyCategory findStudyCategory = studyCategoryRepository.findById(studyCategoryId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.STUDY_CATEGORY_NOT_FOUND));

        return findStudyCategory;
    }

    public List<StudyCategory> findAllStudyCategory() {

        return studyCategoryRepository.findAll();
    }
}
