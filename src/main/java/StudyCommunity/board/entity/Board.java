package StudyCommunity.board.entity;

import StudyCommunity.audit.Auditable;
import StudyCommunity.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "BOARD")
public class Board extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

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
    @Getter
    public enum BoardStatus {
        QUESTION_ANSWER_POST("질문&답변 게시글"),
        THINKING_POST("고민 게시글"),
        STUDY_POST("스터디 게시글");

        private final String status;

        BoardStatus(String status) {
            this.status = status;
        }

    }
}
