package StudyCommunity.tag.service;

import StudyCommunity.tag.dto.TagResponseDto;
import StudyCommunity.tag.entity.Tag;
import StudyCommunity.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagResponseDto> getAllHashTags() {
        List<Tag> tagEntities = tagRepository.findAll();
        return tagEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TagResponseDto convertToDto(Tag tag) {
        TagResponseDto tagResponseDto = new TagResponseDto();
        tagResponseDto.setTagId(tag.getTagId());
        tagResponseDto.setTagName(tag.getTagName());
        return tagResponseDto;
    }
}
