package org.example.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Hero;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;


import java.io.File;
import java.util.Arrays;
import java.util.List;

/** Lee un fichero JSON y convierte su contenido en objetos Hero. */
public class JsonMapper {


    private final ObjectMapper mapper;

    // Prepara el lector de ficheros JSON.
    public JsonMapper() {

        this.mapper = new ObjectMapper();

        this.mapper.setPropertyNamingStrategy(
                PropertyNamingStrategies.SNAKE_CASE
        );
    }


    // Lee un JSON y devuelve los héroes que contiene.
    public List<Hero> readHeroes(String path) throws Exception {


        Hero[] heroes = mapper.readValue(
                new File(path),
                Hero[].class
        );


        return Arrays.asList(heroes);

    }

}