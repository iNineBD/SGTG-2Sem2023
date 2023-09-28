create database sgtg;

use sgtg;

-- Criando a tabela dos alunos
create table aluno(
id int primary key not null,
email_institucional varchar(50) unique key not null ,
nome varchar (100),
email_pessoal varchar(50),
id_orientador int
);

-- Criando a tabela matricula
create table matricula(
id_aluno int not null,
id_turma int not null,
constraint pk_matricula primary key (id_aluno,id_turma)
);

-- Criando a tabela turma
create table turma(
id int primary key not null,
nome varchar(10)
);

-- Criando as relações do aluno com a turma através da tabela auxiliar matricula.
-- Ela nasceu da relação N:N do aluno e turma.
alter table matricula
add constraint fk_id_aluno
foreign key (id_aluno)
references aluno (id);

alter table matricula
add constraint fk_id_turma
foreign key (id_turma)
references turma (id);

-- Criando a tabela de entregas
create table entrega(
id int primary key not null,
data_entrega date,
descricao varchar (200),
id_turma int
);

-- Criando a relação da tabela entrega com a tabela turma
-- Devido a relação 1:N da turma com a entrega não é necessária a criação de tabela auxiliar.
alter table entrega
add constraint fk_id_turma_entrega
foreign key (id_turma)
references turma (id);

-- Criando a tabela feedback
create table feedback(
id int primary key not null,
comentario varchar (200),
nota double(4,2),
id_entrega int,
id_aluno int
);

-- Criando a relação do feedback com a entrega
-- Devido a relação 1:N da entrega com o feedback não é necessária a  criação de tabela auxiliar.
alter table feedback
add constraint fk_id_entrega
foreign key (id_entrega)
references entrega (id);

-- Criando a relação do feedback com o aluno
-- Devido a relação 1:N do aluno com a entrega não é necessária a criação de tabela auxiliar.
alter table feedback
add constraint fk_id_aluno_feedback
foreign key (id_aluno)
references aluno (id);

-- Criando a tabela do orientador
create table orientador(
id int primary key not null,
email_fatec varchar(50),
nome varchar (100)
);

-- Criando a relação aluno e orientador
-- Devido a relação 1:N do aluno com o orientador não é necessária a criação de tabela auxiliar
alter table aluno
add constraint fk_id_orientador
foreign key (id_orientador)
references orientador (id);

-- Criando a tabela tg
create table tg(
id int primary key not null,
problema_a_resolver varchar(200) not null,
empresa varchar(50),
disciplina varchar(50),
id_aluno int,
id_tipo int
);

-- Criando a relação do aluno com tg
-- Devido a relação 1:1 do aluno com o tg não é necessária a criação de tabela auxiliar
alter table tg
add constraint fk_id_aluno_tg
foreign key (id_aluno)
references aluno (id);

-- Criando a tabela tipo
create table tipo(
id int primary key not null,
tipo varchar(50),
regra varchar(50) 
);

-- Criando a relação do tg com tipp
-- Devido a relação N:1 do tg com tipo não é necessária a criação de tabela auxiliar
alter table tg
add constraint fk_id_tipo
foreign key (id_tipo)
references tipo(id);

