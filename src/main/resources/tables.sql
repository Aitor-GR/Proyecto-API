-- Estructura de las tablas que utiliza la aplicación.
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_salt VARCHAR(64) NOT NULL,
    password_hash VARCHAR(64) NOT NULL,
    dle_streak INT NOT NULL DEFAULT 0,
    dle_last_date DATE NULL
);

CREATE TABLE IF NOT EXISTS heroes (
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

CREATE TABLE IF NOT EXISTS descriptions (
    hero_id INT PRIMARY KEY,
    role VARCHAR(100),
    lore TEXT,
    playstyle TEXT,
    CONSTRAINT fk_description_hero
        FOREIGN KEY (hero_id) REFERENCES heroes(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tags (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS hero_tags (
    hero_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (hero_id, tag_id),
    CONSTRAINT fk_hero_tags_hero
        FOREIGN KEY (hero_id) REFERENCES heroes(id) ON DELETE CASCADE,
    CONSTRAINT fk_hero_tags_tag
        FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);
