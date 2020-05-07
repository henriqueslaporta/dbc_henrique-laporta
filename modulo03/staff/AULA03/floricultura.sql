
create table cliente( id number not null primary key, nome varchar2(100) not null, telefone varchar2(20) );
create sequence cliente_seq;
create table produto( id number not null primary key, descricao varchar2(100) not null, unidade_medida varchar2(3), valor number(10,2) not null);
create sequence produto_seq;
create table venda (id number not null primary key, id_cliente number not null references cliente(id), id_produto number not null references produto(id), desconto number(10,2));
create sequence venda_seq;

