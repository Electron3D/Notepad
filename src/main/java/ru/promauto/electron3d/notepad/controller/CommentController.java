package ru.promauto.electron3d.notepad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.promauto.electron3d.notepad.data.RestResponse;
import ru.promauto.electron3d.notepad.data.dto.CommentDto;
import ru.promauto.electron3d.notepad.data.mapper.CommentMapper;
import ru.promauto.electron3d.notepad.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping("/notes/{noteId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createComment(@PathVariable Long noteId,
                                      @RequestBody @Valid CommentDto commentDto) {
        commentService.create(noteId, commentMapper.dtoToEntity(commentDto));
        return new RestResponse("Comment created.");
    }

    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getCommentById(@PathVariable Long id) {
        return new RestResponse(commentMapper.entityToDto(commentService.findById(id)));
    }

    @GetMapping("/notes/{noteId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getAllCommentsByNoteId(@PathVariable Long noteId) {
        return new RestResponse(commentService.findAllByNoteId(noteId)
                .stream()
                .map(commentMapper::entityToDto)
                .toList());
    }

    @DeleteMapping("/notes/{noteId}/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RestResponse deleteCommentById(@PathVariable Long noteId,
                                          @PathVariable Long id) {
        commentService.deleteById(noteId, id);
        return new RestResponse("Comment with ID \"" + id + "\" deleted.");
    }
}
