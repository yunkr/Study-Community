package StudyCommunity.studyTag;

import StudyCommunity.audit.Auditable;
import StudyCommunity.study.entity.Study;
import StudyCommunity.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "STUDY_TAG")
public class StudyTag extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyTagId;

    @ManyToOne
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

}
