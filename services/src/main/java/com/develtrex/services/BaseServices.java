package com.develtrex.services;


import java.util.List;

public interface BaseServices<T> {
    List<T> getAll();
    T getById(String id);
    T create(T entity);
    T update(T entity);
    boolean deleteById(String id);
    void deleteAll();
}
