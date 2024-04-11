package StudyCommunity.note.dto;

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
public class NotePatchDto {

    @Positive
    private long noteId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
