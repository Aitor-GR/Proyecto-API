package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.database.DatabaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Gestiona la relación entre héroes y etiquetas. */
public class HeroTagRepositoryImpl implements HeroTagRepository {

    private final DatabaseManager db;
    private final TagRepository tagRepository;

    // Prepara el acceso a etiquetas y a la base de datos.
    public HeroTagRepositoryImpl() {
        this.db = new DatabaseService();
        this.tagRepository = new TagRepositoryImpl(db);
    }

    // Prepara el acceso a etiquetas y a la base de datos.
    public HeroTagRepositoryImpl(DatabaseManager db) {
        this.db = db;
        this.tagRepository = new TagRepositoryImpl(db);
    }

    @Override
    // Devuelve las etiquetas asociadas a un héroe.
    public List<String> findTagsByHeroId(int heroId) {

        String sql = """
                SELECT t.name
                FROM tags t
                INNER JOIN hero_tags ht
                    ON t.id = ht.tag_id
                WHERE ht.hero_id = ?
                ORDER BY t.name
                """;

        List<Map<String, Object>> result = db.select(sql, heroId);

        List<String> tags = new ArrayList<>();

        for (Map<String, Object> row : result) {
            tags.add((String) row.get("name"));
        }

        return tags;
    }

    @Override
    // Guarda las etiquetas del héroe y sus relaciones.
    public List<String> saveTags(int heroId, List<String> tags) {

        if (tags == null || tags.isEmpty()) {
            return tags;
        }

        String sql = """
                INSERT INTO hero_tags(hero_id, tag_id)
                VALUES (?, ?)
                """;

        for (String tag : tags) {

            Integer tagId = tagRepository.getOrCreateTag(tag);

            db.insert(
                    sql,
                    heroId,
                    tagId
            );
        }

        return tags;
    }

    @Override
    // Reemplaza las etiquetas anteriores por la nueva lista.
    public List<String> updateTags(int heroId, List<String> tags) {

        String deleteSql = """
                DELETE FROM hero_tags
                WHERE hero_id = ?
                """;

        db.delete(deleteSql, heroId);

        return saveTags(heroId, tags);
    }

}