package ru.promauto.electron3d.notepad.data;

import org.springframework.stereotype.Component;
import ru.promauto.electron3d.notepad.data.dto.NoteDto;
import ru.promauto.electron3d.notepad.data.entity.Note;

@Component
public class NoteMapper {
    public Note dtoToEntity(NoteDto noteDto) {
        Note note = new Note();
        //todo implement
        return note;
    }
    public NoteDto entityToDto(Note note) {
        NoteDto noteDto = new NoteDto();
        //todo implement
        return noteDto;
    }
}
