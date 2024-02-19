package ru.promauto.electron3d.notepad.service.impl;

import org.springframework.stereotype.Service;
import ru.promauto.electron3d.notepad.data.entity.User;
import ru.promauto.electron3d.notepad.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void create(User user) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void updateById(Long id, User user) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
