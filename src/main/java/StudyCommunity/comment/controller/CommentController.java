package StudyCommunity.comment.controller;

import StudyCommunity.comment.dto.CommentPatchDto;
import StudyCommunity.comment.dto.CommentPostDto;
import StudyCommunity.comment.dto.CommentResponseDto;
import StudyCommunity.comment.entity.Comment;
import StudyCommunity.comment.mapper.CommentMapper;
import StudyCommunity.comment.service.CommentService;
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
@RequestMapping("/comments")
public class CommentController {

    private final static String COMMENT_DEFAULT_URL = "/comments";

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }


    // 댓글 등록(Post)
    @PostMapping
    public ResponseEntity<?> postComment(@Valid @RequestBody CommentPostDto requestBody) {
        Comment comment = commentService.createComment(commentMapper.commentPostDtoToComment(requestBody));
        URI location = UriCreator.createUri(COMMENT_DEFAULT_URL, comment.getCommentId());

        return ResponseEntity.created(location).build();
    }

    // 댓글 수정(Patch)
    @PatchMapping("/{comment-id}")
    public ResponseEntity<?> patchComment(@PathVariable("comment-id") @Positive long commentId,
                                          @Valid @RequestBody CommentPatchDto requestBody) {

        requestBody.setCommentId(commentId);
        Comment comment = commentService.updateComment(commentMapper.commentPatchDtoToComment(requestBody));
        CommentResponseDto response = commentMapper.commentToCommentResponseDto(comment);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 댓글 찾기(Get)
    @GetMapping("/{comment-id}")
    public ResponseEntity<?> getComment(@PathVariable("comment-id") @Positive long commentId) {
        Comment comment = commentService.findComment(commentId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(commentMapper.commentToCommentResponseDto(comment))
                , HttpStatus.OK);
    }

    // 모든 댓글 찾기(Get)
    @GetMapping
    public ResponseEntity<?> getComments() {
        List<Comment> comments = commentService.getAllComments();

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // 댓글 삭제(Delete)
    @DeleteMapping("/{comment-id}")
    public ResponseEntity<?> deleteComment(@PathVariable("comment-id") @Positive long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
