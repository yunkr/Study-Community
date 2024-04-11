package StudyCommunity.studycategory.service;

import StudyCommunity.studycategory.entity.StudyCategory;
import StudyCommunity.studycategory.respository.StudyCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class StudyCategoryService {

    private final StudyCategoryRepository studyCategoryRepository;

    public StudyCategoryService(StudyCategoryRepository studyCategoryRepository) {
        this.studyCategoryRepository = studyCategoryRepository;
    }

    public StudyCategory createStudyCategory(StudyCategory studyCategory) {

        return studyCategoryRepository.save(studyCategory);
    }

}
