package StudyCommunity.board.mapper;

import StudyCommunity.board.dto.BoardPatchDto;
import StudyCommunity.board.dto.BoardPostDto;
import StudyCommunity.board.dto.BoardResponseDto;
import StudyCommunity.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    Board boardPostDtoToBoard(BoardPostDto requestBody);

    Board boardPatchDtoToBoard(BoardPatchDto requestBody);

    BoardResponseDto boardToBoardResponseDto(Board board);

    List<BoardResponseDto> boardsToBoardResponseDtos(List<Board> boards);

}
