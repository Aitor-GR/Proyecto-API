package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.models.Description;
import org.example.models.Hero;

import java.util.List;

public class HeroCardController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label playstyleLabel;

    @FXML
    private Label tagsLabel;

    public void setHero(Hero hero) {
        nameLabel.setText(valueOrDefault(hero.getName(), "Hero #" + hero.getId()));
        typeLabel.setText(joinValues(hero.getHeroType(), hero.getClassName()));

        Description description = hero.getDescription();
        setDetail(roleLabel, "Rol", description == null ? null : description.getRole());
        setDetail(playstyleLabel, "Estilo", description == null ? null : description.getPlaystyle());
        setDetail(tagsLabel, "Tags", joinTags(hero.getTags()));
    }

    private void setDetail(Label label, String title, String value) {
        boolean visible = value != null && !value.isBlank();
        label.setVisible(visible);
        label.setManaged(visible);
        if (visible) {
            label.setText(title + ": " + value);
        }
    }

    private String joinTags(List<String> tags) {
        return tags == null || tags.isEmpty() ? null : String.join(", ", tags);
    }

    private String joinValues(String first, String second) {
        if (first == null || first.isBlank()) {
            return valueOrDefault(second, "");
        }
        return second == null || second.isBlank() ? first : first + " - " + second;
    }

    private String valueOrDefault(String value, String defaultValue) {
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
