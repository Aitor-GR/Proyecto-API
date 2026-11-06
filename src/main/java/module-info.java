module Proyecto.API.Deadlock {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;

    opens org.example to javafx.fxml;
    opens org.example.models to javafx.base;
    opens org.example.controller to javafx.fxml;
    exports org.example;
    exports org.example.controller;
}