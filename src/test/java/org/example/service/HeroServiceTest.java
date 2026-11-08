package org.example.service;

import org.example.models.Hero;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/** Comprueba las búsquedas, filtros y agrupaciones de HeroService. */
class HeroServiceTest {

    // Comprueba varias operaciones de búsqueda, ordenación y filtrado.
    @Test
    void searchesSortsAndFiltersHeroes() {
        Hero zeus = hero("Zeus", "Mystic", List.of("Mage", "Flying"), true);
        Hero abaddon = hero("Abaddon", null, List.of("Tank"), false);
        HeroService service = new HeroService(List.of(zeus, abaddon));

        assertEquals(List.of(zeus), service.buscarPorNombre("eus"));
        assertEquals(List.of("Zeus", "Abaddon"), service.obtenerNombres());
        assertEquals(List.of(abaddon, zeus), service.ordenarPorNombre());
        assertEquals(List.of(zeus), service.obtenerHeroesJugables());
        assertTrue(service.existeHeroe("ABADDON"));
    }

    // Comprueba que se agrupan etiquetas y héroes aunque falte el tipo.
    @Test
    void aggregatesTagsAndTypesIncludingMissingType() {
        Hero first = hero("First", "Mystic", List.of("Mage", "Tank"), true);
        Hero second = hero("Second", null, List.of("mage"), true);
        HeroService service = new HeroService(List.of(first, second));

        assertEquals(List.of("Mage", "Tank", "mage"), service.obtenerTodosLosTags());
        assertEquals(Map.of("Mystic", 1L, "Sin tipo", 1L),
                service.contarHeroesPorTipo());
        assertEquals(List.of(first), service.buscarPorTag("TANK"));
    }

    // Crea un héroe sencillo para reutilizarlo en las pruebas.
    private static Hero hero(String name, String type, List<String> tags, boolean playable) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setHeroType(type);
        hero.setTags(tags);
        hero.setPlayerSelectable(playable);
        return hero;
    }
}
