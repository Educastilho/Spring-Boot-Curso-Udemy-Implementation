CREATE TABLE tb_cidade (
    id_cidade bigint not null PRIMARY KEY,
    nome VARCHAR(100) not null,
    qtd_habitantes bigint
);

insert INTO tb_cidade (id_cidade, nome, qtd_habitantes) 
    VALUES 
    (1, 'São Paulo', 12396372), 
    (2, 'Rio de Janeiro', 18600000),
    (3, 'Recife', 9345372);