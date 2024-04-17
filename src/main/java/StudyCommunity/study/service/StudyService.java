package StudyCommunity.study.service;

import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.service.MemberService;
import StudyCommunity.study.entity.Study;
import StudyCommunity.study.repository.StudyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final MemberService memberService;

    public StudyService(StudyRepository studyRepository, MemberService memberService) {
        this.studyRepository = studyRepository;
        this.memberService = memberService;
    }

    // 온라인 스터디 등록
    public Study createStudy(Study study) {

        verifyMember(study);

        return studyRepository.save(study);
    }

    // 온라인 스터디 수정
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

    // 온라인 스터디 조회
    public Study findStudy(long studyId) {

        Study findStudy = studyRepository.findById(studyId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.STUDY_NOT_FOUND));

        return findStudy;
    }

    // 모든 온라인 스터디 조회
    public List<Study> getAllStudies() {

        return studyRepository.findAll();
    }

    // 온라인 스터디 삭제
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

    // 온라인 스터디 존재하는지 확인
    public Study findverifyStudy(long studyId) {
        Optional<Study> study = studyRepository.findById(studyId);

        Study findStudy = study.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.STUDY_NOT_FOUND));

        return findStudy;
    }

}
