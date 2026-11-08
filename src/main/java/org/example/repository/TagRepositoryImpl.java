package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.database.DatabaseService;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Implementa las consultas para buscar y guardar etiquetas. */
public class TagRepositoryImpl implements TagRepository {

    private final DatabaseManager db;

    // Prepara el acceso a la base de datos.
    public TagRepositoryImpl() {
        this.db = new DatabaseService();
    }

    // Prepara el acceso a la base de datos.
    public TagRepositoryImpl(DatabaseManager db) {
        this.db = db;
    }

    @Override
    // Consulta y devuelve todos los registros disponibles.
    public List<String> findAll() {

        String sql = """
                SELECT name
                FROM tags
                ORDER BY name
                """;

        return db.select(sql)
                .stream()
                .map(entity -> (String) entity.get("name"))
                .collect(Collectors.toList());
    }

    @Override
    // Busca un registro usando su identificador.
    public String findById(int id) {

        String sql = """
                SELECT name
                FROM tags
                WHERE id = ?
                """;

        List<Map<String, Object>> result = db.select(sql, id);

        if (result.isEmpty()) {
            return null;
        }

        return (String) result.get(0).get("name");
    }

    @Override
    // Busca el identificador de una etiqueta por su nombre.
    public Integer findIdByName(String name) {

        String sql = """
                SELECT id
                FROM tags
                WHERE name = ?
                """;

        List<Map<String, Object>> result = db.select(sql, name);

        if (result.isEmpty()) {
            return null;
        }

        return ((Number) result.get(0).get("id")).intValue();
    }

    @Override
    // Inserta un registro nuevo en la base de datos.
    public Integer save(String name) {

        String sql = """
                INSERT INTO tags(name)
                VALUES (?)
                """;

        Object generatedId = db.insertAndGetId(sql, name);

        if (generatedId instanceof BigInteger) {
            return ((BigInteger) generatedId).intValue();
        }

        return ((Number) generatedId).intValue();
    }

    @Override
    // Busca la etiqueta y la crea si todavía no existe.
    public Integer getOrCreateTag(String name) {

        Integer id = findIdByName(name);

        if (id != null) {
            return id;
        }

        return save(name);
    }
}