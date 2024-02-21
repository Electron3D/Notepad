package ru.promauto.electron3d.notepad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promauto.electron3d.notepad.data.entity.Comment;
import ru.promauto.electron3d.notepad.data.entity.Note;
import ru.promauto.electron3d.notepad.data.entity.User;
import ru.promauto.electron3d.notepad.exception.NotFoundException;
import ru.promauto.electron3d.notepad.repository.CommentRepository;
import ru.promauto.electron3d.notepad.repository.NoteRepository;
import ru.promauto.electron3d.notepad.repository.UserRepository;
import ru.promauto.electron3d.notepad.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(Long noteId, Comment comment) {
        if (comment.getParentComment() == null) {
            Note note = noteRepository
                    .findById(noteId)
                    .orElseThrow(() -> new NotFoundException("Note with ID: \"" + noteId + "\" not found."));
            note.getComments().add(comment);
            noteRepository.save(note);
        } else {
            Long parentCommentId = comment.getParentComment().getId();
            if (!commentRepository.existsById(parentCommentId)) {
                throw new NotFoundException("Parent comment with ID: \"" + parentCommentId + "\" not found.");
            }
        }
        Long userId = comment.getUser().getId();
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID: \"" + userId + "\" not found."));
        user.getComments().add(comment);
        userRepository.save(user);
        commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Comment with ID: \"" + id + "\" not found."));
    }

    @Override
    public List<Comment> findAllByNoteId(Long noteId) {
        return noteRepository
                .findById(noteId)
                .orElseThrow(() -> new NotFoundException("Note with ID: \"" + noteId + "\" not found."))
                .getComments();
    }

    @Override
    @Transactional
    public void deleteById(Long noteId, Long id) {
        Comment comment = commentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Comment with ID: \"" + id + "\" not found."));
        User user = comment.getUser();
        user.getComments().remove(comment);
        userRepository.save(user);
        if (comment.getParentComment() == null) {
            Note note = noteRepository
                    .findById(noteId)
                    .orElseThrow(() -> new NotFoundException("Note with ID: \"" + noteId + "\" not found."));
            note.getComments().remove(comment);
            noteRepository.save(note);
        }
        commentRepository.deleteById(id);
    }
}
