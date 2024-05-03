package StudyCommunity.postcomment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentResponseDto {

//    private long memberId;
    private long postId;

    private long postCommentId;

    private String content;


    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
