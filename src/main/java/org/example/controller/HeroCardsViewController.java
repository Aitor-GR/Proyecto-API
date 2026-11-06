package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import org.example.models.Hero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class HeroCardsViewController {

    @FXML
    private TextField searchField;

    @FXML
    private TilePane cardsPane;

    private List<Hero> heroes = List.of();

    @FXML
    private void initialize() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> renderCards(newValue));
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        Objects.requireNonNull(heroes, "heroes must not be null");
        this.heroes = Collections.unmodifiableList(new ArrayList<>(heroes));
        renderCards(searchField.getText());
    }

    private void renderCards(String searchText) {
        cardsPane.getChildren().clear();

        for (Hero hero : this.heroes) {
            Hero nonNullHero = Objects.requireNonNull(hero, "heroes must not contain null elements");
            if (matchesSearch(nonNullHero, searchText)) {
                cardsPane.getChildren().add(createCard(nonNullHero));
            }
        }
    }

    private boolean matchesSearch(Hero hero, String searchText) {
        if (searchText == null || searchText.isBlank()) {
            return true;
        }

        String normalizedSearch = searchText.toLowerCase(Locale.ROOT).trim();
        return contains(hero.getName(), normalizedSearch)
                || contains(hero.getSearchName(), normalizedSearch)
                || contains(hero.getHeroType(), normalizedSearch)
                || contains(hero.getClassName(), normalizedSearch)
                || (hero.getTags() != null
                && hero.getTags().stream().anyMatch(tag -> contains(tag, normalizedSearch)));
    }

    private boolean contains(String value, String searchText) {
        return value != null && value.toLowerCase(Locale.ROOT).contains(searchText);
    }

    private Node createCard(Hero hero) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    HeroCardsViewController.class.getResource("/org/example/HeroCard.fxml"));
            Node card = loader.load();
            HeroCardController controller = loader.getController();
            controller.setHero(hero);
            return card;
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load HeroCard.fxml", exception);
        }
    }
}
