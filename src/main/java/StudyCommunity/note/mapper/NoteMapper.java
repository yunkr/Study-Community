package StudyCommunity.note.mapper;

import StudyCommunity.member.entity.Member;
import StudyCommunity.note.dto.NotePatchDto;
import StudyCommunity.note.dto.NotePostDto;
import StudyCommunity.note.dto.NoteResponseDto;
import StudyCommunity.note.entity.Note;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteMapper {

    default Note notePostDtoToNote(NotePostDto notePostDto) {

        Member member = new Member();
        Note note = new Note();

        member.setMemberId(member.getMemberId());

        note.setMember(notePostDto.getMember());
        note.setTitle(notePostDto.getTitle());
        note.setContent(notePostDto.getContent());

        return note;
    }

    default Note notePatchDtoToNote(NotePatchDto notePatchDto) {

        Note note = new Note();

        note.setNoteId(notePatchDto.getNoteId());

        note.setTitle(notePatchDto.getTitle());
        note.setContent(notePatchDto.getContent());

        return note;
    }

    default NoteResponseDto noteToNoteResponseDto(Note note) {

        NoteResponseDto noteResponseDto = new NoteResponseDto();

        noteResponseDto.setMemberId(note.getMember().getMemberId());

        noteResponseDto.setNoteId(note.getNoteId());
        noteResponseDto.setTitle(note.getTitle());
        noteResponseDto.setContent(note.getContent());

        noteResponseDto.setCreatedAt(note.getCreatedAt());
        noteResponseDto.setLastModifiedAt(note.getLastModifiedAt());

        return noteResponseDto;
    }
}
