package StudyCommunity.study.dto;

import StudyCommunity.member.entity.Member;
import StudyCommunity.studycategory.entity.StudyCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyPostDto {

    @Positive
    private long memberId;

    @Positive
    private long studyCategoryId;

    @NotBlank
    private String title;

    @NotBlank
    private String topic;

    @NotBlank
    private String schedule;

    @NotBlank
    private String curriculum;

    @NotBlank
    private String personnel;

    @NotBlank
    private String introduction;

    @NotBlank
    private String precautions;

    @NotBlank
    private String apply;


    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

    public StudyCategory getStudyCategory() {
        StudyCategory studyCategory = new StudyCategory();
        studyCategory.setStudyCategoryId(studyCategoryId);
        return studyCategory;
    }
}
