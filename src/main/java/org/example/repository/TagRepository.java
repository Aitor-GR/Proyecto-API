package org.example.repository;

import java.util.List;

/** Define las operaciones disponibles para trabajar con etiquetas. */
public interface TagRepository {

    List<String> findAll();

    String findById(int id);

    Integer findIdByName(String name);

    Integer save(String name);

    Integer getOrCreateTag(String name);

}