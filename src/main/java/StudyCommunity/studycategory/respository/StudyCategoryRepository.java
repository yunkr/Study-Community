package StudyCommunity.studycategory.respository;

import StudyCommunity.studycategory.entity.StudyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCategoryRepository extends JpaRepository<StudyCategory, Long> {

}
