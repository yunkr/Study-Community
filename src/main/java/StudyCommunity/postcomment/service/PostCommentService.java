package StudyCommunity.postcomment.service;

import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.service.MemberService;
import StudyCommunity.post.entity.Post;
import StudyCommunity.post.service.PostService;
import StudyCommunity.postcomment.dto.PostCommentResponseDto;
import StudyCommunity.postcomment.entity.PostComment;
import StudyCommunity.postcomment.mapper.PostCommentMapper;
import StudyCommunity.postcomment.repository.PostCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostService postService;
    private final MemberService memberService;
    private final PostCommentMapper postCommentMapper;

    public PostCommentService(PostCommentRepository postCommentRepository, PostService postService, MemberService memberService, PostCommentMapper postCommentMapper) {
        this.postCommentRepository = postCommentRepository;
        this.postService = postService;
        this.memberService = memberService;
        this.postCommentMapper = postCommentMapper;
    }


    // 댓글 등록
    public PostComment createPostComment(PostComment postComment) {

        verifyPostComment(postComment);

        return postCommentRepository.save(postComment);
    }

    // 댓글 수정
    public PostComment updatePostComment(PostComment postComment) {

        PostComment findPostComment = findverifyPostComment(postComment.getPostCommentId());

        findPostComment.setContent(postComment.getContent());

        return postCommentRepository.save(findPostComment);
    }

    // 댓글 조회
    public PostComment findPostComment(long postCommentId) {
        PostComment findPostComment = postCommentRepository.findById(postCommentId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.POST_COMMENT_NOT_FOUND));

        return findPostComment;
    }

    // 모든 댓글 조회
    public List<PostComment> getAllPostComments() {
        return postCommentRepository.findAll();
    }

    public List<PostCommentResponseDto> findComments() {
        List<PostComment> comments = postCommentRepository.findAll();
        return postCommentMapper.postCommentToPostCommentResponseDtos(comments);
    }

    // 한 게시물에 달린 댓글들 찾기
    public List<PostCommentResponseDto> findCommentsByPostId(long postId) {
        List<PostCommentResponseDto> postCommentResponseDtos = findComments();
        return postCommentResponseDtos.stream().filter(d -> d.getPostId() == postId).collect(Collectors.toList());
    }

    // 댓글 삭제
    public void deletePostComment(long postCommentId) {
        postCommentRepository.deleteById(postCommentId);
    }


    // 댓글을 등록하기 위한 검증
    public void verifyPostComment(PostComment postComment) {

        // member 존재하는지 확인
        Member member = postComment.getMember();
        if (member == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND); // 적절한 예외 처리 추가
        }

        // member가 null이 아닌 경우에만 setId 메서드를 호출하여 memberId 설정
        postComment.setMember(memberService.findMember(member.getMemberId()));


        // study 존재하는지 확인
        Post post = postComment.getPost();
        if (post == null) {
            throw new BusinessLogicException(ExceptionCode.POST_NOT_FOUND); // 적절한 예외 처리 추가
        }

        // study가 null이 아닌 경우에만 setId 메서드를 호출하여 studyId 설정
        postComment.setPost(postService.findPost(post.getPostId()));

    }

    // Comment 존재하는지 확인
    public PostComment findverifyPostComment(long postCommentId) {

        Optional<PostComment> postComment = postCommentRepository.findById(postCommentId);

        PostComment findPostComment = postComment.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.POST_COMMENT_NOT_FOUND));

        return findPostComment;
    }
}
