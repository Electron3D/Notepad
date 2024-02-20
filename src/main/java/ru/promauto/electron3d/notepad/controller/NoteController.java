package ru.promauto.electron3d.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.promauto.electron3d.notepad.data.mapper.NoteMapper;
import ru.promauto.electron3d.notepad.data.RestResponse;
import ru.promauto.electron3d.notepad.data.dto.NoteDto;
import ru.promauto.electron3d.notepad.service.NoteService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{userId}/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createNote(@PathVariable Long userId, @RequestBody NoteDto noteDto) {
        noteService.create(userId, noteMapper.dtoToEntity(noteDto));
        return new RestResponse("Note created.");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getNoteById(@PathVariable Long userId, @PathVariable Long id) {
        return new RestResponse(noteMapper.entityToDto(noteService.findById(userId, id)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getAllNotes(@PathVariable Long userId) {
        return new RestResponse(noteService.findAll(userId)
                .stream()
                .map(noteMapper::entityToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getAllNotesByTag(@PathVariable Long userId, @RequestParam(name = "tag") String tag) {
        return new RestResponse(noteService.findAllByTag(userId, tag)
                .stream()
                .map(noteMapper::entityToDto)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse updateNoteById(@PathVariable Long userId,
                                       @PathVariable Long id,
                                       @RequestBody NoteDto noteDto) {
        noteService.updateById(userId, id, noteMapper.dtoToEntity(noteDto));
        return new RestResponse("Note with ID \"" + id + "\" updated.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RestResponse deleteNoteById(@PathVariable Long userId, @PathVariable Long id) {
        noteService.deleteById(userId, id);
        return new RestResponse("Note with ID \"" + id + "\" deleted.");
    }
}
