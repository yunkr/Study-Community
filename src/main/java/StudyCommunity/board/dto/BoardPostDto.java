package StudyCommunity.board.dto;

import StudyCommunity.board.entity.Board;
import StudyCommunity.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardPostDto {

    @Positive
    private long memberId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String hashTag;

    // 게시글 상태
    private Board.BoardStatus boardStatus;


    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

//    private String createdBy;
//    private String modifiedBy;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

}
