package StudyCommunity.note.service;

import StudyCommunity.exception.BusinessLogicException;
import StudyCommunity.exception.ExceptionCode;
import StudyCommunity.member.entity.Member;
import StudyCommunity.member.service.MemberService;
import StudyCommunity.note.entity.Note;
import StudyCommunity.note.repository.NoteRepository;
import StudyCommunity.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final MemberService memberService;

    public NoteService(NoteRepository noteRepository, MemberService memberService) {
        this.noteRepository = noteRepository;
        this.memberService = memberService;
    }

    // Note 등록
    public Note createNote(Note note) {

        verifyMember(note);

        return noteRepository.save(note);
    }

    // Note 수정
    public Note updateNote(Note note) {

        Note findNote = findverifyNote(note.getNoteId());

        findNote.setTitle(note.getTitle());
        findNote.setContent(note.getContent());

        return noteRepository.save(findNote);
    }

    // Note 조회
    public Note findNote(long noteId) {
        Note findNote = noteRepository.findById(noteId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.NOTE_NOT_FOUND));

        return findNote;
    }

//    // 모든 Note 조회
//    public List<Note> getAllNotes() {
//        return noteRepository.findAll();
//    }

    public Page<Note> findAllNotes(int page, int size) {
        return noteRepository.findAll(PageRequest.of(page, size, Sort.by("noteId").descending()));
    }

    // Note 삭제
    public void deleteNote(long noteId) {
        noteRepository.deleteById(noteId);
    }


    // member 존재하는지 확인
    public void verifyMember(Note note) {
        Member member = note.getMember();
        if (member == null) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND); // 적절한 예외 처리 추가
        }

        // member가 null이 아닌 경우에만 setId 메서드를 호출하여 memberId 설정
        note.setMember(memberService.findMember(member.getMemberId()));
    }

    // Note 존재하는지 확인
    public Note findverifyNote(long noteId) {

        Optional<Note> note = noteRepository.findById(noteId);

        Note findNote = note.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.NOTE_NOT_FOUND));

        return findNote;
    }

    /* 검색 기능 - pagination(페이지네이션) */
    public Page<Note> searchNotes(int page, int size, String searchKeyword, boolean ascendingSort) {
        Pageable pageable;

        if (ascendingSort) {
            pageable = PageRequest.of(page, size, Sort.by("noteId").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("noteId").descending());
        }

        if (searchKeyword.equals("")) {
            return noteRepository.findAll(pageable);
        } else if (!searchKeyword.equals("")) {
            return noteRepository.findByTitleContaining(searchKeyword, pageable);
        } else {
            throw new BusinessLogicException(ExceptionCode.SEARCH_NOT_FOUND);
        }
    }

}
