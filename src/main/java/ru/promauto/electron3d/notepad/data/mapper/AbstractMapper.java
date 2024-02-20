package ru.promauto.electron3d.notepad.data.mapper;

public abstract class AbstractMapper<E, D> {
    public abstract E dtoToEntity(D dto);
    public abstract D entityToDto(E entity);
}
