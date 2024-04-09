package StudyCommunity.post.mapper;

import StudyCommunity.post.dto.PostPatchDto;
import StudyCommunity.post.dto.PostPostDto;
import StudyCommunity.post.dto.PostResponseDto;
import StudyCommunity.post.entity.Post;
import StudyCommunity.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    default Post postPostDtoToPost(PostPostDto postPostDto) {

        Member member = new Member();
        Post post = new Post();

        member.setMemberId(member.getMemberId());

        post.setMember(postPostDto.getMember());
        post.setTitle(postPostDto.getTitle());
        post.setContent(postPostDto.getContent());
        post.setHashTag(postPostDto.getHashTag());
        post.setPostStatus(postPostDto.getPostStatus());

        return post;
    }

    default Post postPatchDtoToPost(PostPatchDto postPatchDto) {

        Post post = new Post();

        post.setPostId(postPatchDto.getPostId());
        post.setTitle(postPatchDto.getTitle());
        post.setContent(postPatchDto.getContent());
        post.setHashTag(postPatchDto.getHashTag());

        return post;
    }

    default PostResponseDto postToPostResponseDto(Post post) {

        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.setMemberId(post.getMember().getMemberId());

        postResponseDto.setPostId(post.getPostId());
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setContent(post.getContent());
        postResponseDto.setHashTag(post.getHashTag());
        postResponseDto.setPostStatus(post.getPostStatus());
        postResponseDto.setCreatedAt(post.getCreatedAt());
        postResponseDto.setModifiedAt(post.getLastModifiedAt());

        return postResponseDto;
    }

    List<PostResponseDto> postsToPostResponseDtos(List<Post> posts);

}
