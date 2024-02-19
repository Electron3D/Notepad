package ru.promauto.electron3d.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.promauto.electron3d.notepad.data.NoteMapper;
import ru.promauto.electron3d.notepad.data.RestResponse;
import ru.promauto.electron3d.notepad.data.dto.NoteDto;
import ru.promauto.electron3d.notepad.service.NoteService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createNote(NoteDto noteDto) {
        noteService.create(noteMapper.dtoToEntity(noteDto));
        return new RestResponse("Note created.");
    }

    @GetMapping("/{id}")
    public RestResponse getNoteById(@PathVariable Long id) {
        return new RestResponse(noteMapper.entityToDto(noteService.findById(id)));
    }

    @GetMapping
    public RestResponse getAllNotes() {
        return new RestResponse(noteService.findAll()
                .stream()
                .map(noteMapper::entityToDto)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public RestResponse updateNoteById(@PathVariable Long id, @RequestBody NoteDto noteDto) {
        noteService.updateById(id, noteMapper.dtoToEntity(noteDto));
        return new RestResponse("Note with ID \"" + id + "\" updated.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RestResponse deleteNoteById(@PathVariable Long id) {
        noteService.deleteById(id);
        return new RestResponse("Note with ID \"" + id + "\" deleted.");
    }
}
