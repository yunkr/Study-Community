package StudyCommunity.note.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponseDto {

    private long memberId;
    private long noteId;

    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
