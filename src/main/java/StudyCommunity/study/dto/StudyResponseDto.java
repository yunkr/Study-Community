package StudyCommunity.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyResponseDto {

    private long memberId;
    private long studyId;

    private String title;
    private String topic;
    private String schedule;
    private String curriculum;
    private String personnel;
    private String introduction;
    private String precautions;
    private String apply;


    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
