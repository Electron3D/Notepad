package ru.promauto.electron3d.notepad.controller;

import jakarta.validation.Valid;
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
    public RestResponse createNote(@PathVariable Long userId, @RequestBody @Valid NoteDto noteDto) {
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
    public RestResponse getAllNotesByUserId(@PathVariable Long userId) {
        return new RestResponse(noteService.findAllByUserId(userId)
                .stream()
                .map(noteMapper::entityToDto)
                .toList());
    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getAllPublicNotes(@PathVariable Long userId) {
        return new RestResponse(noteService.findAllPublic(userId)
                .stream()
                .map(noteMapper::entityToDto)
                .toList());
    }

    @GetMapping("/tag")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getAllNotesByTag(@PathVariable Long userId, @RequestParam(name = "tag") String tag) {
        return new RestResponse(noteService.findAllByUserIdAndTag(userId, tag)
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
    public RestResponse deleteNoteById(@PathVariable Long userId, @PathVariable Long id) {
        noteService.deleteById(userId, id);
        return new RestResponse("Note with ID \"" + id + "\" deleted.");
    }
}
