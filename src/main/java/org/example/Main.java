package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.models.Hero;
import org.example.repository.HeroRepository;
import org.example.repository.HeroRepositoryImpl;
import org.example.controller.HeroCardsViewController;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HeroRepository heroRepository = new HeroRepositoryImpl();
        List<Hero> heroes = heroRepository.findAll();

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/org/example/HeroCardsView.fxml"));
        Parent view = loader.load();
        HeroCardsViewController controller = loader.getController();
        controller.setHeroes(heroes);

        stage.setTitle("Heroes");
        stage.setScene(new Scene(view, 900, 600));
        stage.show();
    }
}