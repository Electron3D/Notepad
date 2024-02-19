package ru.promauto.electron3d.notepad.service;

import ru.promauto.electron3d.notepad.data.entity.User;

import java.util.List;

public interface UserService {
    void create(User user);
    User findById(Long id);
    List<User> findAll();
    void updateById(Long id, User user);
    void deleteById(Long id);
}
