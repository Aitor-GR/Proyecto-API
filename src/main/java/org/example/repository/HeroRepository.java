package org.example.repository;

import org.example.models.Hero;

import java.util.List;

public interface HeroRepository {
    List<Hero> findByNombre(String nombre);
}
