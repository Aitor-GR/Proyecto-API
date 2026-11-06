package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import org.example.models.Hero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HeroCardsViewController {

    @FXML
    private TilePane cardsPane;

    private List<Hero> heroes = List.of();

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        Objects.requireNonNull(heroes, "heroes must not be null");
        this.heroes = Collections.unmodifiableList(new ArrayList<>(heroes));
        cardsPane.getChildren().clear();

        for (Hero hero : this.heroes) {
            cardsPane.getChildren().add(createCard(
                    Objects.requireNonNull(hero, "heroes must not contain null elements")));
        }
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
