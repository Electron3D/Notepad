package ru.promauto.electron3d.notepad.service;

import ru.promauto.electron3d.notepad.data.entity.Note;

import java.util.List;

public interface NoteService {
    void create(Note note);
    Note findById(Long id);
    List<Note> findAll();
    void updateById(Long id, Note note);
    void deleteById(Long id);
}
