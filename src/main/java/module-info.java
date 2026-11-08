/** Declara los módulos y paquetes que utiliza el proyecto. */
module Proyecto.API.Deadlock {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires org.slf4j;
    requires com.fasterxml.jackson.databind;

    opens org.example to javafx.fxml;
    opens org.example.models to javafx.base, com.fasterxml.jackson.databind;
    opens org.example.controller to javafx.fxml;
    exports org.example;
    exports org.example.controller;
    exports org.example.models;
    exports org.example.service;
    exports org.example.utils;
}