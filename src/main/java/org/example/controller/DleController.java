package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.models.Hero;
import org.example.service.DleService;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;

public class DleController {

    @FXML
    private TextField guessField;

    @FXML
    private ListView<String> suggestionsList;

    @FXML
    private Button guessButton;

    @FXML
    private Label feedbackLabel;

    @FXML
    private Label streakLabel;

    @FXML
    private TableView<Hero> attemptsTable;

    @FXML
    private TableColumn<Hero, Hero> imageColumn;

    @FXML
    private TableColumn<Hero, String> nameColumn;

    @FXML
    private TableColumn<Hero, String> genderColumn;

    @FXML
    private TableColumn<Hero, String> gunTagColumn;

    @FXML
    private TableColumn<Hero, String> heroTypeColumn;

    private final DleService dleService = new DleService();
    private ObservableList<Hero> attempts = FXCollections.observableArrayList();
    private List<Hero> heroes = List.of();
    private Hero dailyHero;
    private String username;
    private Consumer<Void> onBackToCards = ignored -> {
    };

    @FXML
    private void initialize() {
        configureAttemptTable();
        guessField.textProperty().addListener((observable, oldValue, newValue) ->
                updateSuggestions(newValue));
        suggestionsList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selectSuggestion();
            }
        });
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = List.copyOf(Objects.requireNonNull(heroes, "heroes must not be null"));
        dailyHero = selectDailyHero();
        attemptsTable.refresh();
        if (username != null) {
            refreshStreak();
        }
    }

    public void setUsername(String username) {
        this.username = Objects.requireNonNull(username, "username must not be null");
        refreshStreak();
    }

    public void setAttempts(ObservableList<Hero> attempts) {
        this.attempts = Objects.requireNonNull(attempts, "attempts must not be null");
        attemptsTable.setItems(this.attempts);
        attemptsTable.refresh();
    }

    public void setOnBackToCards(Runnable onBackToCards) {
        this.onBackToCards = ignored -> onBackToCards.run();
    }

    @FXML
    private void checkGuess() {
        String guessText = guessField.getText();
        if (dailyHero == null || guessText.isBlank()) {
            feedbackLabel.setText("Escribe el nombre de un héroe.");
            return;
        }

        Hero guessedHero = findHero(guessText);
        if (guessedHero == null) {
            feedbackLabel.setText("Selecciona un héroe de la lista de sugerencias.");
            return;
        }

        attempts.remove(guessedHero);
        attempts.add(0, guessedHero);
        suggestionsList.getItems().removeIf(name ->
                normalize(name).equals(normalize(guessedHero.getName())));
        attemptsTable.refresh();
        guessField.clear();
        suggestionsList.setVisible(false);
        suggestionsList.setManaged(false);

        if (!normalize(dailyHero.getName()).equals(normalize(guessedHero.getName()))) {
            feedbackLabel.setText("No es ese héroe. ¡Sigue intentándolo!");
            return;
        }

        try {
            int streak = dleService.recordWin(username, LocalDate.now());
            feedbackLabel.setText("¡Correcto! El héroe era " + dailyHero.getName() + ".");
            streakLabel.setText("Racha: " + streak + " día" + (streak == 1 ? "" : "s"));
            guessField.setDisable(true);
            guessButton.setDisable(true);
        } catch (IllegalStateException exception) {
            showError(exception.getMessage());
        }
    }

    @FXML
    private void backToCards() {
        onBackToCards.accept(null);
    }

    private void refreshStreak() {
        if (streakLabel != null) {
            int streak = dleService.getStreak(username);
            streakLabel.setText("Racha: " + streak + " día" + (streak == 1 ? "" : "s"));
        }
    }

    private Hero selectDailyHero() {
        if (heroes.isEmpty()) {
            return null;
        }
        int index = Math.floorMod(LocalDate.now().toEpochDay(), heroes.size());
        return heroes.get(index);
    }

    private Hero findHero(String name) {
        return heroes.stream()
                .filter(hero -> normalize(hero.getName()).equals(normalize(name)))
                .findFirst()
                .orElse(null);
    }

    private void updateSuggestions(String text) {
        if (text == null || text.isBlank()) {
            hideSuggestions();
            return;
        }

        String normalizedText = normalize(text);
        List<String> suggestions = heroes.stream()
                .map(Hero::getName)
                .filter(Objects::nonNull)
                .filter(name -> attempts.stream()
                        .noneMatch(attempt -> normalize(name).equals(normalize(attempt.getName()))))
                .filter(name -> normalize(name).contains(normalizedText))
                .toList();
        suggestionsList.getItems().setAll(suggestions);
        if (suggestions.isEmpty()) {
            hideSuggestions();
        } else {
            suggestionsList.setVisible(true);
            suggestionsList.setManaged(true);
        }
    }

    private void selectSuggestion() {
        String selected = suggestionsList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        guessField.setText(selected);
        guessField.positionCaret(selected.length());
        hideSuggestions();
    }

    private void hideSuggestions() {
        suggestionsList.getSelectionModel().clearSelection();
        suggestionsList.setVisible(false);
        suggestionsList.setManaged(false);
    }

    private void configureAttemptTable() {
        attemptsTable.setItems(attempts);
        imageColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue()));
        nameColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        genderColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getGender()));
        gunTagColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getGunTag()));
        heroTypeColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getHeroType()));

        imageColumn.setCellFactory(column -> new TableCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Hero hero, boolean empty) {
                super.updateItem(hero, empty);
                setGraphic(null);
                if (!empty && hero != null) {
                    String imageUrl = getImageUrl(hero);
                    if (imageUrl != null) {
                        imageView.setImage(new Image(imageUrl, true));
                        imageView.setFitWidth(48);
                        imageView.setFitHeight(48);
                        imageView.setPreserveRatio(true);
                        setGraphic(imageView);
                    }
                    markMatch(this, imageUrl != null && imageUrl.equals(getImageUrl(dailyHero)));
                } else {
                    setStyle("");
                }
            }
        });
        nameColumn.setCellFactory(column -> textCell(hero -> hero.getName()));
        genderColumn.setCellFactory(column -> textCell(hero -> hero.getGender()));
        gunTagColumn.setCellFactory(column -> textCell(hero -> hero.getGunTag()));
        heroTypeColumn.setCellFactory(column -> textCell(hero -> hero.getHeroType()));
    }

    private TableCell<Hero, String> textCell(java.util.function.Function<Hero, String> valueProvider) {
        return new TableCell<>() {
            @Override
            protected void updateItem(String value, boolean empty) {
                super.updateItem(value, empty);
                if (empty) {
                    setText(null);
                    setStyle("");
                    return;
                }
                setText(displayValue(value));
                Hero hero = getTableRow() == null ? null : getTableRow().getItem();
                if (hero == null) {
                    setStyle("");
                    return;
                }
                markMatch(this, sameValue(value, valueProvider.apply(dailyHero)));
            }
        };
    }

    private void markMatch(TableCell<?, ?> cell, boolean matches) {
        cell.setStyle(matches
                ? "-fx-background-color: #c6efce; -fx-text-fill: #006100;"
                : "-fx-background-color: #ffc7ce; -fx-text-fill: #9c0006;");
    }

    private boolean sameValue(String first, String second) {
        return first != null && second != null && normalize(first).equals(normalize(second));
    }

    private String displayValue(String value) {
        return value == null || value.isBlank() ? "-" : value;
    }

    private String getImageUrl(Hero hero) {
        if (hero == null || hero.getImages() == null) {
            return null;
        }
        String imageUrl = hero.getImages().get("icon_hero_card");
        return imageUrl == null || imageUrl.isBlank()
                ? hero.getImages().get("icon_image_small")
                : imageUrl;
    }

    private String normalize(String value) {
        return value.trim().toLowerCase(Locale.ROOT);
    }

    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait();
    }
}
