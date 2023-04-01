CREATE EXTENSION IF NOT EXISTS CITEXT;

CREATE TABLE IF NOT EXISTS categoria (
  id SERIAL PRIMARY KEY,
  nome CITEXT NOT NULL UNIQUE CHECK(LENGTH(nome) <= 50)
);

CREATE TABLE IF NOT EXISTS servico (
  id SERIAL PRIMARY KEY,
  nome CITEXT NOT NULL UNIQUE CHECK(LENGTH(nome) <= 75),
  categoria_id INTEGER,
  CONSTRAINT fk_categoria
    FOREIGN KEY(categoria_id)
      REFERENCES categoria(id)
);

CREATE TABLE IF NOT EXISTS contratante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(11) NOT NULL CHECK(telefone ~ '[0-9]{11}'),
    endereco VARCHAR(200),
    email VARCHAR(100),
    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE,
    observacoes TEXT
);
