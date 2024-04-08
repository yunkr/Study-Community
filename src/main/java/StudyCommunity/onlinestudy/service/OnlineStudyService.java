package StudyCommunity.onlinestudy.service;

import StudyCommunity.board.entity.Board;
import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.service.MemberService;
import StudyCommunity.onlinestudy.entity.OnlineStudy;
import StudyCommunity.onlinestudy.repository.OnlineStudyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OnlineStudyService {

    private final OnlineStudyRepository onlineStudyRepository;
    private final MemberService memberService;

    public OnlineStudyService(OnlineStudyRepository onlineStudyRepository, MemberService memberService) {
        this.onlineStudyRepository = onlineStudyRepository;
        this.memberService = memberService;
    }

    // 온라인 스터디 등록
    public OnlineStudy createOnlineStudy(OnlineStudy onlineStudy) {

        verifyMember(onlineStudy);

        return onlineStudyRepository.save(onlineStudy);
    }

    // 온라인 스터디 수정
    public OnlineStudy updateOnlineStudy(OnlineStudy onlineStudy) {

        OnlineStudy findOnlineStudy = findverifyOnlineStudy(onlineStudy.getOnlineStudyId());

        findOnlineStudy.setTitle(onlineStudy.getTitle());
        findOnlineStudy.setTopic(onlineStudy.getTopic());
        findOnlineStudy.setSchedule(onlineStudy.getSchedule());
        findOnlineStudy.setCurriculum(onlineStudy.getCurriculum());
        findOnlineStudy.setPersonnel(onlineStudy.getPersonnel());
        findOnlineStudy.setIntroduction(onlineStudy.getIntroduction());
        findOnlineStudy.setPrecautions(onlineStudy.getPrecautions());
        findOnlineStudy.setApply(onlineStudy.getApply());

        return onlineStudyRepository.save(onlineStudy);
    }

    // 온라인 스터디 조회
    public OnlineStudy findOnlineStudy(long onlineStudyId) {

        OnlineStudy findOnlineStudy = onlineStudyRepository.findById(onlineStudyId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ONLINE_STUDY_NOT_FOUND));

        return findOnlineStudy;
    }

    // 모든 온라인 스터디 조회
    public List<OnlineStudy> getAllOnlineStudies() {

        return onlineStudyRepository.findAll();
    }

    // 온라인 스터디 삭제
    public void deleteOnlineStudy(long onlineStudyId) {

        onlineStudyRepository.deleteById(onlineStudyId);
    }


    /*
    // member 존재하는지 확인
    public void verifyMember(OnlineStudy onlineStudy) {

        Member member = memberService.findMember(onlineStudy.getMember().getMemberId());
        onlineStudy.setMember(member);
    }
     */

    // member 존재하는지 확인
    public void verifyMember(OnlineStudy onlineStudy) {
        Member member = onlineStudy.getMember();
        if (member == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND); // 적절한 예외 처리 추가
        }

        // member가 null이 아닌 경우에만 setId 메서드를 호출하여 memberId 설정
        onlineStudy.setMember(memberService.findMember(member.getMemberId()));
    }

    // 온라인 스터디 존재하는지 확인
    public OnlineStudy findverifyOnlineStudy(long onlineStudyId) {
        Optional<OnlineStudy> onlineStudy = onlineStudyRepository.findById(onlineStudyId);

        OnlineStudy findOnlineStudy = onlineStudy.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ONLINE_STUDY_NOT_FOUND));

        return findOnlineStudy;
    }

}
