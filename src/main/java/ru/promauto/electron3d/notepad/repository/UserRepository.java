package ru.promauto.electron3d.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.promauto.electron3d.notepad.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
