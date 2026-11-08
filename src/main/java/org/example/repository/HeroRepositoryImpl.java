package org.example.repository;

import org.example.database.DatabaseManager;
import org.example.database.DatabaseService;
import org.example.models.Description;
import org.example.models.Hero;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Implementa las consultas de héroes y sus datos relacionados. */
public class HeroRepositoryImpl implements HeroRepository {

    private final DatabaseManager db;

    private final DescriptionRepository descriptionRepository;
    private final HeroTagRepository heroTagRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Prepara los repositorios necesarios para guardar los datos relacionados.
    public HeroRepositoryImpl() {

        this.db = new DatabaseService();

        this.descriptionRepository =
                new DescriptionRepositoryImpl(db);

        this.heroTagRepository =
                new HeroTagRepositoryImpl(db);
    }

    // Prepara los repositorios necesarios para guardar los datos relacionados.
    public HeroRepositoryImpl(DatabaseManager db) {

        this.db = db;

        this.descriptionRepository =
                new DescriptionRepositoryImpl(db);

        this.heroTagRepository =
                new HeroTagRepositoryImpl(db);
    }

    @Override
    // Consulta y devuelve todos los registros disponibles.
    public List<Hero> findAll() {

        String sql = """
                SELECT *
                FROM heroes
                ORDER BY name
                """;

        return db.select(sql)
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    // Busca un registro usando su identificador.
    public Hero findById(int id) {

        String sql = """
                SELECT *
                FROM heroes
                WHERE id = ?
                """;

        List<Map<String, Object>> result =
                db.select(sql, id);

        if (result.isEmpty()) {
            return null;
        }

        return toModel(result.get(0));
    }

    @Override
    // Inserta un registro nuevo en la base de datos.
    public Hero save(Hero hero) {

        String sql = """
                INSERT INTO heroes
                (
                    id,
                    name,
                    search_name,
                    gender,
                    class_name,
                    hero_type,
                    gun_tag,
                    development_state,
                    assigned_players_only,
                    in_development,
                    disabled,
                    player_selectable,
                    needs_testing,
                    hideout_rich_presence,
                    images
                )
                VALUES
                (
                    ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?
                )
                """;

        int result = db.insert(

                sql,

                hero.getId(),
                hero.getName(),
                hero.getSearchName(),
                hero.getGender(),
                hero.getClassName(),
                hero.getHeroType(),
                hero.getGunTag(),
                hero.getDevelopmentState(),
                hero.isAssignedPlayersOnly(),
                hero.isInDevelopment(),
                hero.isDisabled(),
                hero.isPlayerSelectable(),
                hero.isNeedsTesting(),
                hero.getHideoutRichPresence(),
                serializeImages(hero)

        );

        if (result <= 0) {
            return null;
        }

        if (hero.getDescription() != null) {

            descriptionRepository.save(
                    hero.getId(),
                    hero.getDescription()
            );

        }

        heroTagRepository.saveTags(
                hero.getId(),
                hero.getTags()
        );

        return hero;
    }

    @Override
    // Actualiza los datos del registro indicado.
    public Hero update(int id, Hero hero) {

        String sql = """
                UPDATE heroes
                SET
                    name=?,
                    search_name=?,
                    gender=?,
                    class_name=?,
                    hero_type=?,
                    gun_tag=?,
                    development_state=?,
                    assigned_players_only=?,
                    in_development=?,
                    disabled=?,
                    player_selectable=?,
                    needs_testing=?,
                    hideout_rich_presence=?,
                    images=?
                WHERE id=?
                """;

        int result = db.update(

                sql,

                hero.getName(),
                hero.getSearchName(),
                hero.getGender(),
                hero.getClassName(),
                hero.getHeroType(),
                hero.getGunTag(),
                hero.getDevelopmentState(),
                hero.isAssignedPlayersOnly(),
                hero.isInDevelopment(),
                hero.isDisabled(),
                hero.isPlayerSelectable(),
                hero.isNeedsTesting(),
                hero.getHideoutRichPresence(),
                serializeImages(hero),
                id

        );

        if (result <= 0) {
            return null;
        }

        if (hero.getDescription() != null) {
            if (descriptionRepository.findByHeroId(id) == null) {
                descriptionRepository.save(id, hero.getDescription());
            } else {
                descriptionRepository.update(id, hero.getDescription());
            }

        }

        heroTagRepository.updateTags(
                id,
                hero.getTags()
        );

        return findById(id);
    }

    @Override
    // Elimina el registro indicado.
    public Hero delete(int id) {

        Hero hero = findById(id);

        if (hero == null) {
            return null;
        }

        String sql = """
                DELETE FROM heroes
                WHERE id = ?
                """;

        int result = db.delete(sql, id);

        if (result <= 0) {
            return null;
        }

        return hero;
    }

    @Override
    // Busca héroes cuyo nombre coincide con el texto indicado.
    public List<Hero> findByNombre(String nombre) {

        String sql = """
                SELECT *
                FROM heroes
                WHERE LOWER(name) LIKE LOWER(?)
                ORDER BY name
                """;

        return db.select(sql, "%" + nombre + "%")
                .stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    // Convierte una fila de MySQL en un objeto del modelo.
    private Hero toModel(Map<String, Object> entity) {

        Hero hero = new Hero();

        hero.setId(((Number) entity.get("id")).intValue());

        hero.setName((String) entity.get("name"));
        hero.setSearchName((String) entity.get("search_name"));
        hero.setGender((String) entity.get("gender"));

        hero.setClassName((String) entity.get("class_name"));
        hero.setHeroType((String) entity.get("hero_type"));
        hero.setGunTag((String) entity.get("gun_tag"));

        hero.setDevelopmentState((String) entity.get("development_state"));

        hero.setAssignedPlayersOnly((Boolean) entity.get("assigned_players_only"));
        hero.setInDevelopment((Boolean) entity.get("in_development"));
        hero.setDisabled((Boolean) entity.get("disabled"));
        hero.setPlayerSelectable((Boolean) entity.get("player_selectable"));
        hero.setNeedsTesting((Boolean) entity.get("needs_testing"));

        hero.setHideoutRichPresence(
                (String) entity.get("hideout_rich_presence")
        );
        hero.setImages(deserializeImages(entity.get("images")));

        Description description =
                descriptionRepository.findByHeroId(hero.getId());

        hero.setDescription(description);

        hero.setTags(
                heroTagRepository.findTagsByHeroId(hero.getId())
        );

        return hero;
    }

    // Convierte las imágenes del héroe a JSON para guardarlas.
    private String serializeImages(Hero hero) {
        if (hero.getImages() == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(hero.getImages());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("No se pudieron serializar las imágenes del héroe " + hero.getId(), e);
        }
    }

    // Lee el JSON de imágenes y lo convierte en un mapa.
    private Map<String, String> deserializeImages(Object value) {
        if (value == null) {
            return null;
        }

        try {
            return objectMapper.readValue(
                    value.toString(),
                    new TypeReference<>() {
                    }
            );
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("No se pudieron leer las imágenes del héroe", e);
        }
    }

}