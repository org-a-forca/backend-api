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

CREATE TABLE IF NOT EXISTS trabalhador (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(11) NOT NULL CHECK(telefone ~ '[0-9]{11}'),
    endereco VARCHAR(200),
    email VARCHAR(100),
    nivel INTEGER,
    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE,
    data_ultimo_contrato DATE,
    referencias TEXT,
    restricoes TEXT,
    observacoes TEXT
);

CREATE TABLE IF NOT EXISTS trabalhadores_servicos (
    trabalhador_id INTEGER REFERENCES trabalhador(id),
    servico_id INTEGER REFERENCES servico(id),
    PRIMARY KEY (trabalhador_id, servico_id)
);

CREATE TABLE IF NOT EXISTS contrato (
    id SERIAL PRIMARY KEY,
    contratante_id INTEGER,
    trabalhador_id INTEGER,
    data DATE NOT NULL DEFAULT CURRENT_DATE,
    status VARCHAR(11) CHECK(status IN ('Aberto', 'Desistiu', 'Feito', 'Para depois', 'Pegou fora')),
    nota_trabalhador INTEGER CHECK(nota_trabalhador IN (NULL, 1, 2, 3, 4, 5)),
    nota_contratante INTEGER CHECK(nota_contratante IN (NULL, 1, 2, 3, 4, 5)),
    observacoes TEXT,
    CONSTRAINT fk_contratante
      FOREIGN KEY(contratante_id)
        REFERENCES contratante(id),
    CONSTRAINT fk_trabalhador
      FOREIGN KEY(trabalhador_id)
        REFERENCES trabalhador(id)
);

CREATE TABLE IF NOT EXISTS contratos_servicos (
    contrato_id INTEGER REFERENCES contrato(id),
    servico_id INTEGER REFERENCES servico(id),
    PRIMARY KEY (contrato_id, servico_id)
);

CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha TEXT
);
