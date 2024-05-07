package StudyCommunity.member.dto;

import StudyCommunity.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CustomMemberDto {

    private Long memberId;
    private String email;
    private String password;
    private String nickname;

    private LocalDateTime createAt;
    private LocalDateTime lastModifiedAt;

    public static void from(Member entity) {
        new CustomMemberDto(
                entity.getMemberId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getNickname(),
                entity.getCreatedAt(),
                entity.getLastModifiedAt()
        );
    }

    // from : factory method 파라미터로 넘어온 값들을 해당 클래스의 인스턴스로 변환할 때 사용한다.

}
