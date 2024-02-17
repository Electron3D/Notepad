package ru.promauto.electron3d.notepad.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//todo add validation
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoteDto {
    private String text;
    private List<String> comments;
    private String tag;
}
