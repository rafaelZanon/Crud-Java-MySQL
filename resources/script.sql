create database cliente;

create table cliente(

id int not null auto_increment primary key,
nome varchar (40),
idade int (2),
cep varchar,
numeroCasa int,
complemento varchar
)
