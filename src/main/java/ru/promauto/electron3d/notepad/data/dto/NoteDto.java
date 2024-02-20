package ru.promauto.electron3d.notepad.data.dto;

import jakarta.validation.constraints.NotNull;
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
    private String userNickname;
    private List<String> comments;
    private String tag;
    private Map<String, Boolean> checkList;
}
