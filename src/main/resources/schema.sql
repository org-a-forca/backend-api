-- CREATE EXTENSION IF NOT EXISTS citext;

CREATE TABLE IF NOT EXISTS categoria (
  id SERIAL PRIMARY KEY,
  nome CITEXT NOT NULL UNIQUE CHECK(LENGTH(nome) <= 50)
);