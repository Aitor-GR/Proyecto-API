package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.example.models.Hero;
import org.example.repository.HeroRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class HeroSyncService {

    private static final URI HEROES_URI =
            URI.create("https://api.deadlock-api.com/v1/assets/heroes");

    private final HeroRepository heroRepository;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public HeroSyncService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
        this.objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    public int synchronize() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(HEROES_URI)
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException(
                    "La API de héroes respondió con HTTP " + response.statusCode()
            );
        }

        List<Hero> heroes = objectMapper.readValue(
                response.body(),
                new TypeReference<>() {
                }
        );

        int synchronizedHeroes = 0;
        for (Hero hero : heroes) {
            if (heroRepository.findById(hero.getId()) == null) {
                heroRepository.save(hero);
            } else {
                heroRepository.update(hero.getId(), hero);
            }
            synchronizedHeroes++;
        }

        return synchronizedHeroes;
    }
}
