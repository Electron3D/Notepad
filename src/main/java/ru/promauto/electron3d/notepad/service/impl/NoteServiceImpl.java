package ru.promauto.electron3d.notepad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promauto.electron3d.notepad.data.entity.Note;
import ru.promauto.electron3d.notepad.data.entity.Tag;
import ru.promauto.electron3d.notepad.data.entity.User;
import ru.promauto.electron3d.notepad.exception.NotFoundException;
import ru.promauto.electron3d.notepad.repository.NoteRepository;
import ru.promauto.electron3d.notepad.repository.UserRepository;
import ru.promauto.electron3d.notepad.service.NoteService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Override
    @Transactional
    public void create(Long userId, Note note) {
        User user = getUserIfExist(userId);
        note.setUser(user);
        user.getNotes().add(note);
        noteRepository.save(note);
    }

    @Override
    @Transactional(readOnly = true)
    public Note findById(Long userId, Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with ID: \"" + id + "\" not found."));
        if (note.getUser().getId().equals(userId)) {
            return note;
        } else {
            throw new AccessDeniedException("It's not your note!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllByUserId(Long userId) {
        User user = getUserIfExist(userId);
        return user.getNotes();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllByUserIdAndTag(Long userId, String tag) {
        if (userRepository.existsById(userId)) {
            return noteRepository
                    .findAllByUserIdAndTag(userId, Tag.valueOf(tag))
                    .orElse(new ArrayList<>());
        } else {
            throw new NotFoundException("User with ID: \"" + userId + "\" not found.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllPublic(Long userId) {
        return noteRepository.findAllByUserIdOrAccessModifier_Public(userId).orElse(new ArrayList<>());
    }

    @Override
    @Transactional
    public void updateById(Long userId, Long id, Note note) {
        Note existedNote = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with ID: \"" + id + "\" not found."));
        User user = existedNote.getUser();
        if (user.getId().equals(userId)) {
            existedNote.setText(note.getText());
            existedNote.setAccessModifier(note.getAccessModifier());
            existedNote.setTag(note.getTag());
            existedNote.setCheckList(note.getCheckList());
        } else {
            throw new AccessDeniedException("It's not your note!");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long userId, Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with ID: \"" + id + "\" not found."));
        User user = note.getUser();
        if (user.getId().equals(userId)) {
            user.getNotes().remove(note);
            noteRepository.deleteById(id);
        } else {
            throw new AccessDeniedException("It's not your note!");
        }
    }

    private User getUserIfExist(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID: \"" + userId + "\" not found."));
    }
}
