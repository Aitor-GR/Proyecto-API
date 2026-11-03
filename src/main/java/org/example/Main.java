package org.example;

import org.example.models.Hero;
import org.example.repository.HeroRepository;
import org.example.repository.HeroRepositoryImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        HeroRepository heroRepository = new HeroRepositoryImpl();

        List<Hero> heroes = heroRepository.findAll();

        for (Hero hero : heroes) {

            System.out.println("------------------------");
            System.out.println("ID: " + hero.getId());
            System.out.println("Nombre: " + hero.getName());
            System.out.println("Tipo: " + hero.getHeroType());
            System.out.println("Clase: " + hero.getClassName());

            if (hero.getDescription() != null) {
                System.out.println("Rol: "
                        + hero.getDescription().getRole());

                System.out.println("Playstyle: "
                        + hero.getDescription().getPlaystyle());
            }

            System.out.println("Tags: "
                    + hero.getTags());
        }
    }
}