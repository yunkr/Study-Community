package StudyCommunity.post.entity;

import StudyCommunity.audit.Auditable;
import StudyCommunity.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "POST")
public class Post extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private String title;

    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostTag> postTags = new HashSet<>();

    // 게시글 상태
    @Getter
    public enum PostStatus {
        QUESTION_ANSWER_POST("질문&답변 게시글"),
        THINKING_POST("고민 게시글");

        private final String status;

        PostStatus(String status) {
            this.status = status;
        }

    }
}
