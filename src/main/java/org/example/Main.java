package org.example;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Punto de entrada de la aplicación. Carga las ventanas y coordina el inicio. */
public class Main extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private HeroCardsViewController heroCardsController;
    private List<Hero> heroes = List.of();
    private String username;
    private final ObservableList<Hero> dleAttempts = FXCollections.observableArrayList();

    // Inicia la aplicación JavaFX.
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    // Prepara y muestra la pantalla inicial de acceso.
    public void start(Stage stage) throws Exception {
        FXMLLoader loginLoader = new FXMLLoader(
                Main.class.getResource("/org/example/Login.fxml"));
        Parent loginView = loginLoader.load();
        LoginController loginController = loginLoader.getController();
        AuthService authService = new AuthService();
        loginController.setAuthService(authService);
        synchronizeHeroesInBackground(stage);
        loginController.setOnLoginSuccess(() -> {
            try {
                showHeroes(stage, authService.getAuthenticatedUsername());
            } catch (Exception exception) {
                logger.error("No se pudo abrir la vista de héroes", exception);
            }
        });

        stage.setTitle("Iniciar sesión");
        stage.setScene(new Scene(loginView));
        stage.show();
    }

    // Lanza la sincronización en segundo plano para no bloquear la ventana.
    private void synchronizeHeroesInBackground(Stage owner) {
        Task<Integer> synchronizationTask = new Task<>() {
            @Override
            // Ejecuta la sincronización dentro de la tarea de JavaFX.
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
        synchronizationTask.setOnFailed(event -> {
            Throwable exception = synchronizationTask.getException();
            logger.error("No se pudieron sincronizar los héroes desde la API", exception);

            Alert alert = new Alert(
                    Alert.AlertType.ERROR,
                    "No se pudieron sincronizar los héroes con la API.\n"
                            + exception.getMessage(),
                    ButtonType.OK
            );
            alert.setTitle("Error de sincronización");
            alert.setHeaderText(null);
            alert.initOwner(owner);
            alert.showAndWait();
        });

        Thread synchronizationThread = new Thread(synchronizationTask, "hero-api-synchronization");
        synchronizationThread.setDaemon(true);
        synchronizationThread.start();
    }

    // Guarda el usuario autenticado y carga la pantalla principal.
    private void showHeroes(Stage stage, String username) throws Exception {
        this.username = username;
        HeroRepository heroRepository = new HeroRepositoryImpl();
        heroes = heroRepository.findAll();

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/org/example/HeroCardsView.fxml"));
        Parent view = loader.load();
        heroCardsController = loader.getController();
        heroCardsController.setHeroes(heroes);
        heroCardsController.setOnOpenDle(() -> {
            try {
                showDle(stage);
            } catch (Exception exception) {
                logger.error("No se pudo abrir la vista DLE", exception);
            }
        });

        stage.setTitle("Heroes");
        stage.setScene(new Scene(view, 900, 600));
        stage.show();
    }

    // Abre el juego diario y le pasa los datos que necesita.
    private void showDle(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/org/example/DleView.fxml"));
        Parent view = loader.load();
        org.example.controller.DleController controller = loader.getController();
        controller.setHeroes(heroes);
        controller.setUsername(username);
        controller.setAttempts(dleAttempts);
        controller.setOnBackToCards(() -> {
            try {
                showHeroes(stage, username);
            } catch (Exception exception) {
                logger.error("No se pudo volver al listado de héroes", exception);
            }
        });

        stage.setTitle("DLE de héroes");
        stage.setScene(new Scene(view, 900, 600));
        stage.show();
    }
}
