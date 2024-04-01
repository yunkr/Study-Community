package StudyCommunity.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberPatchDto {

    @NotBlank
    private long memberId;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nickname;

}
