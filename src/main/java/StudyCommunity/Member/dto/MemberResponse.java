package StudyCommunity.Member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private long memberId;

    private String email;

    private String nickname;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

}
