CREATE DATABASE touristdb;

-- Connect to touristdb, then run:
CREATE TABLE IF NOT EXISTS tourists (
 id BIGSERIAL PRIMARY KEY,
 name VARCHAR(100) NOT NULL,
 location VARCHAR(100) NOT NULL,
 country VARCHAR(100) NOT NULL
);
