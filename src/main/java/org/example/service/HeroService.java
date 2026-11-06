package org.example.service;

import org.example.models.Hero;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HeroService {


    private final List<Hero> heroes;


    public HeroService(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public List<Hero> buscarPorNombre(String nombre) {

        return heroes.stream()
                .filter(hero ->
                        hero.getName()
                                .toLowerCase()
                                .contains(nombre.toLowerCase())
                )
                .toList();
    }

    public List<Hero> ordenarPorNombre() {

        return heroes.stream()
                .sorted(
                        Comparator.comparing(
                                Hero::getName
                        )
                )
                .toList();
    }

    public List<String> obtenerNombres() {

        return heroes.stream()
                .map(Hero::getName)
                .toList();
    }

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

    public long contarHeroes() {

        return heroes.stream()
                .count();
    }

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

    public boolean existeHeroe(String nombre) {

        return heroes.stream()
                .anyMatch(hero ->
                        hero.getName()
                                .equalsIgnoreCase(nombre)
                );
    }

    public Hero primerHeroe() {

        return heroes.stream()
                .findFirst()
                .orElse(null);
    }


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


    public List<Hero> obtenerHeroesJugables() {

        return heroes.stream()
                .filter(Hero::isPlayerSelectable)
                .toList();
    }


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