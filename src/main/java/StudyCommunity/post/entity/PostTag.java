package StudyCommunity.post.entity;

import StudyCommunity.audit.Auditable;
import StudyCommunity.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "POST_TAG")
public class PostTag extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postTagId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;


    public void addPost(Post post){
        this.post = post;
        this.post.getPostTags().add(this);
    }

    public void addTag(Tag tag){
        this.tag = tag;
    }

}
