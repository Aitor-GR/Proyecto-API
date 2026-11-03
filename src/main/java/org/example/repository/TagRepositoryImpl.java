package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.database.DatabaseService;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TagRepositoryImpl implements TagRepository {

    private final DatabaseManager db;

    public TagRepositoryImpl() {
        this.db = new DatabaseService();
    }

    public TagRepositoryImpl(DatabaseManager db) {
        this.db = db;
    }

    @Override
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
    public Integer getOrCreateTag(String name) {

        Integer id = findIdByName(name);

        if (id != null) {
            return id;
        }

        return save(name);
    }
}