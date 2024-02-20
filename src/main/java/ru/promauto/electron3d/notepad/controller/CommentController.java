package ru.promauto.electron3d.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.promauto.electron3d.notepad.data.RestResponse;
import ru.promauto.electron3d.notepad.data.dto.CommentDto;
import ru.promauto.electron3d.notepad.data.mapper.CommentMapper;
import ru.promauto.electron3d.notepad.service.CommentService;

@RestController
@RequestMapping("/users/{userId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createComment(@PathVariable Long userId,
                                      @RequestBody CommentDto commentDto) {
        commentService.create(userId, commentMapper.dtoToEntity(commentDto));
        return new RestResponse("Comment created.");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getCommentById(@PathVariable Long userId,
                                       @PathVariable Long id) {
        return new RestResponse(commentMapper.entityToDto(commentService.findById(userId, id)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getAllCommentsForUserId(@PathVariable Long userId) {
        return new RestResponse(commentService.findAll(userId)
                .stream()
                .map(commentMapper::entityToDto)
                .toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RestResponse deleteCommentById(@PathVariable Long userId,
                                          @PathVariable Long id) {
        commentService.deleteById(userId, id);
        return new RestResponse("Comment with ID \"" + id + "\" deleted.");
    }
}
