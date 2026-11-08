package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Description;
import org.example.models.Hero;
import org.example.utils.CsvUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/** Prepara y exporta los datos de los héroes en formato CSV. */
public class CsvExportService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final List<String> HEADERS = List.of(
            "id", "name", "searchName", "gender", "className", "heroType",
            "gunTag", "developmentState", "assignedPlayersOnly",
            "inDevelopment", "disabled", "playerSelectable", "needsTesting",
            "hideoutRichPresence", "images", "tags", "descriptionRole",
            "descriptionLore", "descriptionPlaystyle"
    );

    // Comprueba los datos y guarda los héroes en el formato elegido.
    public void exportHeroes(List<Hero> heroes, Path destination) throws IOException {
        Objects.requireNonNull(heroes, "heroes must not be null");
        Objects.requireNonNull(destination, "destination must not be null");

        List<String> rows = new ArrayList<>(heroes.size() + 1);
        rows.add(CsvUtils.toRow(HEADERS));
        for (Hero hero : heroes) {
            rows.add(toRow(Objects.requireNonNull(hero, "heroes must not contain null elements")));
        }
        CsvUtils.write(destination, rows);
    }

    // Convierte los datos de un héroe en una fila CSV.
    private String toRow(Hero hero) {
        Description description = hero.getDescription();
        return CsvUtils.toRow(Arrays.asList(
                hero.getId(),
                hero.getName(),
                hero.getSearchName(),
                hero.getGender(),
                hero.getClassName(),
                hero.getHeroType(),
                hero.getGunTag(),
                hero.getDevelopmentState(),
                hero.isAssignedPlayersOnly(),
                hero.isInDevelopment(),
                hero.isDisabled(),
                hero.isPlayerSelectable(),
                hero.isNeedsTesting(),
                hero.getHideoutRichPresence(),
                serializeImages(hero),
                hero.getTags() == null ? null : String.join("; ", hero.getTags()),
                description == null ? null : description.getRole(),
                description == null ? null : description.getLore(),
                description == null ? null : description.getPlaystyle()
        ));
    }

    private String serializeImages(Hero hero) {
        if (hero.getImages() == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(hero.getImages());
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "No se pudieron serializar las imágenes del héroe " + hero.getId(),
                    exception
            );
        }
    }
}
