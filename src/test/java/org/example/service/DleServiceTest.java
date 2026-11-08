package org.example.service;

import org.example.database.DatabaseManager;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/** Pruebas del cálculo de la racha diaria del juego DLE. */
class DleServiceTest {

    // Comprueba que una victoria seguida suma la racha y no se repite el mismo día.
    @Test
    void incrementsConsecutiveWinsAndDoesNotDuplicateSameDay() {
        FakeDatabase database = new FakeDatabase(2, Date.valueOf("2026-01-10"));
        DleService service = new DleService(database);

        assertEquals(3, service.recordWin("alice", LocalDate.of(2026, 1, 11)));
        assertEquals(3, service.recordWin("alice", LocalDate.of(2026, 1, 11)));
        assertEquals(1, database.updates);
    }

    // Comprueba que la racha se reinicia tras un día perdido y que se detecta un usuario inexistente.
    @Test
    void resetsStreakAfterGapAndRejectsUnknownUser() {
        FakeDatabase database = new FakeDatabase(4, Date.valueOf("2026-01-01"));
        DleService service = new DleService(database);

        assertEquals(1, service.recordWin("alice", LocalDate.of(2026, 1, 4)));
        database.userPresent = false;
        assertThrows(IllegalStateException.class,
                () -> service.recordWin("alice", LocalDate.of(2026, 1, 5)));
    }

    // Base de datos falsa que guarda el usuario y cuenta las actualizaciones.
    private static final class FakeDatabase implements DatabaseManager {
        private int streak;
        private Date lastDate;
        private int updates;
        private boolean userPresent = true;

        // Crea la simulación con una racha y una fecha iniciales.
        private FakeDatabase(int streak, Date lastDate) {
            this.streak = streak;
            this.lastDate = lastDate;
        }

        // Devuelve el estado del usuario simulado si está registrado.
        @Override
        public List<Map<String, Object>> select(String query, Object... params) {
            if (!userPresent) {
                return List.of();
            }
            Map<String, Object> user = new HashMap<>();
            user.put("dle_streak", streak);
            user.put("dle_last_date", lastDate);
            return List.of(user);
        }

        // Guarda la racha y la fecha que recibe el servicio.
        @Override
        public int update(String query, Object... params) {
            streak = (Integer) params[0];
            lastDate = (Date) params[1];
            updates++;
            return 1;
        }

        // Las operaciones siguientes no se necesitan para probar la racha.
        @Override
        public int insert(String query, Object... params) { return 0; }

        // No se usa en estas pruebas.
        @Override
        public Object insertAndGetId(String query, Object... params) { return null; }

        // No se usa en estas pruebas.
        @Override
        public int delete(String query, Object... params) { return 0; }
    }
}
