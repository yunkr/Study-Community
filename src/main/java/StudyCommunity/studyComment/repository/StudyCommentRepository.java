package StudyCommunity.studyComment.repository;

import StudyCommunity.study.entity.Study;
import StudyCommunity.studyComment.entity.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCommentRepository extends JpaRepository<StudyComment, Long> {

}
