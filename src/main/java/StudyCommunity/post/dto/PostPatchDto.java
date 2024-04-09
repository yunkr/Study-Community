package StudyCommunity.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPatchDto {

    @Positive
    private long postId;

    @NotBlank
    private String title;

    @NotBlank(message = "내용을 작성해주세요.")
    private String content;

    private String hashTag;

}
