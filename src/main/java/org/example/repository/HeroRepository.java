package org.example.repository;

import java.util.List;

public interface HeroRepository {
    List<Hero> findByNombre(String nombre);
}
