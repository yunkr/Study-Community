package StudyCommunity.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyResponseDto {

    private long memberId;
    private long studyCategoryId;
    private long studyId;

    private String title;
    private String topic;
    private String schedule;
    private String curriculum;
    private String personnel;
    private String introduction;
    private String precautions;
    private String apply;

    private long viewCount;

    private Set<String> tags;
    private String studyCategoryName;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
