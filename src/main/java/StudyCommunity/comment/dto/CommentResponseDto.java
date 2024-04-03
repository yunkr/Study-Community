package StudyCommunity.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private long commentId;

    private String content;


    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

//    private String createdBy;
//    private String modifiedBy;
}
