package StudyCommunity.postcomment.controller;

import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.postcomment.dto.PostCommentPatchDto;
import StudyCommunity.postcomment.dto.PostCommentPostDto;
import StudyCommunity.postcomment.dto.PostCommentResponseDto;
import StudyCommunity.postcomment.entity.PostComment;
import StudyCommunity.postcomment.mapper.PostCommentMapper;
import StudyCommunity.postcomment.service.PostCommentService;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/postComments")
public class PostCommentController {

    private final static String POST_COMMENT_DEFAULT_URL = "/postComments";
    private final PostCommentMapper postCommentMapper;
    private final PostCommentService postCommentService;


    public PostCommentController(PostCommentMapper postCommentMapper, PostCommentService postCommentService) {
        this.postCommentMapper = postCommentMapper;
        this.postCommentService = postCommentService;
    }

    // 댓글 등록(Post)
    @PostMapping
    public ResponseEntity<?> postPostComment(@Valid @RequestBody PostCommentPostDto requestBody) {
        PostComment postComment = postCommentService.createPostComment(postCommentMapper.postCommentPostDtoToPostComment(requestBody));
        URI location = UriCreator.createUri(POST_COMMENT_DEFAULT_URL, postComment.getPostCommentId());

        return ResponseEntity.created(location).build();
    }

    // 댓글 수정(Patch)
    @PatchMapping("/{postComment-id}")
    public ResponseEntity<?> patchPostComment(@PathVariable("postComment-id") @Positive long postCommentId,
                                               @Valid @RequestBody PostCommentPatchDto requestBody) {

        requestBody.setPostCommentId(postCommentId);
        PostComment postComment = postCommentService.updatePostComment(postCommentMapper.postCommentPatchDtoToPostComment(requestBody));
        PostCommentResponseDto response = postCommentMapper.postCommentToPostCommentResponseDto(postComment);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 댓글 찾기(Get)
    @GetMapping("/{postComment-id}")
    public ResponseEntity<?> getPostComment(@PathVariable("postComment-id") @Positive long postCommentId) {
        PostComment postComment = postCommentService.findPostComment(postCommentId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(postCommentMapper.postCommentToPostCommentResponseDto(postComment))
                , HttpStatus.OK);
    }

    // 모든 댓글 찾기(Get)
    @GetMapping
    public ResponseEntity<?> getPostComments() {
        List<PostComment> postComments = postCommentService.getAllPostComments();

        return new ResponseEntity<>(postComments, HttpStatus.OK);
    }

    // 댓글 삭제(Delete)
    @DeleteMapping("/{postComment-id}")
    public ResponseEntity<?> deletePostComment(@PathVariable("postComment-id") @Positive long postCommentId) {
        postCommentService.deletePostComment(postCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
