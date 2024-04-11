package StudyCommunity.studycategory.entity;

import StudyCommunity.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "STUDY_CATEGORY")
public class StudyCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyCategoryId;

    @Column(nullable = false)
    private String studyCategoryName;

}
