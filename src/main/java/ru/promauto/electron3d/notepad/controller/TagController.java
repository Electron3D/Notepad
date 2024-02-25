package ru.promauto.electron3d.notepad.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.promauto.electron3d.notepad.data.RestResponse;
import ru.promauto.electron3d.notepad.data.entity.Tag;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
@io.swagger.v3.oas.annotations.tags.Tag(
        name = "Endpoint to receive all allowed tags"
)
public class TagController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all tags")
    public RestResponse getTags() {
        return new RestResponse(Tag.values());
    }
}
