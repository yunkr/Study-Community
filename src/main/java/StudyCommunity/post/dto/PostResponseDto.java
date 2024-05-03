package StudyCommunity.post.dto;

import StudyCommunity.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

//    private long memberId;

    private long postId;

    private String title;

    private String content;

    private long viewCount;

    private Set<String> tags;

//    // 게시글 상태
//    private Post.PostStatus postStatus;


    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
