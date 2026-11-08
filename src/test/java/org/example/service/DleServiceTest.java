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

class DleServiceTest {

    @Test
    void incrementsConsecutiveWinsAndDoesNotDuplicateSameDay() {
        FakeDatabase database = new FakeDatabase(2, Date.valueOf("2026-01-10"));
        DleService service = new DleService(database);

        assertEquals(3, service.recordWin("alice", LocalDate.of(2026, 1, 11)));
        assertEquals(3, service.recordWin("alice", LocalDate.of(2026, 1, 11)));
        assertEquals(1, database.updates);
    }

    @Test
    void resetsStreakAfterGapAndRejectsUnknownUser() {
        FakeDatabase database = new FakeDatabase(4, Date.valueOf("2026-01-01"));
        DleService service = new DleService(database);

        assertEquals(1, service.recordWin("alice", LocalDate.of(2026, 1, 4)));
        database.userPresent = false;
        assertThrows(IllegalStateException.class,
                () -> service.recordWin("alice", LocalDate.of(2026, 1, 5)));
    }

    private static final class FakeDatabase implements DatabaseManager {
        private int streak;
        private Date lastDate;
        private int updates;
        private boolean userPresent = true;

        private FakeDatabase(int streak, Date lastDate) {
            this.streak = streak;
            this.lastDate = lastDate;
        }

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

        @Override
        public int update(String query, Object... params) {
            streak = (Integer) params[0];
            lastDate = (Date) params[1];
            updates++;
            return 1;
        }

        @Override
        public int insert(String query, Object... params) { return 0; }

        @Override
        public Object insertAndGetId(String query, Object... params) { return null; }

        @Override
        public int delete(String query, Object... params) { return 0; }
    }
}
