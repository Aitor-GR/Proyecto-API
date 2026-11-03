package org.example;

import org.example.repository.HeroRepositoryImpl;

public class Main {
    static void main() {
        HeroRepositoryImpl repository = new HeroRepositoryImpl();


        // Mostrar todos los países
        System.out.println("---- TODOS LOS PAISES ----");
        repository.findAll()
                .forEach(System.out::println);
    }
}