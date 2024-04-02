package StudyCommunity.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentPatchDto {

    @NotBlank
    private long commentId;

    @NotBlank
    private String content;

}
