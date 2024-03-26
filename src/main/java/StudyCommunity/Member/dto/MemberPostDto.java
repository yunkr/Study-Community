package StudyCommunity.Member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberPostDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

}
