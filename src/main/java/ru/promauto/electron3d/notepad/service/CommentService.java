package ru.promauto.electron3d.notepad.service;

import ru.promauto.electron3d.notepad.data.entity.Comment;

import java.util.List;

public interface CommentService {
    void create(Long noteId, Long userId, Comment comment);
    Comment findById(Long id);
    List<Comment> findAllByNoteId(Long noteId);
    void deleteById(Long noteId, Long id);
}
