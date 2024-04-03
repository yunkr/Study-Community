package StudyCommunity.board.dto;

import StudyCommunity.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardResponseDto {

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
