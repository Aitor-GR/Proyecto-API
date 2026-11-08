package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.service.AuthService;

/** Gestiona el formulario de acceso y el registro de usuarios. */
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

    // Actualiza auth service con el valor recibido.
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    // Actualiza on login success con el valor recibido.
    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }

    @FXML
    // Comprueba las credenciales o continúa con el registro.
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
    // Cambia al formulario de registro o crea una cuenta.
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
    // Vuelve al formulario de inicio de sesión.
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

    // Valida los campos y solicita crear una cuenta.
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

    // Muestra un aviso informativo al usuario.
    private void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Registro");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    // Muestra un aviso cuando ocurre un error.
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Registro");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
