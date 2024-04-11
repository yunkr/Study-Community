package StudyCommunity.note.dto;

import StudyCommunity.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotePostDto {

    @Positive
    private long memberId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

}
