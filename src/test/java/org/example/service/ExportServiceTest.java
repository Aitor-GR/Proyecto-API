package org.example.service;

import org.example.models.Hero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Comprueba las exportaciones de héroes a CSV y JSON. */
class ExportServiceTest {

    // Verifica la cabecera CSV, el escape de comas y la unión de etiquetas.
    @Test
    void exportsCsvWithHeaderAndEscapedValues(@TempDir Path tempDir) throws Exception {
        Hero hero = new Hero();
        hero.setId(7);
        hero.setName("A,Hero");
        hero.setTags(List.of("Tank", "Frontline"));

        Path destination = tempDir.resolve("heroes.csv");
        new CsvExportService().exportHeroes(List.of(hero), destination);

        String content = Files.readString(destination);
        assertTrue(content.startsWith("id,name,searchName"));
        assertTrue(content.contains("\"A,Hero\""));
        assertTrue(content.contains("Tank; Frontline"));
    }

    // Comprueba la exportación JSON y el rechazo de elementos nulos.
    @Test
    void exportsJsonAndRejectsNullElements(@TempDir Path tempDir) throws Exception {
        Path destination = tempDir.resolve("heroes.json");
        Hero hero = new Hero();
        hero.setName("Aegis");

        new JsonExportService().exportHeroes(List.of(hero), destination);

        assertTrue(Files.readString(destination).contains("\"name\" : \"Aegis\""));
        assertThrows(NullPointerException.class,
                () -> new JsonExportService().exportHeroes(List.of(hero, null), destination));
    }
}
