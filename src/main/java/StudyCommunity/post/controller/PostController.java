package StudyCommunity.post.controller;

import StudyCommunity.post.dto.PostPatchDto;
import StudyCommunity.post.dto.PostPostDto;
import StudyCommunity.post.dto.PostResponseDto;
import StudyCommunity.post.entity.Post;
import StudyCommunity.post.mapper.PostMapper;
import StudyCommunity.post.service.PostService;
import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final static String POST_DEFAULT_URL = "/posts";

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    // 게시글 등록(Post)
    @PostMapping
    public ResponseEntity<?> postPost(@Valid @RequestBody PostPostDto requestBody) {
        Post post = postService.createPost(postMapper.postPostDtoToPost(requestBody));
        URI location = UriCreator.createUri(POST_DEFAULT_URL, post.getPostId());

        return ResponseEntity.created(location).build();
    }

    // 게시글 수정(Patch)
    @PatchMapping("/{post-id}")
    public ResponseEntity<?> patchPost(@PathVariable("post-id") @Positive long postId,
                                        @Valid @RequestBody PostPatchDto requestBody) {
        requestBody.setPostId(postId);
        Post post = postService.updatePost(postMapper.postPatchDtoToPost(requestBody));
        PostResponseDto response = postMapper.postToPostResponseDto(post);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 게시글 조회(Get)
    @GetMapping("/{post-id}")
    public ResponseEntity<?> getPost(@PathVariable("post-id") @Positive long postId) {
        Post post = postService.findPost(postId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(postMapper.postToPostResponseDto(post))
                , HttpStatus.OK);
    }

    // 모든 게시글 조회(Get)
    @GetMapping
    public ResponseEntity<?> getPosts() {
        List<Post> posts = postService.getAllPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // 게시글 삭제(Delete)
    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> deletePost(@PathVariable("post-id") @Positive long postId) {
        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
