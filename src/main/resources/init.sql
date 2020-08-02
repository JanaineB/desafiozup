DROP SCHEMA IF EXISTS ezup CASCADE;

CREATE SCHEMA ezup;

CREATE TABLE IF NOT EXISTS ezup.produtos(
   ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   SKU VARCHAR(100) UNIQUE   NOT NULL,
   DESCRICAO           TEXT    NOT NULL,
   PRECO      DECIMAL DEFAULT '0.00',
   PESO       DECIMAL,
   ALTURA       DECIMAL,
   LARGURA 	DECIMAL,
   PROFUNDIDADE DECIMAL,
   FABRICANTE VARCHAR(200)
);


CREATE TABLE IF NOT EXISTS ezup.pedidos(
   ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   NOME_CLIENTE VARCHAR(200) NOT NULL,
   TELEFONE VARCHAR(30),
   VALOR_PRODUTOS DECIMAL DEFAULT '0.00',
   VALOR_DESCONTO DECIMAL DEFAULT '0.00',
   VALOR_TOTAL DECIMAL DEFAULT '0.00'
);

CREATE TABLE IF NOT EXISTS ezup.pedidos_produtos(
   ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   PEDIDOS_ID INT NOT NULL,
   PRODUTOS_ID INT NOT NULL,
   CONSTRAINT FK_PEDIDOS FOREIGN KEY (PEDIDOS_ID) REFERENCES ezup.pedidos(ID),
   CONSTRAINT FK_PRODUTOS FOREIGN KEY (PRODUTOS_ID) REFERENCES ezup.produtos(ID)
);

Insert into ezup.produtos(sku, descricao, preco, peso, altura, largura, profundidade, fabricante)
values ('SOFCAMBR1', 'SOFÁ-CAMA', 3977.00, 10, 1.05, 2.50, 1.15,'Casa Verde');
Insert into ezup.produtos(sku, descricao, preco, peso, altura, largura, profundidade, fabricante)
values ('SOFCAMBR2', 'SOFÁ', 2890.00, 10, 1.05, 2.50, 1.15,'Casa Amarela');
Insert into ezup.produtos(sku, descricao, preco, peso, altura, largura, profundidade, fabricante)
values ('SOFCAMBR3', 'SOFÁ-CAMAverde', 1500.00, 10, 1.05, 1.60, 1.15,'Casa Campinas');
Insert into ezup.produtos(sku, descricao, preco, peso, altura, largura, profundidade, fabricante)
values ('SOFCAMBR4', 'SOFÁ-CAMAazul', 3000.00, 10, 1.05, 2.50, 1.15,'Casa Azul');