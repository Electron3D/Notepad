package ru.promauto.electron3d.notepad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.promauto.electron3d.notepad.data.RestResponse;
import ru.promauto.electron3d.notepad.data.UserMapper;
import ru.promauto.electron3d.notepad.data.dto.UserDto;
import ru.promauto.electron3d.notepad.service.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse createUser(@RequestBody UserDto userDto) {
        userService.create(userMapper.dtoToEntity(userDto));
        return new RestResponse("User created");
    }

    @GetMapping
    public RestResponse getAllUsers() {
        return new RestResponse(userService.findAll()
                .stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public RestResponse getUserById(@PathVariable Long id) {
        return new RestResponse(userMapper.entityToDto(userService.findById(id)));
    }

    @PutMapping("/{id}")
    public RestResponse updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.updateById(id, userMapper.dtoToEntity(userDto));
        return new RestResponse("User with ID \"" + id + "\" updated.");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RestResponse deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return new RestResponse("User with ID: \"" + id + "\" deleted");
    }
}
