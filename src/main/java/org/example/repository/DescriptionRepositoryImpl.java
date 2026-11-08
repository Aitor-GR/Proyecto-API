package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.database.DatabaseService;
import org.example.models.Description;

import java.util.List;
import java.util.Map;

/** Implementa el acceso a las descripciones de los héroes. */
public class DescriptionRepositoryImpl implements DescriptionRepository {

    private final DatabaseManager db;

    // Prepara el repositorio para acceder a MySQL.
    public DescriptionRepositoryImpl() {
        this.db = new DatabaseService();
    }

    // Prepara el repositorio para acceder a MySQL.
    public DescriptionRepositoryImpl(DatabaseManager db) {
        this.db = db;
    }

    @Override
    // Busca la descripción asociada al identificador del héroe.
    public Description findByHeroId(int heroId) {

        String sql = """
                SELECT *
                FROM descriptions
                WHERE hero_id = ?
                """;

        List<Map<String, Object>> result = db.select(sql, heroId);

        if (result.isEmpty()) {
            return null;
        }

        return toModel(result.get(0));
    }

    @Override
    // Inserta un registro nuevo en la base de datos.
    public Description save(int heroId, Description description) {

        String sql = """
                INSERT INTO descriptions(hero_id, role, lore, playstyle)
                VALUES (?, ?, ?, ?)
                """;

        int result = db.insert(
                sql,
                heroId,
                description.getRole(),
                description.getLore(),
                description.getPlaystyle()
        );

        if (result <= 0) {
            return null;
        }

        return description;
    }

    @Override
    // Actualiza los datos del registro indicado.
    public Description update(int heroId, Description description) {

        String sql = """
                UPDATE descriptions
                SET role = ?,
                    lore = ?,
                    playstyle = ?
                WHERE hero_id = ?
                """;

        int result = db.update(
                sql,
                description.getRole(),
                description.getLore(),
                description.getPlaystyle(),
                heroId
        );

        if (result <= 0) {
            return null;
        }

        return description;
    }

    @Override
    // Elimina el registro indicado.
    public Description delete(int heroId) {

        Description description = findByHeroId(heroId);

        if (description == null) {
            return null;
        }

        String sql = """
            DELETE FROM descriptions
            WHERE hero_id = ?
            """;

        int result = db.delete(sql, heroId);

        if (result <= 0) {
            return null;
        }

        return description;
    }

    // Convierte una fila de MySQL en un objeto del modelo.
    private Description toModel(Map<String, Object> entity) {

        Description description = new Description();

        description.setRole((String) entity.get("role"));
        description.setLore((String) entity.get("lore"));
        description.setPlaystyle((String) entity.get("playstyle"));

        return description;
    }
}