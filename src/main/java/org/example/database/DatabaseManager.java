package org.example.database;

import java.util.List;
import java.util.Map;

/** Define las operaciones de base de datos que usan los repositorios. */
public interface DatabaseManager {
    List<Map<String, Object>> select(String query, Object... params);
    int insert(String query, Object... params);
    Object insertAndGetId(String query, Object... params);
    int update(String query, Object... params);
    int delete(String query, Object... params);
}
