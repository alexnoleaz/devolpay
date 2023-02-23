package com.develtrex.repositories;

import java.util.List;

public interface BaseRepository<T> {
    List<T> getAll();

    T getById(String id);

    T insert(T entity);

    T update(T entity);

    boolean existsById(String id);

    void deleteById(String id);

    void deleteAll();
}
