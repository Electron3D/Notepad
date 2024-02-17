package ru.promauto.electron3d.notepad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.promauto.electron3d.notepad.data.entity.Note;
import ru.promauto.electron3d.notepad.repository.CommentRepository;
import ru.promauto.electron3d.notepad.repository.NoteRepository;
import ru.promauto.electron3d.notepad.service.NoteService;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, CommentRepository commentRepository) {
        this.noteRepository = noteRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void create(Note note) {
        noteRepository.save(note);
    }

    @Override
    public Note findById(Long id) {
        return null;
    }

    @Override
    public List<Note> findAll() {

        return null;
    }

    @Override
    public void updateById(Long id, Note note) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
