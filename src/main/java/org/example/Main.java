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


        System.out.println("==============================");
        System.out.println("   DEADLOCK HERO ANALYZER");
        System.out.println("==============================");


        System.out.println("\nTotal héroes:");
        System.out.println(service.contarHeroes());


        System.out.println("\n=== PRIMER HÉROE ===");
        System.out.println(
                service.primerHeroe()
        );


        System.out.println("\n=== BUSCAR POR NOMBRE ===");
        service.buscarPorNombre("Abrams")
                .forEach(System.out::println);


        System.out.println("\n=== NOMBRES DE HÉROES ===");
        service.obtenerNombres()
                .forEach(System.out::println);


        System.out.println("\n=== HÉROES ORDENADOS POR NOMBRE ===");
        service.ordenarPorNombre()
                .forEach(hero ->
                        System.out.println(hero.getName())
                );


        System.out.println("\n=== HÉROES CON TAG TANK ===");
        service.buscarPorTag("Tank")
                .forEach(hero ->
                        System.out.println(hero.getName())
                );


        System.out.println("\n=== HÉROES JUGABLES ===");
        service.obtenerHeroesJugables()
                .forEach(hero ->
                        System.out.println(hero.getName())
                );


        System.out.println("\n=== TODOS LOS TAGS ===");
        service.obtenerTodosLosTags()
                .forEach(System.out::println);


        System.out.println("\n=== HÉROES AGRUPADOS POR TIPO ===");

        service.agruparPorTipo()
                .forEach((tipo, listaHeroes) -> {

                    System.out.println("\nTipo: " + tipo);

                    listaHeroes.forEach(hero ->
                            System.out.println(
                                    " - " + hero.getName()
                            )
                    );
                });


        System.out.println("\n=== CANTIDAD DE HÉROES POR TIPO ===");

        service.contarHeroesPorTipo()
                .forEach((tipo, cantidad) ->
                        System.out.println(
                                tipo + ": " + cantidad
                        )
                );
    }
}