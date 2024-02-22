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
@Table(name="notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Enumerated(EnumType.STRING)
    private AccessModifier accessModifier;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    @Enumerated(EnumType.STRING)
    private Tag tag;
    @ElementCollection
    @CollectionTable(name = "note_check_list", joinColumns = @JoinColumn(name = "note_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, Boolean> checkList;
}
