-- CRIAR TABELA CLIENTE
CREATE TABLE CLIENTE(
    ID INTEGER NOT NULL PRIMARY KEY,
    NOME VARCHAR(100) NOT NULL
);
CREATE SEQUENCE CLIENTE_SEQ;

-- CRIAR TABELA ANIMAL
CREATE TABLE ANIMAL(
    ID INTEGER NOT NULL PRIMARY KEY, 
    NOME VARCHAR(100) NOT NULL
);
CREATE SEQUENCE ANIMAL_SEQ;

-- CRIAR RELACIONAMENTO CLIENTE <-> ANIMAL (MANY TO MANY)
CREATE TABLE CLIENTE_ANIMAL(
    ID_CLIENTE INTEGER NOT NULL REFERENCES CLIENTE(ID), 
    ID_ANIMAL INTEGER NOT NULL REFERENCES ANIMAL(ID)
);

-- SALVAR
COMMIT;

-- REMOVER TABELA
DROP TABLE ANIMAL;
DROP TABLE ANIMAL_SEQ;
DROP TABLE CLIENTE;
DROP TABLE CLIENTE_SEQ;
DROP TABLE CLIENTE_ANIMAL;

