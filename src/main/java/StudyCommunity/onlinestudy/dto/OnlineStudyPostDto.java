package StudyCommunity.onlinestudy.dto;

import StudyCommunity.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OnlineStudyPostDto {

    @Positive
    private long memberId;

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
}
