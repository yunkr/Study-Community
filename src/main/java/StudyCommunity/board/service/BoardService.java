package StudyCommunity.board.service;

import StudyCommunity.board.entity.Board;
import StudyCommunity.board.repository.BoardRepository;
import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    public BoardService(BoardRepository boardRepository, MemberService memberService) {
        this.boardRepository = boardRepository;
        this.memberService = memberService;
    }


    // 게시글 등록
    public Board createBoard(Board board) {

        verifyMember(board);

        return boardRepository.save(board);
    }

    // 게시글 수정
    public Board updateBoard(Board board) {

        Board findBoard = findverifyBoard(board.getBoardId());

        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        findBoard.setHashTag(board.getHashTag());

        return boardRepository.save(findBoard);
    }

    // 게시글 조회
    public Board findBoard(long boardId) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));

        return findBoard;
    }

    // 모든 게시글 조회
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // 게시글 삭제
    public void deleteBoard(long boardId) {
        boardRepository.deleteById(boardId);
    }


    // member 존재하는지 확인
    public void verifyMember(Board board) {

        Member member = memberService.findMember(board.getMember().getMemberId());
        board.setMember(member);
    }

    // Board 존재하는지 확인
    public Board findverifyBoard(long boardId) {

        Optional<Board> board = boardRepository.findById(boardId);

        Board findBoard = board.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));

        return findBoard;
    }
}