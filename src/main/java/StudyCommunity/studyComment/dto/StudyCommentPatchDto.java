package StudyCommunity.studyComment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyCommentPatchDto {

    private long studyCommentId;

    @NotBlank
    private String content;

}
