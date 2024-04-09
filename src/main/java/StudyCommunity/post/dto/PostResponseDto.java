package StudyCommunity.post.dto;

import StudyCommunity.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private long memberId;

    private long postId;

    private String title;

    private String content;

    private String hashTag;

    // 게시글 상태
    private Post.PostStatus postStatus;


    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

//    private String createdBy;
//    private String modifiedBy;


}
