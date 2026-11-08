package org.example;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.models.Hero;
import org.example.repository.HeroRepository;
import org.example.repository.HeroRepositoryImpl;
import org.example.service.AuthService;
import org.example.controller.HeroCardsViewController;
import org.example.service.HeroSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private HeroCardsViewController heroCardsController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loginLoader = new FXMLLoader(
                Main.class.getResource("/org/example/Login.fxml"));
        Parent loginView = loginLoader.load();
        LoginController loginController = loginLoader.getController();
        loginController.setAuthService(new AuthService());
        synchronizeHeroesInBackground();
        loginController.setOnLoginSuccess(() -> {
            try {
                showHeroes(stage);
            } catch (Exception exception) {
                logger.error("No se pudo abrir la vista de héroes", exception);
            }
        });

        stage.setTitle("Iniciar sesión");
        stage.setScene(new Scene(loginView));
        stage.show();
    }

    private void synchronizeHeroesInBackground() {
        Task<Integer> synchronizationTask = new Task<>() {
            @Override
            protected Integer call() throws Exception {
                HeroRepository heroRepository = new HeroRepositoryImpl();
                return new HeroSyncService(heroRepository).synchronize();
            }
        };

        synchronizationTask.setOnSucceeded(event -> {
            logger.info("Se sincronizaron {} héroes desde la API", synchronizationTask.getValue());
            if (heroCardsController != null) {
                heroCardsController.setHeroes(new HeroRepositoryImpl().findAll());
            }
        });
        synchronizationTask.setOnFailed(event ->
                logger.error("No se pudieron sincronizar los héroes desde la API",
                        synchronizationTask.getException()));

        Thread synchronizationThread = new Thread(synchronizationTask, "hero-api-synchronization");
        synchronizationThread.setDaemon(true);
        synchronizationThread.start();
    }

    private void showHeroes(Stage stage) throws Exception {
        HeroRepository heroRepository = new HeroRepositoryImpl();
        List<Hero> heroes = heroRepository.findAll();

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/org/example/HeroCardsView.fxml"));
        Parent view = loader.load();
        heroCardsController = loader.getController();
        heroCardsController.setHeroes(heroes);

        stage.setTitle("Heroes");
        stage.setScene(new Scene(view, 900, 600));
        stage.show();
    }
}