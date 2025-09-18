package com.equipo4hackathonf5.CronoSana.implementations;

import java.util.List;

public interface IService <T, S> {
    public List<T> getEntities();
    public T createEntity(S dto);
    public T getById(Long id);
    public T updateEntity(Long id, S dto);
    public void deleteEntity(Long id);
}
