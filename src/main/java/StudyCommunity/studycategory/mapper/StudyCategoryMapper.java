package StudyCommunity.studycategory.mapper;

import StudyCommunity.studycategory.dto.StudyCategoryPostDto;
import StudyCommunity.studycategory.dto.StudyCategoryResponseDto;
import StudyCommunity.studycategory.entity.StudyCategory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudyCategoryMapper {

    default StudyCategory studyCategoryPostDtoStudyCategory(StudyCategoryPostDto studyCategoryPostDto) {

        StudyCategory studyCategory = new StudyCategory();

        studyCategory.setStudyCategoryName(studyCategoryPostDto.getCategoryName());

        return studyCategory;
    }

    default StudyCategoryResponseDto studyCategoryToStudyCategoryResponseDto(StudyCategory studyCategory) {

        StudyCategoryResponseDto studyCategoryResponseDto = new StudyCategoryResponseDto();

        studyCategoryResponseDto.setCategoryId(studyCategory.getStudyCategoryId());
        studyCategoryResponseDto.setCategoryName(studyCategory.getStudyCategoryName());

        studyCategoryResponseDto.setCreatedAt(studyCategory.getCreatedAt());
        studyCategoryResponseDto.setLastModifiedAt(studyCategory.getLastModifiedAt());

        return studyCategoryResponseDto;
    }
}
