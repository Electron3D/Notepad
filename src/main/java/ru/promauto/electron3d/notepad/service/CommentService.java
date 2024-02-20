package ru.promauto.electron3d.notepad.service;

import ru.promauto.electron3d.notepad.data.entity.Comment;

import java.util.List;

public interface CommentService {
    void create(Long userId, Comment comment);
    Comment findById(Long userId, Long id);
    List<Comment> findAll(Long userId);
    void deleteById(Long userId, Long id);
}
