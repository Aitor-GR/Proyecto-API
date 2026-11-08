package org.example.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/** Comprueba que JsonMapper transforma correctamente los datos JSON de héroes. */
class JsonMapperTest {

    // Verifica que se leen los nombres de campos de la API en snake_case.
    @Test
    void readsSnakeCaseHeroData(@TempDir Path tempDir) throws Exception {
        Path source = tempDir.resolve("heroes.json");
        Files.writeString(source, """
                [{"id": 3, "hero_type": "Mystic", "player_selectable": true}]
                """);

        var heroes = new JsonMapper().readHeroes(source.toString());

        assertEquals(1, heroes.size());
        assertEquals(3, heroes.get(0).getId());
        assertEquals("Mystic", heroes.get(0).getHeroType());
        assertTrue(heroes.get(0).isPlayerSelectable());
    }
}
