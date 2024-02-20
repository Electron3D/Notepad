package ru.promauto.electron3d.notepad.data.mapper;

import org.springframework.stereotype.Component;
import ru.promauto.electron3d.notepad.data.dto.CommentDto;
import ru.promauto.electron3d.notepad.data.entity.Comment;

@Component
public class CommentMapper extends AbstractMapper<Comment, CommentDto> {
    @Override
    public Comment dtoToEntity(CommentDto commentDto) {
        return null;
    }

    @Override
    public CommentDto entityToDto(Comment comment) {
        return null;
    }
}
