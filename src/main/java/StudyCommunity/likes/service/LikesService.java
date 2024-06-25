package StudyCommunity.likes.service;

import StudyCommunity.likes.entity.Likes;
import StudyCommunity.likes.repository.LikesRepository;
import StudyCommunity.member.repository.MemberRepository;
import StudyCommunity.post.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public LikesService(LikesRepository likesRepository, MemberRepository memberRepository, PostRepository postRepository) {
        this.likesRepository = likesRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public long toggleLike(Long memberId, Long postId) {
        Likes existingLike = likesRepository.findByMember_MemberIdAndPost_PostId(memberId, postId);

        if (existingLike != null) {
            // 이미 해당 회원이 해당 게시물에 좋아요를 누른 경우 취소
            likesRepository.delete(existingLike);
        } else {
            // 해당 회원이 해당 게시물에 좋아요를 누르지 않은 경우 추가
            Likes like = new Likes();
            like.setMember(memberRepository.findById(memberId).orElse(null));
            like.setPost(postRepository.findById(postId).orElse(null));
            likesRepository.save(like);
        }
        return getLikesCount(postId);
    }

    public long getLikesCount(Long postId) {
        return likesRepository.countByPost_PostId(postId);
    }

}
