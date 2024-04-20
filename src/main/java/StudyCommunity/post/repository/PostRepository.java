package StudyCommunity.post.repository;

import StudyCommunity.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 게시글 검색 기능
    Page<Post> findByTitleContaining(String searchKeyword, Pageable pageable);

}
