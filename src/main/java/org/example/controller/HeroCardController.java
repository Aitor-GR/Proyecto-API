package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.models.Description;
import org.example.models.Hero;

import java.util.Map;
import java.util.List;

/** Rellena una tarjeta de la interfaz con los datos de un héroe. */
public class HeroCardController {

    @FXML
    private ImageView heroImageView;

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

    // Coloca los datos del héroe en los controles de su tarjeta.
    public void setHero(Hero hero) {
        setHeroImage(hero.getImages());
        nameLabel.setText(valueOrDefault(hero.getName(), "Hero #" + hero.getId()));
        typeLabel.setText(joinValues(hero.getHeroType(), hero.getClassName()));

        Description description = hero.getDescription();
        setDetail(roleLabel, "Rol", description == null ? null : description.getRole());
        setDetail(playstyleLabel, "Estilo", description == null ? null : description.getPlaystyle());
        setDetail(tagsLabel, "Tags", joinTags(hero.getTags()));
    }

    // Busca una imagen disponible y la muestra en la tarjeta.
    private void setHeroImage(Map<String, String> images) {
        String imageUrl = images == null ? null : images.get("icon_hero_card");
        if (imageUrl == null || imageUrl.isBlank()) {
            imageUrl = images == null ? null : images.get("icon_image_small");
        }

        if (imageUrl == null || imageUrl.isBlank()) {
            heroImageView.setImage(null);
            heroImageView.setVisible(false);
            heroImageView.setManaged(false);
            return;
        }

        heroImageView.setImage(new Image(imageUrl, true));
        heroImageView.setVisible(true);
        heroImageView.setManaged(true);
    }

    // Muestra un dato solo cuando tiene contenido.
    private void setDetail(Label label, String title, String value) {
        boolean visible = value != null && !value.isBlank();
        label.setVisible(visible);
        label.setManaged(visible);
        if (visible) {
            label.setText(title + ": " + value);
        }
    }

    // Une las etiquetas para mostrarlas en una línea.
    private String joinTags(List<String> tags) {
        return tags == null || tags.isEmpty() ? null : String.join(", ", tags);
    }

    // Combina dos textos cuando ambos tienen contenido.
    private String joinValues(String first, String second) {
        if (first == null || first.isBlank()) {
            return valueOrDefault(second, "");
        }
        return second == null || second.isBlank() ? first : first + " - " + second;
    }

    // Devuelve un valor alternativo si el texto está vacío.
    private String valueOrDefault(String value, String defaultValue) {
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
