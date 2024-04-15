package StudyCommunity.likes.repository;

import StudyCommunity.likes.entity.Likes;
import StudyCommunity.member.entity.Member;
import StudyCommunity.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    // 멤버 ID와 게시물 ID를 이용하여 해당 좋아요를 조회합니다.
    Likes findByMember_MemberIdAndPost_PostId(Long memberId, Long postId);

    // 게시물 ID를 이용하여 해당 게시물에 대한 좋아요 수를 세어 반환합니다.
    long countByPost_PostId(Long postId);

}
