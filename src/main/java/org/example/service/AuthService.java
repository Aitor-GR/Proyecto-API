package org.example.service;

import org.example.database.DatabaseManager;
import org.example.database.DatabaseService;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/** Registra usuarios y comprueba sus contraseñas. */
public class AuthService {

    private static final int ITERATIONS = 120_000;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;

    private final DatabaseManager database;
    private final SecureRandom secureRandom = new SecureRandom();
    private String authenticatedUsername;

    public enum RegistrationResult {
        CREATED,
        INVALID_DATA,
        USERNAME_ALREADY_EXISTS,
        ERROR
    }

    // Prepara el servicio de autenticación con la conexión por defecto.
    public AuthService() {
        this(new DatabaseService());
    }

    // Prepara el servicio de autenticación con la conexión por defecto.
    public AuthService(DatabaseManager database) {
        this.database = database;
    }

    // Comprueba si el usuario y la contraseña son correctos.
    public boolean authenticate(String username, String password) {
        authenticatedUsername = null;
        if (username == null || username.isBlank() || password == null || password.isEmpty()) {
            return false;
        }

        List<Map<String, Object>> users = database.select(
                "SELECT password_salt, password_hash FROM users WHERE username = ?",
                username.trim()
        );
        if (users.isEmpty()) {
            return false;
        }

        Map<String, Object> user = users.get(0);
        Object salt = user.get("password_salt");
        Object hash = user.get("password_hash");
        if (!(salt instanceof String saltValue) || !(hash instanceof String hashValue)) {
            return false;
        }

        try {
            byte[] calculatedHash = hashPassword(password.toCharArray(), decode(saltValue));
            byte[] expectedHash = decode(hashValue);
            boolean authenticated = MessageDigest.isEqual(calculatedHash, expectedHash);
            authenticatedUsername = authenticated ? username.trim() : null;
            return authenticated;
        } catch (GeneralSecurityException | IllegalArgumentException exception) {
            return false;
        }

    }

    public String getAuthenticatedUsername() {
        return authenticatedUsername;
    }

    // Cambia al formulario de registro o crea una cuenta.
    public RegistrationResult register(String username, String password) {
        if (username == null || username.isBlank() || username.length() > 100
                || password == null || password.isEmpty()) {
            return RegistrationResult.INVALID_DATA;
        }

        String normalizedUsername = username.trim();
        if (!database.select("SELECT id FROM users WHERE username = ?", normalizedUsername).isEmpty()) {
            return RegistrationResult.USERNAME_ALREADY_EXISTS;
        }

        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        try {
            byte[] hash = hashPassword(password.toCharArray(), salt);
            int inserted = database.insert(
                    "INSERT INTO users(username, password_salt, password_hash) VALUES (?, ?, ?)",
                    normalizedUsername,
                    encode(salt),
                    encode(hash)
            );
            return inserted == 1 ? RegistrationResult.CREATED : RegistrationResult.ERROR;
        } catch (GeneralSecurityException exception) {
            return RegistrationResult.ERROR;
        }
    }

    // Genera el hash de la contraseña con el salt recibido.
    private byte[] hashPassword(char[] password, byte[] salt) throws GeneralSecurityException {
        PBEKeySpec specification = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(specification)
                    .getEncoded();
        } finally {
            specification.clearPassword();
        }
    }

    // Convierte una cadena Base64 en bytes.
    private byte[] decode(String value) {
        return Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8));
    }

    // Convierte bytes en una cadena Base64.
    private String encode(byte[] value) {
        return Base64.getEncoder().encodeToString(value);
    }
}
