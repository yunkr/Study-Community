package StudyCommunity.tag.controller;

import StudyCommunity.tag.dto.TagResponseDto;
import StudyCommunity.tag.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDto>> getAllHashTags() {
        List<TagResponseDto> hashTags = tagService.getAllHashTags();
        return new ResponseEntity<>(hashTags, HttpStatus.OK);
    }

}
