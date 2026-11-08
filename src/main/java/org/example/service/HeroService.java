package org.example.service;

import org.example.models.Hero;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Contiene consultas sencillas sobre la lista de héroes. */
public class HeroService {


    private final List<Hero> heroes;


    // Guarda la lista de héroes sobre la que se harán las consultas.
    public HeroService(List<Hero> heroes) {
        this.heroes = heroes;
    }

    // Filtra la lista de héroes por nombre.
    public List<Hero> buscarPorNombre(String nombre) {

        return heroes.stream()
                .filter(hero ->
                        hero.getName()
                                .toLowerCase()
                                .contains(nombre.toLowerCase())
                )
                .toList();
    }

    // Devuelve los héroes ordenados alfabéticamente.
    public List<Hero> ordenarPorNombre() {

        return heroes.stream()
                .sorted(
                        Comparator.comparing(
                                Hero::getName
                        )
                )
                .toList();
    }

    // Devuelve solo los nombres de los héroes.
    public List<String> obtenerNombres() {

        return heroes.stream()
                .map(Hero::getName)
                .toList();
    }

    // Recoge las etiquetas sin repetirlas y las ordena.
    public List<String> obtenerTodosLosTags() {

        return heroes.stream()
                .filter(hero -> hero.getTags() != null)
                .flatMap(hero ->
                        hero.getTags().stream()
                )
                .distinct()
                .sorted()
                .toList();
    }

    // Cuenta cuántos héroes hay en la lista.
    public long contarHeroes() {

        return heroes.stream()
                .count();
    }

    // Agrupa los héroes según su tipo.
    public Map<String, List<Hero>> agruparPorTipo() {

        return heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                hero -> hero.getHeroType() == null
                                        ? "Sin tipo"
                                        : hero.getHeroType()
                        )
                );
    }

    // Comprueba si hay un héroe con ese nombre.
    public boolean existeHeroe(String nombre) {

        return heroes.stream()
                .anyMatch(hero ->
                        hero.getName()
                                .equalsIgnoreCase(nombre)
                );
    }

    // Devuelve el primer héroe o null si la lista está vacía.
    public Hero primerHeroe() {

        return heroes.stream()
                .findFirst()
                .orElse(null);
    }


    // Filtra los héroes que tienen la etiqueta indicada.
    public List<Hero> buscarPorTag(String tag) {

        return heroes.stream()
                .filter(hero ->
                        hero.getTags() != null &&
                                hero.getTags()
                                        .stream()
                                        .anyMatch(
                                                t -> t.equalsIgnoreCase(tag)
                                        )
                )
                .toList();
    }


    // Devuelve los héroes que se pueden elegir para jugar.
    public List<Hero> obtenerHeroesJugables() {

        return heroes.stream()
                .filter(Hero::isPlayerSelectable)
                .toList();
    }


    // Cuenta cuántos héroes hay de cada tipo.
    public Map<String, Long> contarHeroesPorTipo() {

        return heroes.stream()
                .collect(
                        Collectors.groupingBy(
                                hero -> hero.getHeroType() == null
                                        ? "Sin tipo"
                                        : hero.getHeroType(),
                                Collectors.counting()
                        )
                );
    }
}