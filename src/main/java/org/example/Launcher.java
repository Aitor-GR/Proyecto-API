package org.example;

/** Inicia la aplicación desde el JAR ejecutable sin lanzar JavaFX directamente. */
public final class Launcher {

    private Launcher() {
        // Esta clase solo sirve como punto de entrada del JAR.
    }

    /** Delega el arranque en la aplicacion JavaFX. */
    public static void main(String[] args) {
        Main.main(args);
    }
}
