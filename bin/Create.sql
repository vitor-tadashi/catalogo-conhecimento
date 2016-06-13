create database CatalogoConhecimentos;
go
use CatalogoConhecimentos;
go

create table Tecnologia(
idTecnologia int identity primary key,
nomeTecnologia varchar (50) not null
);

create table Equipe(
idEquipe int identity primary key,
observacao varchar (255)
);

create table Cargo(
idCargo int identity primary key,
nomeCargo varchar(80)not null
);

create table Funcionario(
idFuncionario int identity primary key,
idCargo int not null,
nomeFuncionario varchar (150) not null,
telefone varchar(20),
nomeUser varchar(50),
email varchar(50)
constraint FK_Cargo_Funcionario
	foreign key (idCargo)
	references Cargo(idCargo)
);

create table TecnologiaFuncionario(
idTecnologiaFuncionario int identity primary key,
idTecnologia int not null,
idFuncionario int not null,
constraint FK_Tecnologia_TecnologiaFuncionario
	foreign key (idTecnologia)
	references Tecnologia(idTecnologia),
constraint FK_Funcionario_TecnologiaFuncionario
	foreign key (idFuncionario)
	references Funcionario(idFuncionario)
);

create table EquipeFuncionario(
idEquipeFuncionario int identity primary key,
idEquipe int not null,
idFuncionario int not null,
constraint FK_Equipe_EquipeFuncionario
	foreign key (idEquipe)
	references Equipe(idEquipe),
constraint FK_Funcionario_EquipeFuncionario
	foreign key (idFuncionario)
	references Funcionario(idFuncionario)
);

create table Cliente(
idCliente int identity primary key,
nomeCliente varchar (150) not null,
logradouro varchar(100) not null,
CEP varchar(8),
numero varchar(10),
CNPJ varchar(25),
email varchar(100)
);

create table Concorrente(
idConcorrente int identity primary key,
nomeConcorrente varchar(100) not null, 
descricao varchar (255)
);

create table ConcorrenteCliente(
idConcorrenteCliente int identity primary key,
idCliente int not null,
idConcorrente int not null,
valorHora decimal(5,2) not null
constraint FK_Cliente_ConcorrenteCliente
	foreign key (idCliente)
	references Cliente(idCliente),
constraint FK_Concorrente_ConcorrenteCliente
	foreign key (idConcorrente)
	references Concorrente(idConcorrente)
);

create table Negocio(
idNegocio int identity primary key,
areaAtuacao varchar(50) not null
);



create table Projeto(
idProjeto int identity primary key,
idEquipe int not null,
idCliente int not null,
nomeProjeto varchar(150)not null,
observacao varchar(255),
constraint FK_Equipe_Projeto
	foreign key (idEquipe)
	references Equipe(idEquipe),
constraint FK_Cliente_Projeto
	foreign key (idCliente)
	references Cliente(idCliente)
);

create table ProjetoTecnologia(
idProjetoTecnologia int identity primary key,
idProjeto int not null,
idTecnologia int not null,
constraint FK_Projeto_ProjetoTecnologia
	foreign key (idProjeto)
	references Projeto(idProjeto),
constraint FK_Tecnologia_ProjetoTecnologia
	foreign key (idTecnologia)
	references Tecnologia(idTecnologia)
);

create table ProjetoNegocio(
idProjetoNegocio int identity primary key,
idProjeto int not null,
idNegocio int not null
constraint FK_idProjeto
	foreign key (idProjeto)
	references Projeto(idProjeto),
constraint FK_idNegocio
	foreign key (idNegocio)
	references Negocio (idNegocio)
);
