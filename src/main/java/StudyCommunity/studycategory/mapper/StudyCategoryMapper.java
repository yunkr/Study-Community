package StudyCommunity.studycategory.mapper;

import StudyCommunity.studycategory.dto.StudyCategoryPostDto;
import StudyCommunity.studycategory.entity.StudyCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudyCategoryMapper {

    default StudyCategory studyCategoryPostDtoStudyCategory(StudyCategoryPostDto studyCategoryPostDto) {

        StudyCategory studyCategory = new StudyCategory();

        studyCategory.setStudyCategoryName(studyCategoryPostDto.getCategoryName());

        return studyCategory;
    }
}
