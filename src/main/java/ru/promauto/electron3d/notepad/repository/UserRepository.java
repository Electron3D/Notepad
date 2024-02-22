package ru.promauto.electron3d.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.promauto.electron3d.notepad.data.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.nickname = :nickname")
    Optional<User> findByNickname(@Param("nickname") String nickname);
}
