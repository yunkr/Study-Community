package StudyCommunity.postcomment.entity;

import StudyCommunity.audit.Auditable;
import StudyCommunity.member.entity.Member;
import StudyCommunity.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "POST_COMMENT")
public class PostComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postCommentId;

    @Column
    private String content;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
