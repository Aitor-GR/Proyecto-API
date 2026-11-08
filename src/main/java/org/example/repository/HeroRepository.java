package org.example.repository;

import org.example.models.Hero;

import java.util.List;

/** Define las operaciones disponibles para trabajar con héroes. */
public interface HeroRepository extends CrudRepository<Hero> {

    List<Hero> findByNombre(String nombre);

}