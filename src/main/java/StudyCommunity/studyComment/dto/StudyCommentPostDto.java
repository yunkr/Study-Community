package StudyCommunity.studyComment.dto;

import StudyCommunity.member.entity.Member;
import StudyCommunity.study.entity.Study;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyCommentPostDto {

    @Positive
    private long memberId;

    @Positive
    private long studyId;

    @NotBlank
    private String content;


    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;


    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

    public Study getStudy() {
        Study study = new Study();
        study.setStudyId(studyId);
        return study;
    }

}
