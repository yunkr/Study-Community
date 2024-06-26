package StudyCommunity.post.entity;

import StudyCommunity.postTag.PostTag;
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

    // 조회수
    @Column(columnDefinition = "bigint default 0", nullable = false)
    private long viewCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostStatus postStatus = PostStatus.POST_REGISTRATION;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<PostTag> postTags = new HashSet<>();

    // 게시글 상태
    @Getter
    public enum PostStatus {
        POST_REGISTRATION("질문&답변 게시글 등록"),
        POST_DELETE("질문&답변 게시글 삭제");

        private final String status;

        PostStatus(String status) {
            this.status = status;
        }
    }

}
