package ru.promauto.electron3d.notepad.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
    @NotNull
    @NotBlank
    private String text;
    @NotNull
    @NotBlank
    private String userNickname;
    private Long parentCommentId;
}
