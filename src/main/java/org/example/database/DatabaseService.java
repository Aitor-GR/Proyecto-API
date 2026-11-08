package org.example.database;



import org.example.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;

/** Ejecuta consultas SQL y gestiona la conexión con MySQL. */
public class DatabaseService implements DatabaseManager {

    private final Logger logger = LoggerFactory.getLogger(DatabaseService.class.getName());

    private Connection connection = null;

    // Inicia la conexión con MySQL y, si toca, prepara las tablas.
    public DatabaseService() {
        initConexion();
        if (Config.getDatabaseInitTables()) {
            initTablas();
        }
    }

    // Abre la conexión con la base de datos configurada.
    private void initConexion() {
        logger.debug("Iniciando conexión con la base de datos");
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(Config.getDatabaseUrl(), Config.getDatabaseUser(), Config.getDatabasePassword());
            }
            logger.debug("Conexión con la base de datos inicializada");
        } catch (SQLException e) {
            logger.error("Error al iniciar la conexión: " + e.getMessage());
        }
    }

    // Crea las tablas si está activada su inicialización.
    private void initTablas() {
        logger.debug("Creando tablas");
        try (InputStream input = DatabaseService.class.getClassLoader().getResourceAsStream("tables.sql")) {
            if (input == null) {
                throw new IOException("Recurso 'tables.sql' no encontrado");
            }

            String tables = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            try (Statement statement = connection.createStatement()) {
                for (String query : tables.split(";")) {
                    if (!query.isBlank()) {
                        statement.executeUpdate(query);
                    }
                }
            }

            logger.debug("Tablas creadas correctamente");
        } catch (Exception e) {
            logger.error("Error al crear las tablas: " + e.getMessage());
        }
    }

    @Override
    // Ejecuta un SELECT y devuelve sus filas.
    public List<Map<String, Object>> select(String sql, Object... params) {
        logger.debug("Ejecutando consulta: " + sql);
        List<Map<String, Object>> result = new ArrayList<>();
        initConexion();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParameters(statement, params);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                ResultSetMetaData meta = resultSet.getMetaData();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    row.put(meta.getColumnName(i), resultSet.getObject(i));
                }
                result.add(row);
            }
        } catch (SQLException e) {
            logger.error("Error en la consulta: " + e.getMessage());
        }

        logger.debug("Consulta finalizada con " + result.size() + " resultados");
        return result;
    }

    @Override
    // Realiza la operación indicada por el nombre del método.
    public int insert(String sql, Object... params) {
        logger.debug("Ejecutando inserción: " + sql);
        int result = 0;
        initConexion();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParameters(statement, params);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error en la inserción: " + e.getMessage());
        }

        logger.debug("Inserción finalizada con " + result + " filas afectadas");
        return result;
    }

    @Override
    // Inserta un registro y devuelve el identificador generado.
    public Object insertAndGetId(String sql, Object... params) {
        logger.debug("Ejecutando inserción y obteniendo ID: " + sql);
        Object result = 0;
        initConexion();

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(statement, params);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getObject(1);
            }
        } catch (SQLException e) {
            logger.error("Error al insertar y obtener ID: " + e.getMessage());
        }

        logger.debug("Inserción finalizada con ID: " + result);
        return result;
    }

    @Override
    // Actualiza los datos del registro indicado.
    public int update(String query, Object... params) {
        logger.debug("Ejecutando actualización: " + query);
        int result = 0;
        initConexion();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setParameters(statement, params);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error al actualizar: " + e.getMessage());
        }
        logger.debug("Actualización finalizada con " + result + " filas afectadas");
        return result;
    }

    @Override
    // Elimina el registro indicado.
    public int delete(String query, Object... params) {
        logger.debug("Ejecutando borrado: " + query);
        int result = 0;
        initConexion();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setParameters(statement, params);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error al borrar: " + e.getMessage());
        }
        logger.debug("Borrado finalizado con " + result + " filas afectadas");
        return result;
    }

    // Asigna los valores a la consulta preparada.
    private void setParameters(PreparedStatement statement, Object[] params) throws SQLException {
        logger.debug("Pasando parámetros a la consulta: " + Arrays.toString(params));
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }
}