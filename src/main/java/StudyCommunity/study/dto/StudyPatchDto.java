package StudyCommunity.study.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyPatchDto {

    private long studyId;

    @NotBlank
    private String title;

    @NotBlank
    private String topic;

    @NotBlank
    private String schedule;

    @NotBlank
    private String curriculum;

    @NotBlank
    private String personnel;

    @NotBlank
    private String introduction;

    @NotBlank
    private String precautions;

    @NotBlank
    private String apply;


}
