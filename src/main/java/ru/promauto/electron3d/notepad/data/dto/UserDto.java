package ru.promauto.electron3d.notepad.data.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotNull
    @NotBlank
    private String nickname;
    private List<String> notes;
    private List<String> comments;
}
