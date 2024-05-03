package StudyCommunity.studycomment.entity;

import StudyCommunity.audit.Auditable;
import StudyCommunity.member.entity.Member;
import StudyCommunity.study.entity.Study;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "STUDY_COMMENT")
public class StudyComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyCommentId;

    @Column
    private String content;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

}
