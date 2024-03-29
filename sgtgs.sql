drop database sgtg;

create database if not exists sgtg CHARACTER  SET utf8mb4 COLLATE utf8mb4_unicode_ci;

use sgtg;

-- Criando a tabela dos alunos
create table aluno(
id int auto_increment,
email_institucional varchar(50),
nome varchar (100) not null,
email_pessoal varchar(50) not null,
id_orientador int not null,
id_tipo int not null,
visibility bool not null default true,
constraint pk_idAluno primary key (id)
);

-- Criando a tabela tipo
create table tipo(
id int auto_increment,
tipo varchar(100) not null,
regra varchar(100),
constraint pk_idTipo primary key (id),
constraint uk_tipo unique (tipo)
);

-- Criando a relação de aluno com tipo
-- Devido a relação ser 1:N não a necessidade de criação de tabela auxiliar
alter table aluno
add constraint fk_id_tipo_aluno
foreign key (id_tipo)
references tipo(id);

-- Criando a tabela matricula
create table matricula(
id_aluno int not null,
id_turma int not null,
constraint pk_idAluno_e_idTurma primary key (id_aluno,id_turma)
);

-- Criando tabela Semestre
create table semestre(
semestralizacao int not null,
ano year not null,
constraint pk_semestre primary key (semestralizacao, ano)
);

-- Criando a tabela turma
create table turma(
id int auto_increment,
nome varchar(30) not null,
semestralizacao int not null,
ano year not null,
constraint pk_idTurma primary key (id),
constraint fk_turma_semestre foreign key (semestralizacao, ano) references semestre (semestralizacao, ano),
constraint uk_nome_semest_ano unique (nome, semestralizacao, ano)
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
id int auto_increment,
titulo_entrega varchar(30),
data_entrega date not null,
descricao varchar (200) not null,
visibility bool not null default true,
constraint pk_idEntrega primary key (id)
);

create table entrega_turma(
id_turma int not null,
id_entrega int not null,
constraint pk_idTurma_e_id_entrega primary key(id_turma,id_entrega)
);

alter table entrega_turma
add constraint fk_id_turma_table_entrega_turma
foreign key(id_turma)
references turma(id);

alter table entrega_turma
add constraint fk_id_tipo_table_entrega_turma
foreign key (id_entrega)
references entrega(id);

-- Criando a tabela da relação de tipo e entrega
-- Devida a relação de N:N de tipo com entrega é necessário a criação de tabela auxiliar

create table entrega_tipo(
id_entrega int not null,
id_tipo int not null,
constraint pk_id_entrega_e_id_tipo primary key (id_entrega,id_tipo)
);

alter table entrega_tipo
add constraint fk_id_tipo_entrega
foreign key (id_tipo)
references tipo (id);

alter table entrega_tipo
add constraint fk_id_entrega_tipo
foreign key (id_entrega)
references entrega (id);

-- Criando a tabela feedback
create table feedback(
id int auto_increment,
comentario varchar (200) not null,
visibility bool default true,
nota double(4,2) not null,
id_entrega int not null,
id_aluno int not null,
constraint pk_feedback primary key (id),
constraint uk_feedback unique (id_entrega, id_aluno)
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
id int auto_increment,
email_fatec varchar(50) not null,
nome varchar (100) not null,
constraint pk_idOrientador primary key (id)
);

-- Criando a relação aluno e orientador
-- Devido a relação 1:N do aluno com o orientador não é necessária a criação de tabela auxiliar
alter table aluno
add constraint fk_id_orientador
foreign key (id_orientador)
references orientador (id);

-- Criando a tabela tg
create table tg(
id int auto_increment,
problema_a_resolver varchar(200),
empresa varchar(50),
disciplina varchar(50),
id_aluno int not null,
constraint pk_idTg primary key(id)
);

-- Criando a relação do aluno com tg
-- Devido a relação 1:1 do aluno com o tg não é necessária a criação de tabela auxiliar
alter table tg
add constraint fk_id_aluno_tg
foreign key (id_aluno)
references aluno (id);


DELIMITER $$
CREATE PROCEDURE `pr_insert_entrega`(
ptitulo varchar(30),
pdata_entrega date,
pdescricao varchar(200)
)
BEGIN
	INSERT INTO entrega (titulo_entrega, data_entrega, descricao) VALUES (ptitulo, pdata_entrega, pdescricao);
    SELECT id FROM entrega WHERE id =  LAST_INSERT_ID();
END$$
DELIMITER ;



