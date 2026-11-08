package org.example.service;

import org.example.database.DatabaseManager;
import org.example.database.DatabaseService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DleService {

    private final DatabaseManager database;

    public DleService() {
        this(new DatabaseService());
    }

    public DleService(DatabaseManager database) {
        this.database = database;
    }

    public int getStreak(String username) {
        List<Map<String, Object>> users = database.select(
                "SELECT dle_streak FROM users WHERE username = ?",
                username
        );
        if (users.isEmpty() || !(users.get(0).get("dle_streak") instanceof Number streak)) {
            return 0;
        }
        return streak.intValue();
    }

    public int recordWin(String username, LocalDate today) {
        List<Map<String, Object>> users = database.select(
                "SELECT dle_streak, dle_last_date FROM users WHERE username = ?",
                username
        );
        if (users.isEmpty()) {
            throw new IllegalStateException("No se encontró el usuario autenticado.");
        }

        Map<String, Object> user = users.get(0);
        LocalDate lastDate = toLocalDate(user.get("dle_last_date"));
        int currentStreak = user.get("dle_streak") instanceof Number streak ? streak.intValue() : 0;
        if (today.equals(lastDate)) {
            return currentStreak;
        }

        int newStreak = today.minusDays(1).equals(lastDate) ? currentStreak + 1 : 1;
        int updated = database.update(
                "UPDATE users SET dle_streak = ?, dle_last_date = ? WHERE username = ?",
                newStreak,
                Date.valueOf(today),
                username
        );
        if (updated != 1) {
            throw new IllegalStateException("No se pudo guardar la racha DLE.");
        }
        return newStreak;
    }

    private LocalDate toLocalDate(Object value) {
        return value instanceof Date date ? date.toLocalDate() : null;
    }
}
