CREATE SEQUENCE alerta_consumo_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE alerta_consumo (
    id NUMBER(19,0) PRIMARY KEY,
    consumo_energia_id NUMBER(19,0) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    mensagem VARCHAR