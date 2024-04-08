package StudyCommunity.onlinestudy.repository;

import StudyCommunity.onlinestudy.entity.OnlineStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineStudyRepository extends JpaRepository<OnlineStudy, Long> {

}
