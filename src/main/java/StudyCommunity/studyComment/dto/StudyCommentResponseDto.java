package StudyCommunity.studyComment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyCommentResponseDto {

    private long memberId;
    private long studyId;

    private long studyCommentId;

    private String content;


    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

//    private String createdBy;
//    private String modifiedBy;
}
