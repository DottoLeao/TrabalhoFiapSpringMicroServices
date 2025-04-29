CREATE SEQUENCE consumo_energia_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE consumo_energia (
    id NUMBER(19,0) PRIMARY KEY,
    area VARCHAR2(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    valor NUMBER(10,2) NOT NULL,
    unidade VARCHAR2(50) NOT NULL
);