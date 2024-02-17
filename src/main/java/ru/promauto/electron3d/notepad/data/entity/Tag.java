package ru.promauto.electron3d.notepad.data.entity;

public enum Tag {
    IMPORTANT("Important"),
    ARCHIVE("Archive"),
    DEPRECATED("Deprecated"),
    REGULAR("Regular");

    Tag(String name) {
        this.name = name;
    }
    final String name;
}
