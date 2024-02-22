package ru.promauto.electron3d.notepad.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promauto.electron3d.notepad.data.entity.User;
import ru.promauto.electron3d.notepad.exception.AlreadyExistException;
import ru.promauto.electron3d.notepad.exception.NotFoundException;
import ru.promauto.electron3d.notepad.repository.UserRepository;
import ru.promauto.electron3d.notepad.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(User user) {
        if (userRepository.findByNickname(user.getNickname()).isPresent()) {
            throw new AlreadyExistException("The nickname is already in use.");
        }
        user.setNotes(new ArrayList<>());
        user.setComments(new ArrayList<>());
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID: \"" + id + "\" not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void updateById(Long id, User user) {
        User existedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID: \"" + id + "\" not found."));
        existedUser.setNickname(user.getNickname());
        userRepository.save(existedUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
