package ru.promauto.electron3d.notepad.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "note", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    @Enumerated(EnumType.STRING)
    private Tag tag;
    @ElementCollection
    private Map<String, Boolean> checkList;
}
