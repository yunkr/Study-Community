package StudyCommunity.post.service;

import StudyCommunity.note.entity.Note;
import StudyCommunity.post.entity.Post;
import StudyCommunity.postTag.PostTag;
import StudyCommunity.post.repository.PostRepository;
import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.service.MemberService;
import StudyCommunity.tag.entity.Tag;
import StudyCommunity.tag.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final TagRepository tagRepository;

    public PostService(PostRepository postRepository, MemberService memberService, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.memberService = memberService;
        this.tagRepository = tagRepository;
    }

    // 게시글 등록
    public Post createPost(Post post) {

        //verifyMember(post);

        for (PostTag postHashTag : post.getPostTags()) {
            Tag tag = postHashTag.getTag();
            Tag existingHashTag = tagRepository.findByTagName(tag.getTagName());
            if (existingHashTag == null) {
                tagRepository.save(tag);
            } else {
                postHashTag.setTag(existingHashTag);
            }
            postHashTag.setPost(post);
        }

        return postRepository.save(post);
    }

    // 게시글 수정
    public Post updatePost(Post post) {

        Post findPost = findVerifyPost(post.getPostId());

        findPost.setTitle(post.getTitle());
        findPost.setContent(post.getContent());

        return postRepository.save(findPost);
    }

    /*
    // 게시글 조회
    public Post findPost(long postId) {
        Post findPost = postRepository.findById(postId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));

        // 게시글 조회수 증가
        long updatedViewCount = findPost.getViewCount() + 1;
        findPost.setViewCount(updatedViewCount);

        // 변경 사항을 데이터베이스에 저장
        postRepository.save(findPost);

        return findPost;
    }
     */

    // 게시글 조회
    public Post findPost(long postId) {
        Post findPost = postRepository.findById(postId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.NOTE_NOT_FOUND));

        return findPost;
    }

    // 게시글 조회
    public Post findPostViewCount(long postId) {

        Post findPost = findVerifyPost(postId);

        // 게시글 조회수 증가
        findPost.setViewCount(findPost.getViewCount() + 1);

        return postRepository.save(findPost);
    }

    /*
    // 모든 게시글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
     */

    public Page<Post> findAllPosts(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size, Sort.by("postId").descending()));
    }


    // 게시글 삭제
    public void deletePost(long postId) {
        postRepository.deleteById(postId);
    }


    // member 존재하는지 확인
    public void verifyMember(Post post) {
        Member member = post.getMember();
        if (member == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND); // 적절한 예외 처리 추가
        }

        // member가 null이 아닌 경우에만 setId 메서드를 호출하여 memberId 설정
        post.setMember(memberService.findMember(member.getMemberId()));
    }

    // Post 존재하는지 확인
    public Post findVerifyPost(long postId) {

        Optional<Post> post = postRepository.findById(postId);

        Post findPost = post.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));

        return findPost;
    }

    /* 검색 기능 - pagination(페이지네이션) */
    public Page<Post> searchPosts(int page, int size, String searchKeyword, boolean ascendingSort) {
        Pageable pageable;

        if (ascendingSort) {
            pageable = PageRequest.of(page, size, Sort.by("postId").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("postId").descending());
        }

        if (searchKeyword.equals("")) {
            return postRepository.findAll(pageable);
        } else if (!searchKeyword.equals("")) {
            return postRepository.findByTitleContaining(searchKeyword, pageable);
        } else {
            throw new BusinessLogicException(ExceptionCode.SEARCH_NOT_FOUND);
        }
    }
}