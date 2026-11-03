package org.example.repository;

import java.util.List;

public interface TagRepository {

    List<String> findAll();

    String findById(int id);

    Integer findIdByName(String name);

    Integer save(String name);

    Integer getOrCreateTag(String name);

}