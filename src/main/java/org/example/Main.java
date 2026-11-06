package org.example;

import org.example.mapper.JsonMapper;
import org.example.models.Hero;
import org.example.service.HeroService;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {


        JsonMapper mapper = new JsonMapper();

        List<Hero> heroes =
                mapper.readHeroes(
                        "data/response.json"
                );


        HeroService service =
                new HeroService(heroes);


        System.out.println(
                "Total héroes: "
                        + service.contarHeroes()
        );


        System.out.println("\nPrimer héroe:");
        System.out.println(
                service.primerHeroe()
        );


        System.out.println("\nBuscar Abrams:");
        service.buscarPorNombre("Abrams")
                .forEach(System.out::println);


        System.out.println("\nTags:");
        service.obtenerTodosLosTags()
                .forEach(System.out::println);


        System.out.println("\nHeroes Tank:");
        service.buscarPorTag("Tank")
                .forEach(hero ->
                        System.out.println(hero.getName())
                );


        System.out.println("\nHeroes jugables:");
        service.obtenerHeroesJugables()
                .forEach(hero ->
                        System.out.println(hero.getName())
                );


        System.out.println("\n=== HEROES AGRUPADOS POR TIPO ===");

        service.agruparPorTipo()
                .forEach((tipo, listaHeroes) -> {

                    System.out.println("\nTipo: " + tipo);

                    listaHeroes.forEach(hero ->
                            System.out.println(
                                    " - " + hero.getName()
                            )
                    );
                });
    }
}