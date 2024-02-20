package ru.promauto.electron3d.notepad.data.mapper;

import org.springframework.stereotype.Component;
import ru.promauto.electron3d.notepad.data.dto.NoteDto;
import ru.promauto.electron3d.notepad.data.entity.Comment;
import ru.promauto.electron3d.notepad.data.entity.Note;
import ru.promauto.electron3d.notepad.data.entity.Tag;

@Component
public class NoteMapper extends AbstractMapper<Note, NoteDto> {
    @Override
    public Note dtoToEntity(NoteDto noteDto) {
        Note note = new Note();
        note.setText(noteDto.getText());
        note.setTag(Tag.valueOf(noteDto.getTag()));
        note.setCheckList(noteDto.getCheckList());
        return note;
    }

    @Override
    public NoteDto entityToDto(Note note) {
        NoteDto noteDto = new NoteDto();
        noteDto.setText(note.getText());
        noteDto.setUserNickname(note.getUser().getNickname());
        noteDto.setComments(note.getComments()
                .stream()
                .map(Comment::getText)
                .toList());
        noteDto.setTag(note.getTag().name());
        noteDto.setCheckList(note.getCheckList());
        return noteDto;
    }
}
