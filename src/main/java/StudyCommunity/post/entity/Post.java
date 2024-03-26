package StudyCommunity.post.entity;

import StudyCommunity.Member.entity.Member;
import StudyCommunity.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column
    private String hashTag;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    // 게시글 상태
    public enum PostStatus {
        QUESTION_ANSWER_POST("질문&답변 게시글"),
        THINKING_POST("고민 게시글"),
        STUDY_POST("스터디 게시글");

        @Getter
        private final String status;

        PostStatus(String status) {
            this.status = status;
        }

    }
}
