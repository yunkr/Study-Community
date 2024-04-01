package StudyCommunity.board.controller;

import StudyCommunity.board.dto.BoardPatchDto;
import StudyCommunity.board.dto.BoardPostDto;
import StudyCommunity.board.dto.BoardResponseDto;
import StudyCommunity.board.entity.Board;
import StudyCommunity.board.mapper.BoardMapper;
import StudyCommunity.board.service.BoardService;
import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.member.dto.MemberPatchDto;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final static String BOARD_DEFAULT_URL = "/boards";

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    public BoardController(BoardService boardService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }

    // 게시글 등록(Post)
    @PostMapping("/{board-id}")
    public ResponseEntity<?> postBoard(@Valid @RequestBody BoardPostDto requestBody) {
        Board board = boardService.createBoard(boardMapper.boardPostDtoToBoard(requestBody));
        URI location = UriCreator.createUri(BOARD_DEFAULT_URL, board.getBoardId());

        return ResponseEntity.created(location).build();
    }

    // 게시글 수정(Patch)
    @PatchMapping("/{board-id}")
    public ResponseEntity<?> patchBoard(@PathVariable("board-id") @Positive long boardId,
                                        @Valid @RequestBody BoardPatchDto requestBody) {
        requestBody.setBoardId(boardId);
        Board board = boardService.updateBoard(boardMapper.boardPatchDtoToBoard(requestBody));
        BoardResponseDto response = boardMapper.boardToBoardResponseDto(board);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 게시글 조회(Get)
    @GetMapping("/{board-id}")
    public ResponseEntity<?> getBoard(@PathVariable("board-id") @Positive long boardId) {
        Board board = boardService.findBoard(boardId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(boardMapper.boardToBoardResponseDto(board))
                , HttpStatus.OK);
    }

    // 모든 게시글 조회(Get)
    @GetMapping
    public ResponseEntity<?> getBoards() {
        List<Board> boards = boardService.getAllBoards();

        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    // 게시글 삭제(Delete)
    @DeleteMapping("/{board-id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("board-id") @Positive long boardId) {
        boardService.deleteBoard(boardId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
