package StudyCommunity.postcomment.dto;

import StudyCommunity.member.entity.Member;
import StudyCommunity.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentPostDto {

    @Positive
    private long memberId;

    @Positive
    private long postId;

    @NotBlank
    private String content;


    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;


    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

    public Post getPost() {
        Post post = new Post();
        post.setPostId(postId);
        return post;
    }

}
