package StudyCommunity.note.controller;

import StudyCommunity.dto.MultiResponseDto;
import StudyCommunity.dto.SingleResponseDto;
import StudyCommunity.note.dto.NotePatchDto;
import StudyCommunity.note.dto.NotePostDto;
import StudyCommunity.note.dto.NoteResponseDto;
import StudyCommunity.note.entity.Note;
import StudyCommunity.note.mapper.NoteMapper;
import StudyCommunity.note.service.NoteService;
import StudyCommunity.post.entity.Post;
import StudyCommunity.utils.UriCreator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final static String NOTE_DEFAULT_URL = "/notes";

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    // 노트 등록(Post)
    @PostMapping
    public ResponseEntity<?> postNote(@Valid @RequestBody NotePostDto requestBody) {
        Note note = noteService.createNote(noteMapper.notePostDtoToNote(requestBody));
        URI location = UriCreator.createUri(NOTE_DEFAULT_URL, note.getNoteId());

        return ResponseEntity.created(location).build();
    }

    // 노트 수정(Patch)
    @PatchMapping("{note-id}")
    public ResponseEntity<?> patchNote(@PathVariable("note-id") @Positive long noteId,
                                       @Valid @RequestBody NotePatchDto requestBody) {

        requestBody.setNoteId(noteId);
        Note note = noteService.updateNote(noteMapper.notePatchDtoToNote(requestBody));
        NoteResponseDto response = noteMapper.noteToNoteResponseDto(note);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 노트 조회(Get)
    @GetMapping("{note-id}")
    public ResponseEntity<?> getNote(@PathVariable("note-id") @Positive long noteId) {

        Note note = noteService.findNote(noteId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(noteMapper.noteToNoteResponseDto(note))
                , HttpStatus.OK);
    }

    // 모든 노트 조회(Get)
    @GetMapping
    public ResponseEntity<?> getNotes(@RequestParam int page, @RequestParam int size) {
        Page<Note> pageNotes = noteService.findAllNotes(page-1, size);
        List<Note> notes = pageNotes.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(noteMapper.noteToNoteResponseDtos(notes), pageNotes)
                , HttpStatus.OK);
    }


    // 노트 삭제(Delete)
    @DeleteMapping("{note-id}")
    public ResponseEntity<?> deleteNote(@PathVariable("note-id") @Positive long noteId) {

        noteService.deleteNote(noteId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* 검색 기능 - pagination(페이지네이션) */
    @GetMapping("/search")
    public ResponseEntity<?> searchNotesByTitleContaining(
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "1") @Positive int page,
            @RequestParam(defaultValue = "10") @Positive int size,
            @RequestParam(defaultValue = "descending") String sort) {

        boolean ascendingSort = sort.equalsIgnoreCase("ascending");

        Page<Note> notesPage = noteService.searchNotes(page - 1, size, searchKeyword, ascendingSort);
        List<Note> content = notesPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(noteMapper.noteToNoteResponseDtos(content), notesPage),
                HttpStatus.OK
        );
    }

}
