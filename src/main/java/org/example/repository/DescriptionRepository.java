package org.example.repository;

import org.example.models.Description;

/** Define las operaciones para consultar y guardar descripciones. */
public interface DescriptionRepository {

    Description findByHeroId(int heroId);

    Description save(int heroId, Description description);

    Description update(int heroId, Description description);

    Description delete(int heroId);
}