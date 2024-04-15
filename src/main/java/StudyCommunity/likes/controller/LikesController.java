package StudyCommunity.likes.controller;

import StudyCommunity.likes.service.LikesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("/{memberId}/{postId}/toggle")
    public long toggleLike(@PathVariable Long memberId, @PathVariable Long postId) {
        return likesService.toggleLike(memberId, postId);
    }

    @GetMapping("/{postId}/count")
    public long getLikesCount(@PathVariable Long postId) {
        return likesService.getLikesCount(postId);
    }

}
