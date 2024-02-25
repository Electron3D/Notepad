package ru.promauto.electron3d.notepad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "CRUD for Comments entities"
)
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping("/notes/{noteId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create comment")
    public RestResponse createComment(
            @Parameter(
                    description = "note ID", required = true) @PathVariable Long noteId,
            @Parameter(
                    description = "comment owner user ID", required = true) @RequestParam(name = "userID") Long userId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "CommentDto as request body in JSON format",
                    required = true) @RequestBody @Valid CommentDto commentDto) {
        commentService.create(noteId, userId, commentMapper.dtoToEntity(commentDto));
        return new RestResponse("Comment created.");
    }

    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get comment by ID")
    public RestResponse getCommentById(@Parameter(description = "comment ID", required = true) @PathVariable Long id) {
        return new RestResponse(commentMapper.entityToDto(commentService.findById(id)));
    }

    @GetMapping("/notes/{noteId}/comments")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all comments by note ID")
    public RestResponse getAllCommentsByNoteId(
            @Parameter(description = "note ID", required = true) @PathVariable Long noteId) {
        return new RestResponse(commentService.findAllByNoteId(noteId)
                .stream()
                .map(commentMapper::entityToDto)
                .toList());
    }

    @DeleteMapping("/notes/{noteId}/comments/{id}")
    @Operation(summary = "Delete comment by ID")
    public RestResponse deleteCommentById(
            @Parameter(description = "note ID", required = true) @PathVariable Long noteId,
            @Parameter(description = "comment ID", required = true) @PathVariable Long id) {
        commentService.deleteById(noteId, id);
        return new RestResponse("Comment with ID " + id + " deleted.");
    }
}
