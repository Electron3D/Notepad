package ru.promauto.electron3d.notepad.data.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoteDto {
    @NotNull
    private String text;
    @Pattern(regexp = "^(PUBLIC|PRIVATE)$", message = "Only 'PUBLIC' and 'PRIVATE' values is allowed")
    private String accessModifier;
    private String userNickname;
    private List<String> comments;
    private String tag;
    private Map<String, Boolean> checkList;
}
