package org.example.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/** Reúne las funciones auxiliares para crear ficheros CSV. */
public final class CsvUtils {

    // Realiza la operación indicada por el nombre del método.
    private CsvUtils() {
    }

    // Escapa los caracteres que pueden romper una celda CSV.
    public static String escape(String value) {
        if (value == null) {
            return "";
        }

        String escaped = value.replace("\"", "\"\"");
        if (escaped.indexOf(',') >= 0
                || escaped.indexOf('"') >= 0
                || escaped.indexOf('\n') >= 0
                || escaped.indexOf('\r') >= 0) {
            return "\"" + escaped + "\"";
        }
        return escaped;
    }

    // Convierte los datos de un héroe en una fila CSV.
    public static String toRow(List<?> values) {
        Objects.requireNonNull(values, "values must not be null");
        return values.stream()
                .map(value -> escape(value == null ? null : value.toString()))
                .reduce((left, right) -> left + "," + right)
                .orElse("");
    }

    // Escribe las filas en un fichero con codificación UTF-8.
    public static void write(Path path, List<String> rows) throws IOException {
        Objects.requireNonNull(path, "path must not be null");
        Objects.requireNonNull(rows, "rows must not be null");
        Files.writeString(
                path,
                String.join("\r\n", rows) + (rows.isEmpty() ? "" : "\r\n"),
                StandardCharsets.UTF_8
        );
    }
}
