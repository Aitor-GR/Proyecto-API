package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import org.example.service.CsvExportService;
import org.example.service.JsonExportService;
import org.example.models.Hero;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class HeroCardsViewController {

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> searchFieldSelector;

    @FXML
    private Button exportButton;

    @FXML
    private TilePane cardsPane;

    private List<Hero> heroes = List.of();
    private final CsvExportService csvExportService = new CsvExportService();
    private final JsonExportService jsonExportService = new JsonExportService();

    @FXML
    private void initialize() {
        searchFieldSelector.getItems().addAll(
                "Todos",
                "Nombre",
                "Nombre interno",
                "Tipo",
                "Clase",
                "Tags"
        );
        searchFieldSelector.getSelectionModel().selectFirst();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> renderCards(newValue));
        searchFieldSelector.valueProperty()
                .addListener((observable, oldValue, newValue) -> renderCards(searchField.getText()));
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
            if (matchesSearch(nonNullHero, searchText, searchFieldSelector.getValue())) {
                cardsPane.getChildren().add(createCard(nonNullHero));
            }
        }
    }

    @FXML
    private void exportData() {
        ChoiceDialog<String> formatDialog = new ChoiceDialog<>(
                "CSV",
                "CSV",
                "JSON"
        );
        formatDialog.setTitle("Exportar datos");
        formatDialog.setHeaderText("Selecciona el formato de exportación");
        formatDialog.setContentText("Formato:");

        formatDialog.showAndWait().ifPresent(format -> {
            FileChooser fileChooser = new FileChooser();
            if ("CSV".equals(format)) {
                fileChooser.setTitle("Guardar héroes como CSV");
                fileChooser.setInitialFileName("heroes.csv");
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Archivos CSV (*.csv)", "*.csv")
                );
            } else {
                fileChooser.setTitle("Guardar héroes como JSON");
                fileChooser.setInitialFileName("heroes.json");
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json")
                );
            }

            Window owner = exportButton.getScene().getWindow();
            java.io.File selectedFile = fileChooser.showSaveDialog(owner);
            if (selectedFile == null) {
                return;
            }

            try {
                Path destination = Path.of(selectedFile.toURI());
                if ("CSV".equals(format)) {
                    csvExportService.exportHeroes(heroes, destination);
                } else {
                    jsonExportService.exportHeroes(heroes, destination);
                }
                showInformation("Los héroes se han exportado correctamente.");
            } catch (IOException exception) {
                showError("No se pudieron exportar los héroes.", exception);
            }
        });
    }

    private void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Exportar datos");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void showError(String message, IOException exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message + "\n" + exception.getMessage(), ButtonType.OK);
        alert.setTitle("Error de exportación");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private boolean matchesSearch(Hero hero, String searchText, String selectedField) {
        if (searchText == null || searchText.isBlank()) {
            return true;
        }

        String normalizedSearch = searchText.toLowerCase(Locale.ROOT).trim();
        return switch (selectedField) {
            case "Nombre" -> contains(hero.getName(), normalizedSearch);
            case "Nombre interno" -> contains(hero.getSearchName(), normalizedSearch);
            case "Tipo" -> contains(hero.getHeroType(), normalizedSearch);
            case "Clase" -> contains(hero.getClassName(), normalizedSearch);
            case "Tags" -> hero.getTags() != null
                    && hero.getTags().stream().anyMatch(tag -> contains(tag, normalizedSearch));
            default -> contains(hero.getName(), normalizedSearch)
                    || contains(hero.getSearchName(), normalizedSearch)
                    || contains(hero.getHeroType(), normalizedSearch)
                    || contains(hero.getClassName(), normalizedSearch)
                    || (hero.getTags() != null
                    && hero.getTags().stream().anyMatch(tag -> contains(tag, normalizedSearch)));
        };
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
