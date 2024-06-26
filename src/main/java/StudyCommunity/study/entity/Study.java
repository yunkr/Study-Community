package StudyCommunity.study.entity;

import StudyCommunity.audit.Auditable;
import StudyCommunity.member.entity.Member;
import StudyCommunity.studyTag.StudyTag;
import StudyCommunity.studycategory.entity.StudyCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "STUDY")
public class Study extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;

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

    // 조회수
    @Column(columnDefinition = "bigint default 0", nullable = false)
    private long viewCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Study.StudyStatus studyStatus = StudyStatus.Study_REGISTRATION;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "study_category")
    private StudyCategory studyCategory;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private Set<StudyTag> studyTags = new HashSet<>();

    // 스터디 상태
    @Getter
    public enum StudyStatus {
        Study_REGISTRATION("스터디 등록"),
        Study_DELETE("스터디 삭제");

        private final String status;

        StudyStatus(String status) {
            this.status = status;
        }
    }

}
