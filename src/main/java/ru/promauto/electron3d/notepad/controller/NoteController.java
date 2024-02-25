package ru.promauto.electron3d.notepad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "CRUD for Notes entities"
)
public class NoteController {
    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create note")
    public RestResponse createNote(
            @Parameter(description = "user ID", required = true) @PathVariable Long userId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "NoteDto as request body in JSON format",
                    required = true) @RequestBody @Valid NoteDto noteDto) {
        noteService.create(userId, noteMapper.dtoToEntity(noteDto));
        return new RestResponse("Note created.");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get note by ID")
    public RestResponse getNoteById(
            @Parameter(description = "user ID", required = true) @PathVariable Long userId,
            @Parameter(description = "note ID", required = true) @PathVariable Long id) {
        return new RestResponse(noteMapper.entityToDto(noteService.findById(userId, id)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all notes by user ID")
    public RestResponse getAllNotesByUserId(
            @Parameter(description = "user ID", required = true) @PathVariable Long userId) {
        return new RestResponse(noteService.findAllByUserId(userId)
                .stream()
                .map(noteMapper::entityToDto)
                .toList());
    }

    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all notes for user ID and all public notes")
    public RestResponse getAllPublicNotes(
            @Parameter(description = "user ID", required = true) @PathVariable Long userId) {
        return new RestResponse(noteService.findAllPublic(userId)
                .stream()
                .map(noteMapper::entityToDto)
                .toList());
    }

    @GetMapping("/tag")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all notes filtered by tag")
    public RestResponse getAllNotesByTag(
            @Parameter(description = "user ID", required = true) @PathVariable Long userId,
            @Parameter(description = "filter tag", required = true) @RequestParam(name = "tag") String tag) {
        return new RestResponse(noteService.findAllByUserIdAndTag(userId, tag)
                .stream()
                .map(noteMapper::entityToDto)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update note by ID")
    public RestResponse updateNoteById(
            @Parameter(description = "user ID", required = true) @PathVariable Long userId,
            @Parameter(description = "note ID", required = true) @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "NoteDto as request body in JSON format",
                    required = true) @RequestBody NoteDto noteDto) {
        noteService.updateById(userId, id, noteMapper.dtoToEntity(noteDto));
        return new RestResponse("Note with ID " + id + " updated.");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete note by ID")
    public RestResponse deleteNoteById(
            @Parameter(description = "user ID", required = true) @PathVariable Long userId,
            @Parameter(description = "note ID", required = true) @PathVariable Long id) {
        noteService.deleteById(userId, id);
        return new RestResponse("Note with ID " + id + " deleted.");
    }
}
