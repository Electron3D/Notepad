package ru.promauto.electron3d.notepad.service;

import ru.promauto.electron3d.notepad.data.entity.Note;

import java.util.List;

public interface NoteService {
    void create(Long userId, Note note);
    Note findById(Long userId, Long id);
    List<Note> findAll(Long userId);
    void updateById(Long userId, Long id, Note note);
    void deleteById(Long userId, Long id);
}
