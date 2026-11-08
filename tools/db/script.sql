-- Script de desarrollo que crea la base de datos y sus tablas.
DROP DATABASE IF EXISTS deadlock;
CREATE DATABASE deadlock
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE deadlock;

-- =====================================
-- USUARIOS
-- =====================================
CREATE TABLE users (

    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_salt VARCHAR(64) NOT NULL,
    password_hash VARCHAR(64) NOT NULL,
    dle_streak INT NOT NULL DEFAULT 0,
    dle_last_date DATE NULL

);

-- =====================================
-- TABLA HEROES
-- =====================================
CREATE TABLE heroes (

                        id INT PRIMARY KEY,

                        name VARCHAR(100) NOT NULL,
                        search_name VARCHAR(100),
                        gender VARCHAR(20),

                        class_name VARCHAR(100),
                        hero_type VARCHAR(50),
                        gun_tag VARCHAR(50),

                        development_state VARCHAR(50),

                        assigned_players_only BOOLEAN NOT NULL DEFAULT FALSE,
                        in_development BOOLEAN NOT NULL DEFAULT FALSE,
                        disabled BOOLEAN NOT NULL DEFAULT FALSE,
                        player_selectable BOOLEAN NOT NULL DEFAULT TRUE,
                        needs_testing BOOLEAN NOT NULL DEFAULT FALSE,

                        hideout_rich_presence VARCHAR(255),
                        images JSON

);

-- =====================================
-- DESCRIPCIÓN DEL HÉROE (1:1)
-- =====================================
CREATE TABLE descriptions (

                              hero_id INT PRIMARY KEY,

                              role VARCHAR(100),
                              lore TEXT,
                              playstyle TEXT,

                              CONSTRAINT fk_description_hero
                                  FOREIGN KEY (hero_id)
                                      REFERENCES heroes(id)
                                      ON DELETE CASCADE

);

-- =====================================
-- TAGS DISPONIBLES
-- =====================================
CREATE TABLE tags (

                      id INT AUTO_INCREMENT PRIMARY KEY,

                      name VARCHAR(50) NOT NULL UNIQUE

);

-- =====================================
-- RELACIÓN HEROE <-> TAG (N:M)
-- =====================================
CREATE TABLE hero_tags (

                           hero_id INT NOT NULL,
                           tag_id INT NOT NULL,

                           PRIMARY KEY (hero_id, tag_id),

                           CONSTRAINT fk_hero_tags_hero
                               FOREIGN KEY (hero_id)
                                   REFERENCES heroes(id)
                                   ON DELETE CASCADE,

                           CONSTRAINT fk_hero_tags_tag
                               FOREIGN KEY (tag_id)
                                   REFERENCES tags(id)
                                   ON DELETE CASCADE

);