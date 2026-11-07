package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.service.AuthService;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    private AuthService authService;
    private boolean registrationMode;
    private Runnable onLoginSuccess = () -> {
    };

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    @FXML
    private void login(ActionEvent event) {
        if (registrationMode) {
            registerUser();
            return;
        }

        if (authService.authenticate(usernameField.getText(), passwordField.getText())) {
            onLoginSuccess.run();
            return;
        }

        Alert alert = new Alert(
                Alert.AlertType.ERROR,
                "Usuario o contraseña incorrectos.",
                ButtonType.OK
        );
        alert.setTitle("Error de autenticación");
        alert.setHeaderText(null);
        alert.showAndWait();
        passwordField.clear();
    }

    @FXML
    private void register(ActionEvent event) {
        if (!registrationMode) {
            registrationMode = true;
            usernameField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
            confirmPasswordField.setVisible(true);
            confirmPasswordField.setManaged(true);
            loginButton.setText("Crear cuenta");
            registerButton.setVisible(false);
            registerButton.setManaged(false);
            backButton.setVisible(true);
            backButton.setManaged(true);
            return;
        }

        registerUser();
    }

    @FXML
    private void backToLogin(ActionEvent event) {
        registrationMode = false;
        usernameField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        confirmPasswordField.setVisible(false);
        confirmPasswordField.setManaged(false);
        loginButton.setText("Entrar");
        registerButton.setVisible(true);
        registerButton.setManaged(true);
        backButton.setVisible(false);
        backButton.setManaged(false);
    }

    private void registerUser() {
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            showError("Las contraseñas no coinciden.");
            return;
        }

        AuthService.RegistrationResult result = authService.register(
                usernameField.getText(),
                passwordField.getText()
        );
        switch (result) {
            case CREATED -> {
                showInformation("Registro completado. Ya puedes iniciar sesión.");
                backToLogin(null);
            }
            case INVALID_DATA -> showError("Introduce un usuario y una contraseña válidos.");
            case USERNAME_ALREADY_EXISTS -> showError("Ese usuario ya está registrado.");
            case ERROR -> showError("No se pudo completar el registro.");
        }
    }

    private void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Registro");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Registro");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
