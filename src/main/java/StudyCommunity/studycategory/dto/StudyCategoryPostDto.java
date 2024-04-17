package StudyCommunity.studycategory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyCategoryPostDto {

    private String categoryName;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
