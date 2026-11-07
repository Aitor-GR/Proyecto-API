package org.example.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public final class CsvUtils {

    private CsvUtils() {
    }

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

    public static String toRow(List<?> values) {
        Objects.requireNonNull(values, "values must not be null");
        return values.stream()
                .map(value -> escape(value == null ? null : value.toString()))
                .reduce((left, right) -> left + "," + right)
                .orElse("");
    }

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
