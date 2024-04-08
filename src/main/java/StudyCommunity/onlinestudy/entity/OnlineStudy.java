package StudyCommunity.onlinestudy.entity;

import StudyCommunity.audit.Auditable;
import StudyCommunity.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ONLINE_STUDY")
public class OnlineStudy extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long onlineStudyId;

    // 제목
    @Column(length = 250, nullable = false)
    private String title;

    // 주제
    @Column(length = 1000, nullable = false)
    private String topic;

    // 일정
    @Column(length = 250, nullable = false)
    private String schedule;

    // 커리큘럼
    @Column(length = 3000, nullable = false)
    private String curriculum;

    // 인원
    @Column(length = 100, nullable = false)
    private String personnel;

    // 소개
    @Column(length = 1000, nullable = false)
    private String introduction;

    // 주의사항
    @Column(length = 1000, nullable = false)
    private String precautions;

    // 지원방법
    @Column(length = 2500, nullable = false)
    private String apply;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
