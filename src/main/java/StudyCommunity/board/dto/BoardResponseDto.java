package StudyCommunity.board.dto;

import StudyCommunity.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {

    private long memberId;

    private long boardId;

    private String title;

    private String content;

    private String hashTag;

    // 게시글 상태
    private Board.BoardStatus boardStatus;


    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

//    private String createdBy;
//    private String modifiedBy;


}
