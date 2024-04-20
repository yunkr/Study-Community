package StudyCommunity.study.repository;

import StudyCommunity.post.entity.Post;
import StudyCommunity.study.entity.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    Page<Study> findByTitleContaining(String searchKeyword, Pageable pageable);

}
