package org.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Lee los valores de configuración que necesita la aplicación. */
public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class.getName());
    // Valores por defecto
    private static String databaseUrl = "jdbc:mysql://localhost:3307/deadlock";
    private static boolean databaseInitTables = false;
    private static String storageData = "data";
    private static String databaseUser = "root";
    private static String databasePassword = "abc123";

    static {
        try {
            logger.debug("Cargando configuración");
            Properties properties = new Properties();
            try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new IOException("Recurso 'config.properties' no encontrado");
                }
                properties.load(input);
                databaseUrl = properties.getProperty("database.url", databaseUrl);
                databaseInitTables = Boolean.parseBoolean(properties.getProperty("database.init.tables", String.valueOf(databaseInitTables)));
                storageData = properties.getProperty("storage.data", storageData);
                databaseUser = properties.getProperty("database.user", databaseUser);
                databasePassword = properties.getProperty("database.password", databasePassword);
                logger.debug("Configuración cargada correctamente");
            }
        } catch (Exception e) {
            logger.error("Error cargando configuración: {}", e.getMessage());
            logger.error("Usando valores por defecto");
        }
    }

    // Devuelve la URL de conexión con MySQL.
    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    // Indica si se deben crear las tablas al iniciar.
    public static boolean getDatabaseInitTables() {
        return databaseInitTables;
    }

    // Devuelve la carpeta configurada para los datos.
    public static String getStorageData() {
        return storageData;
    }

    // Devuelve el usuario configurado para MySQL.
    public static String getDatabaseUser() {
        return databaseUser;
    }

    // Devuelve la contraseña configurada para MySQL.
    public static String getDatabasePassword() {
        return databasePassword;
    }
}
