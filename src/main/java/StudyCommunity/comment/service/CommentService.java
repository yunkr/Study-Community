package StudyCommunity.comment.service;

import StudyCommunity.board.entity.Board;
import StudyCommunity.board.service.BoardService;
import StudyCommunity.comment.entity.Comment;
import StudyCommunity.comment.repository.CommentRepository;
import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final BoardService boardService;

    public CommentService(CommentRepository commentRepository, MemberService memberService, BoardService boardService) {
        this.commentRepository = commentRepository;
        this.memberService = memberService;
        this.boardService = boardService;
    }


    // 댓글 등록
    public Comment createComment(Comment comment) {

        verifyComment(comment);

        return commentRepository.save(comment);
    }

    // 댓글 수정
    public Comment updateComment(Comment comment) {

        Comment findComment = findverifyComment(comment.getCommentId());

        findComment.setContent(findComment.getContent());

        return commentRepository.save(comment);
    }

    // 댓글 조회
    public Comment findComment(long commentId) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        return findComment;
    }

    // 모든 댓글 조회
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // 댓글 삭제
    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }


    // 댓글을 등록하기 위한 검증
    public void verifyComment(Comment comment) {

        // member 존재하는지 확인
        Member member = memberService.findMember(comment.getMember().getMemberId());
        comment.setMember(member);

        // board 존재하는지 확인
        Board board = boardService.findBoard(comment.getBoard().getBoardId());
        comment.setBoard(board);
    }

    // Comment 존재하는지 확인
    public Comment findverifyComment(long commentId) {

        Optional<Comment> comment = commentRepository.findById(commentId);

        Comment findComment = comment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        return findComment;
    }

}
