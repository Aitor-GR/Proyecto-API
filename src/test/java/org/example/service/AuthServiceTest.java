package org.example.service;

import org.example.database.DatabaseManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Test
    void registerAndAuthenticateValidUser() {
        InMemoryDatabase database = new InMemoryDatabase();
        AuthService service = new AuthService(database);

        assertEquals(AuthService.RegistrationResult.CREATED,
                service.register("  alice  ", "secret"));
        assertTrue(service.authenticate("alice", "secret"));
        assertEquals("alice", service.getAuthenticatedUsername());
    }

    @Test
    void rejectsInvalidAndDuplicateUsers() {
        InMemoryDatabase database = new InMemoryDatabase();
        AuthService service = new AuthService(database);

        assertEquals(AuthService.RegistrationResult.INVALID_DATA,
                service.register(" ", "secret"));
        assertEquals(AuthService.RegistrationResult.CREATED,
                service.register("alice", "secret"));
        assertEquals(AuthService.RegistrationResult.USERNAME_ALREADY_EXISTS,
                service.register("alice", "another-secret"));
        assertFalse(service.authenticate("alice", "wrong"));
        assertNull(service.getAuthenticatedUsername());
    }

    private static final class InMemoryDatabase implements DatabaseManager {
        private final List<Map<String, Object>> users = new ArrayList<>();

        @Override
        public List<Map<String, Object>> select(String query, Object... params) {
            String username = (String) params[0];
            return users.stream()
                    .filter(user -> username.equals(user.get("username")))
                    .map(user -> (Map<String, Object>) new HashMap<>(user))
                    .toList();
        }

        @Override
        public int insert(String query, Object... params) {
            users.add(Map.of(
                    "username", params[0],
                    "password_salt", params[1],
                    "password_hash", params[2]
            ));
            return 1;
        }

        @Override
        public Object insertAndGetId(String query, Object... params) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int update(String query, Object... params) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int delete(String query, Object... params) {
            throw new UnsupportedOperationException();
        }
    }
}
