package org.example.repository;

import java.util.List;

/** Declara las operaciones CRUD comunes de los repositorios. */
public interface CrudRepository<T> {
    List<T> findAll();
    T findById(int id);
    T save(T item);
    T update(int id, T item);
    T delete(int id);
}