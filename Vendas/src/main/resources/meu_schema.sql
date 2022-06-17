create database vendas;

use vendas;

CREATE TABLE CLIENTE (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100),
    CPF VARCHAR(11)
);

CREATE TABLE PRODUTO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO VARCHAR(100),
    PRECO_UNITARIO NUMERIC(20, 2)
);

CREATE TABLE PEDIDO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENTE_ID INTEGER REFERENCES CLIENTE(ID),
    DATA_PEDIDO TIMESTAMP,
    TOTAL NUMERIC(20, 2),
    STATUS VARCHAR(20)
);

CREATE TABLE ITEM_PEDIDO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PEDIDO_ID INTEGER REFERENCES PEDIDO(ID),
    PRODUTO_ID INTEGER REFERENCES PRODUTO(ID),
    QUANTIDADE INTEGER
);

create table usuario (
        ID integer not null,
        ADMIN boolean default false,
        LOGIN varchar(255),
        SENHA varchar(255),
        primary key (id)
    );

alter table ITEM_PEDIDO add constraint foreign key (PEDIDO_ID) references pedido (ID);

alter table ITEM_PEDIDO add constraint foreign key (PRODUTO_ID) references produto (ID);

alter table PEDIDO add constraint foreign key (CLIENTE_ID) references cliente (ID);