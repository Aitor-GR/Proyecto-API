package org.example.repository;

import java.util.List;

public interface HeroTagRepository {

    List<String> findTagsByHeroId(int heroId);

    List<String> saveTags(int heroId, List<String> tags);

    List<String> updateTags(int heroId, List<String> tags);

}