package StudyCommunity.member.entity;

import StudyCommunity.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "MEMBER")
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column //(length = 250, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String nickname;

    @Email
    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();


    // 생성자
    public Member(String password, String email, String nickname) {
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

    // OAuth 추가
    public Member(String email) {
        this.email = email;
    }

}
