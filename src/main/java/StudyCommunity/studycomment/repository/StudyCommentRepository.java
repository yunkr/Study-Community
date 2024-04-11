package StudyCommunity.studycomment.repository;

import StudyCommunity.studycomment.entity.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCommentRepository extends JpaRepository<StudyComment, Long> {

}
