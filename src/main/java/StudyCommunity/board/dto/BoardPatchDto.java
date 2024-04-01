package StudyCommunity.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardPatchDto {

    @NotBlank
    private long boardId;

    @NotBlank
    private String title;

    @NotBlank(message = "내용을 작성해주세요.")
    private String content;

    private String hashTag;

}
