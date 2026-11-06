package org.example.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Hero;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;


import java.io.File;
import java.util.Arrays;
import java.util.List;

public class JsonMapper {


    private final ObjectMapper mapper;

    public JsonMapper() {

        this.mapper = new ObjectMapper();

        this.mapper.setPropertyNamingStrategy(
                PropertyNamingStrategies.SNAKE_CASE
        );
    }


    public List<Hero> readHeroes(String path) throws Exception {


        Hero[] heroes = mapper.readValue(
                new File(path),
                Hero[].class
        );


        return Arrays.asList(heroes);

    }

}