package StudyCommunity.board.dto;

import StudyCommunity.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardPostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String hashTag;

    // 게시글 상태
    @NotBlank
    private Board.BoardStatus boardStatus;


    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

//    private String createdBy;
//    private String modifiedBy;

}
