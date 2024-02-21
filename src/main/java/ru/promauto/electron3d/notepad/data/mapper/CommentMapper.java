package ru.promauto.electron3d.notepad.data.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.promauto.electron3d.notepad.data.dto.CommentDto;
import ru.promauto.electron3d.notepad.data.entity.Comment;
import ru.promauto.electron3d.notepad.exception.NotFoundException;
import ru.promauto.electron3d.notepad.repository.CommentRepository;
import ru.promauto.electron3d.notepad.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class CommentMapper extends AbstractMapper<Comment, CommentDto> {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    @Override
    public Comment dtoToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        Long parentCommentId = commentDto.getParentCommentId();
        Comment parentComment = commentRepository
                .findById(parentCommentId)
                .orElseThrow(() -> new NotFoundException("Parent comment with ID: \"" + parentCommentId + "\" not found."));
        comment.setParentComment(parentComment);
        String userNickname = commentDto.getUserNickname();
        comment.setUser(userRepository
                .findByNickname(userNickname)
                .orElseThrow(() -> new NotFoundException("User with nickname: \"" + userNickname + "\" not found.")));
        return comment;
    }

    @Override
    public CommentDto entityToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setText(comment.getText());
        commentDto.setUserNickname(comment.getUser().getNickname());
        commentDto.setParentCommentId(commentDto.getParentCommentId());
        return commentDto;
    }
}
