package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.models.Hero;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class JsonExportService {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public void exportHeroes(List<Hero> heroes, Path destination) throws IOException {
        Objects.requireNonNull(heroes, "heroes must not be null");
        Objects.requireNonNull(destination, "destination must not be null");
        heroes.forEach(hero -> Objects.requireNonNull(hero, "heroes must not contain null elements"));
        objectMapper.writeValue(destination.toFile(), heroes);
    }
}
