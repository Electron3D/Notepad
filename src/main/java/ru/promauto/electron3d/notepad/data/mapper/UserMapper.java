package ru.promauto.electron3d.notepad.data.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.promauto.electron3d.notepad.data.dto.UserDto;
import ru.promauto.electron3d.notepad.data.entity.Comment;
import ru.promauto.electron3d.notepad.data.entity.Note;
import ru.promauto.electron3d.notepad.data.entity.User;

@Component
@RequiredArgsConstructor
public class UserMapper extends AbstractMapper<User, UserDto> {
    @Override
    public User dtoToEntity(UserDto userDto) {
        User user = new User();
        user.setNickname(userDto.getNickname());
        return user;
    }

    @Override
    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setNickname(user.getNickname());
        userDto.setNotes(user.getNotes()
                .stream()
                .map(Note::getText)
                .toList());
        userDto.setComments(user.getComments()
                .stream()
                .map(Comment::getText)
                .toList());
        return userDto;
    }
}
