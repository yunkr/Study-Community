package StudyCommunity.tag.service;

import StudyCommunity.post.dto.PostPostDto;
import StudyCommunity.post.entity.Post;
import StudyCommunity.post.entity.PostTag;
import StudyCommunity.post.repository.PostTagRepository;
import StudyCommunity.tag.entity.Tag;
import StudyCommunity.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TagService {

    private final TagRepository tagRepository;
    //private final PostTagRepository postTagRepository;


    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void createPostTag(Post post, PostPostDto postPostDto) {
        Set<String> tags = new HashSet<>(postPostDto.getTags());
        tags.forEach(tagName -> {
            PostTag postTag = new PostTag();
            Tag tag = createTag(tagName);
            postTag.addPost(post);
            postTag.addTag(tag);
        });
    }

    public Tag createTag(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName);

        return tagRepository.save(tag);
    }
}
