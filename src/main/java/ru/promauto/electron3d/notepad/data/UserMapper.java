package ru.promauto.electron3d.notepad.data;

import org.springframework.stereotype.Component;
import ru.promauto.electron3d.notepad.data.dto.UserDto;
import ru.promauto.electron3d.notepad.data.entity.User;

@Component
public class UserMapper {
    public User dtoToEntity(UserDto userDto) {
        User user = new User();
        //todo implement
        return user;
    }
    public UserDto entityToDto(User User) {
        UserDto userDto = new UserDto();
        //todo implement
        return userDto;
    }
}
