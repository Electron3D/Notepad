package ru.promauto.electron3d.notepad.service.impl;

import org.springframework.stereotype.Service;
import ru.promauto.electron3d.notepad.data.entity.Comment;
import ru.promauto.electron3d.notepad.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public void create(Long userId, Comment comment) {

    }

    @Override
    public Comment findById(Long userId, Long id) {
        return null;
    }

    @Override
    public List<Comment> findAll(Long userId) {
        return null;
    }

    @Override
    public void updateById(Long userId, Long id, Comment comment) {

    }

    @Override
    public void deleteById(Long userId, Long id) {

    }
}
