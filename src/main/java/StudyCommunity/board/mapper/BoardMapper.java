package StudyCommunity.board.mapper;

import StudyCommunity.board.dto.BoardPatchDto;
import StudyCommunity.board.dto.BoardPostDto;
import StudyCommunity.board.dto.BoardResponseDto;
import StudyCommunity.board.entity.Board;
import StudyCommunity.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    //Board boardPostDtoToBoard(BoardPostDto requestBody);
    default Board boardPostDtoToBoard(BoardPostDto boardPostDto) {

        Member member = new Member();
        Board board = new Board();

        member.setMemberId(member.getMemberId());

        board.setMember(boardPostDto.getMember());
        board.setTitle(boardPostDto.getTitle());
        board.setContent(boardPostDto.getContent());
        board.setHashTag(boardPostDto.getHashTag());
        board.setBoardStatus(boardPostDto.getBoardStatus());

        return board;
    }

    //Board boardPatchDtoToBoard(BoardPatchDto requestBody);

    default Board boardPatchDtoToBoard(BoardPatchDto boardPatchDto) {

        Board board = new Board();

        board.setBoardId(boardPatchDto.getBoardId());
        board.setTitle(boardPatchDto.getTitle());
        board.setContent(boardPatchDto.getContent());
        board.setHashTag(boardPatchDto.getHashTag());

        return board;
    }

    //BoardResponseDto boardToBoardResponseDto(Board board);
    default BoardResponseDto boardToBoardResponseDto(Board board) {

        BoardResponseDto boardResponseDto = new BoardResponseDto();

        boardResponseDto.setMemberId(board.getMember().getMemberId());

        boardResponseDto.setBoardId(board.getBoardId());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setContent(board.getContent());
        boardResponseDto.setHashTag(board.getHashTag());
        boardResponseDto.setBoardStatus(board.getBoardStatus());
        boardResponseDto.setCreatedAt(board.getCreatedAt());
        boardResponseDto.setModifiedAt(board.getLastModifiedAt());

        return boardResponseDto;
    }

    List<BoardResponseDto> boardsToBoardResponseDtos(List<Board> boards);

}
