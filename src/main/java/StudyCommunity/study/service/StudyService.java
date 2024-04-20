package StudyCommunity.study.service;

import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.service.MemberService;
import StudyCommunity.post.entity.Post;
import StudyCommunity.postTag.PostTag;
import StudyCommunity.study.entity.Study;
import StudyCommunity.study.repository.StudyRepository;
import StudyCommunity.studyTag.StudyTag;
import StudyCommunity.tag.entity.Tag;
import StudyCommunity.tag.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final MemberService memberService;
    private final TagRepository tagRepository;

    public StudyService(StudyRepository studyRepository, MemberService memberService, TagRepository tagRepository) {
        this.studyRepository = studyRepository;
        this.memberService = memberService;
        this.tagRepository = tagRepository;
    }

    // 스터디 등록
    public Study createStudy(Study study) {

        verifyMember(study);

        for (StudyTag studyTag : study.getStudyTags()) {
            Tag tag = studyTag.getTag();
            Tag existingHashTag = tagRepository.findByTagName(tag.getTagName());
            if (existingHashTag == null) {
                tagRepository.save(tag);
            } else {
                studyTag.setTag(existingHashTag);
            }
            studyTag.setStudy(study);
        }

        return studyRepository.save(study);
    }

    // 스터디 수정
    public Study updateStudy(Study study) {

        Study findStudy = findverifyStudy(study.getStudyId());

        findStudy.setTitle(study.getTitle());
        findStudy.setTopic(study.getTopic());
        findStudy.setSchedule(study.getSchedule());
        findStudy.setCurriculum(study.getCurriculum());
        findStudy.setPersonnel(study.getPersonnel());
        findStudy.setIntroduction(study.getIntroduction());
        findStudy.setPrecautions(study.getPrecautions());
        findStudy.setApply(study.getApply());

        return studyRepository.save(findStudy);
    }

    // 스터디 조회
    public Study findStudy(long studyId) {

        Study findStudy = studyRepository.findById(studyId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.STUDY_NOT_FOUND));

        // 게시글 조회수 증가
        long updatedViewCount = findStudy.getViewCount() + 1;
        findStudy.setViewCount(updatedViewCount);

        // 변경 사항을 데이터베이스에 저장
        studyRepository.save(findStudy);

        return findStudy;
    }

//    // 모든 스터디 조회
//    public List<Study> getAllStudies() {
//
//        return studyRepository.findAll();
//    }

    public Page<Study> findAllStudies(int page, int size) {
        return studyRepository.findAll(PageRequest.of(page, size, Sort.by("studyId").descending()));
    }

    // 스터디 삭제
    public void deleteStudy(long studyId) {

        studyRepository.deleteById(studyId);
    }

    // member 존재하는지 확인
    public void verifyMember(Study study) {
        Member member = study.getMember();
        if (member == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND); // 적절한 예외 처리 추가
        }

        // member가 null이 아닌 경우에만 setId 메서드를 호출하여 memberId 설정
        study.setMember(memberService.findMember(member.getMemberId()));
    }

    // 스터디 존재하는지 확인
    public Study findverifyStudy(long studyId) {
        Optional<Study> study = studyRepository.findById(studyId);

        Study findStudy = study.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.STUDY_NOT_FOUND));

        return findStudy;
    }


    /* 검색 기능 - pagination(페이지네이션) */
    public Page<Study> searchStudies(int page, int size, String searchKeyword, boolean ascendingSort) {
        Pageable pageable;

        if (ascendingSort) {
            pageable = PageRequest.of(page, size, Sort.by("studyId").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("studyId").descending());
        }

        if (searchKeyword.equals("")) {
            return studyRepository.findAll(pageable);
        } else if (!searchKeyword.equals("")) {
            return studyRepository.findByTitleContaining(searchKeyword, pageable);
        } else {
            throw new BusinessLogicException(ExceptionCode.SEARCH_NOT_FOUND);
        }
    }
}
