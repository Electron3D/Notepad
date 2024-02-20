package ru.promauto.electron3d.notepad.controller;

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
public class TagController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse getTags() {
        return new RestResponse(Tag.values());
    }
}
