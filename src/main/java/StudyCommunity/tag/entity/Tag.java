package StudyCommunity.tag.entity;

import StudyCommunity.audit.Auditable;
import StudyCommunity.post.entity.PostTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TAG")
public class Tag extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(nullable = false)
    private String tagName;

    @OneToMany(mappedBy = "tag")
    private Set<PostTag> postTags = new HashSet<>();

}
