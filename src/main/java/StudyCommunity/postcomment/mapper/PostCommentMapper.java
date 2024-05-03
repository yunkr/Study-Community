package StudyCommunity.postcomment.mapper;

import StudyCommunity.member.entity.Member;
import StudyCommunity.postcomment.dto.PostCommentPatchDto;
import StudyCommunity.postcomment.dto.PostCommentPostDto;
import StudyCommunity.postcomment.dto.PostCommentResponseDto;
import StudyCommunity.postcomment.entity.PostComment;
import StudyCommunity.study.entity.Study;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PostCommentMapper {

    default PostComment postCommentPostDtoToPostComment(PostCommentPostDto postCommentPostDto) {

        Member member = new Member();
        Study study = new Study();
        PostComment postComment = new PostComment();

        member.setMemberId(member.getMemberId());
        study.setStudyId(study.getStudyId());

        postComment.setMember(postCommentPostDto.getMember());
        postComment.setPost(postCommentPostDto.getPost());

        postComment.setContent(postCommentPostDto.getContent());

        return postComment;
    }

    default PostComment postCommentPatchDtoToPostComment(PostCommentPatchDto postCommentPatchDto) {

        PostComment postComment = new PostComment();

        postComment.setPostCommentId(postCommentPatchDto.getPostCommentId());

        postComment.setContent(postCommentPatchDto.getContent());

        return postComment;
    }

    default PostCommentResponseDto postCommentToPostCommentResponseDto(PostComment postComment) {

        PostCommentResponseDto postCommentResponseDto = new PostCommentResponseDto();

        postCommentResponseDto.setMemberId(postComment.getMember().getMemberId());
        postCommentResponseDto.setPostId(postComment.getPost().getPostId());

        postCommentResponseDto.setPostCommentId(postComment.getPostCommentId());

        postCommentResponseDto.setContent(postComment.getContent());

        postCommentResponseDto.setCreatedAt(postComment.getCreatedAt());
        postCommentResponseDto.setLastModifiedAt(postComment.getLastModifiedAt());

        return postCommentResponseDto;
    }

    default List<PostCommentResponseDto> postCommentToPostCommentResponseDtos(List<PostComment> postComments) {
        return postComments.stream()
                .map(this::postCommentToPostCommentResponseDto)
                .collect(Collectors.toList());
    }

}
