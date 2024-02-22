package ru.promauto.electron3d.notepad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.promauto.electron3d.notepad.data.RestResponse;
import ru.promauto.electron3d.notepad.data.mapper.UserMapper;
import ru.promauto.electron3d.notepad.data.dto.UserDto;
import ru.promauto.electron3d.notepad.service.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(
        name = "CRUD for Users entities"
)
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create user")
    public RestResponse createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body in JSON format",
                    required = true) @RequestBody @Valid UserDto userDto) {
        userService.create(userMapper.dtoToEntity(userDto));
        return new RestResponse("User created");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all users")
    public RestResponse getAllUsers() {
        return new RestResponse(userService.findAll()
                .stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get user by ID")
    public RestResponse getUserById(@Parameter(description = "user ID", required = true) @PathVariable Long id) {
        return new RestResponse(userMapper.entityToDto(userService.findById(id)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update user by ID")
    public RestResponse updateUserById(@Parameter(description = "user ID", required = true) @PathVariable Long id,
                                       @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                               description = "Request body in JSON format",
                                               required = true) @RequestBody UserDto userDto) {
        userService.updateById(id, userMapper.dtoToEntity(userDto));
        return new RestResponse("User with ID \"" + id + "\" updated.");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID")
    public RestResponse deleteUserById(@Parameter(description = "user ID", required = true) @PathVariable Long id) {
        userService.deleteById(id);
        return new RestResponse("User with ID: \"" + id + "\" deleted");
    }
}
