package StudyCommunity.onlinestudy.controller;

import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.onlinestudy.dto.OnlineStudyPatchDto;
import StudyCommunity.onlinestudy.dto.OnlineStudyPostDto;
import StudyCommunity.onlinestudy.dto.OnlineStudyResponseDto;
import StudyCommunity.onlinestudy.entity.OnlineStudy;
import StudyCommunity.onlinestudy.mapper.OnlineStudyMapper;
import StudyCommunity.onlinestudy.service.OnlineStudyService;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/onlineStudies")
@Validated
public class OnlineStudyController {

    private final static String ONLINE_STUDY_DEFAULT_URL = "/onlineStudies";

    private final OnlineStudyMapper onlineStudyMapper;
    private final OnlineStudyService onlineStudyService;

    public OnlineStudyController(OnlineStudyMapper onlineStudyMapper, OnlineStudyService onlineStudyService) {
        this.onlineStudyMapper = onlineStudyMapper;
        this.onlineStudyService = onlineStudyService;
    }

    // 온라인 스터디 등록(Post)
    @PostMapping
    public ResponseEntity<?> postOnlineStudy(@Valid @RequestBody OnlineStudyPostDto requestBody) {
        OnlineStudy onlineStudy = onlineStudyService.createOnlineStudy(onlineStudyMapper.onlineStudyPostDtoToOnlineStudy(requestBody));
        URI location = UriCreator.createUri(ONLINE_STUDY_DEFAULT_URL, onlineStudy.getOnlineStudyId());

        return ResponseEntity.created(location).build();
    }

    // 온라인 스터디 수정(Patch)
    @PatchMapping("/{onlineStudy-id}")
    public ResponseEntity<?> patchOnlineStudy(@PathVariable("onlineStudy-id") @Positive long onlineStudyId,
                                              @Valid @RequestBody OnlineStudyPatchDto requestBody) {

        requestBody.setOnlineStudyId(onlineStudyId);
        OnlineStudy onlineStudy = onlineStudyService.updateOnlineStudy(onlineStudyMapper.onlineStudyPatchDtoToOnlineStudy(requestBody));
        OnlineStudyResponseDto response = onlineStudyMapper.onlineStudyToOnlineStudyResponseDto(onlineStudy);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 온라인 스터디 조회(Get)
    @GetMapping("/{onlineStudy-id}")
    public ResponseEntity<?> getOnlineStudy(@PathVariable("onlineStudy-id") @Positive long onlineStudyId) {

        OnlineStudy onlineStudy = onlineStudyService.findOnlineStudy(onlineStudyId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(onlineStudyMapper.onlineStudyToOnlineStudyResponseDto(onlineStudy))
                , HttpStatus.OK);
    }

    // 모든 온라인 스터디 조회(Get)
    @GetMapping
    public ResponseEntity<?> getOnlineStudies() {
        List<OnlineStudy> onlineStudies = onlineStudyService.getAllOnlineStudies();

        return new ResponseEntity<>(onlineStudies, HttpStatus.OK);
    }

    // 온라인 스터디 삭제(Delete)
    @DeleteMapping("/{onlineStudy-id}")
    public ResponseEntity<?> deleteOnlineStudy(@PathVariable("onlineStudy-id") @Positive long onlineStudyId) {
        onlineStudyService.deleteOnlineStudy(onlineStudyId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
