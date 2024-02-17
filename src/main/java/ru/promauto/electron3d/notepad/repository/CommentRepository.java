package ru.promauto.electron3d.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.promauto.electron3d.notepad.data.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
