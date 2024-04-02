package StudyCommunity.comment.mapper;

import StudyCommunity.comment.dto.CommentPatchDto;
import StudyCommunity.comment.dto.CommentPostDto;
import StudyCommunity.comment.dto.CommentResponseDto;
import StudyCommunity.comment.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    Comment commentPostDtoToComment(CommentPostDto requestBody);
    Comment commentPatchDtoToComment(CommentPatchDto requestBody);
    CommentResponseDto commentToCommentResponseDto(Comment comment);
    List<CommentResponseDto> commentsToCommentResponseDtos(Comment comment);
}
