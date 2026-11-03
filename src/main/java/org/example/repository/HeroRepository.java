package org.example.repository;

import org.example.models.Hero;

import java.util.List;

public interface HeroRepository extends CrudRepository<Hero> {

    List<Hero> findByNombre(String nombre);

}