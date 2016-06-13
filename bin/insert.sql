INSERT INTO Tecnologia VALUES 
('PHP'),
('C#'),
('C++'),
('C'),
('JavaScript'),
('Phyton'),
('Cobol'),
('Pascal'),
('VisualG'),
('Visual'),
('Java')
SELECT * FROM Tecnologia

INSERT INTO Equipe VALUES
('Certificado PMI'),
('Certificado oracle'),
('Certificado scrum'),
('Certificado CISCO'),
('Certificado Agile'),
('Certificado Microsoft')

INSERT INTO Cargo VALUES
('Presidente'),
('Diretor'),
('Gerente'),
('Desenvolvedor'),
('Estagiário')

SELECT * FROM Cargo

INSERT INTO Funcionario VALUES
(1, 'João', '3252-8541', 'JoaoUser', 'joao.joao@gmail.com'),
(2, 'Maria', '8541-9658', 'MariaUser', 'Maria.maria@gmail.com'),
(2, 'Paulo', '8574-5371', 'PauloUser', 'paulo.paulo@gmail.com'),
(3, 'Marcos', '7586-6324', 'MarcosUser', 'marcos.marcos@gmail.com'),
(2, 'Guilherme', '5236-8547', 'GuilhermeUser', 'gui.gui@gmail.com'),
(2, 'Isabela', '8541-9634', 'IsaUser', 'isa.bela@gmail.com'),
(4, 'Leo', '6368-6337', 'leoUser', 'leo.leo@gmail.com'),
(4, 'Lucas', '8563-6327', 'JoaoUser', 'lucas.lucas@gmail.com'),
(5, 'Leandro', '5625-8732', 'LeoandroUser', 'le.andro@gmail.com')

INSERT INTO TecnologiaFuncionario VALUES
(1,9),
(2,8),
(5,8),
(4,6),
(5,1),
(7,8),
(10,1),
(9,5),
(7,3),
(8,8)



SELECT 
	f.idFuncionario, 
	f.nomeFuncionario, 
	t.nomeTecnologia 
FROM 
	Funcionario AS f
	INNER JOIN TecnologiaFuncionario AS tf
		ON tf.idFuncionario = f.idFuncionario
	INNER JOIN Tecnologia AS t
		ON t.idTecnologia = tf.idTecnologia
	ORDER BY f.idFuncionario
-- QUERY FUNCIONARIOS/TEC
SELECT 
	t.nomeTecnologia, tf.idFuncionario
FROM 
	TecnologiaFuncionario AS tf
    INNER JOIN Tecnologia AS t 
    on tf.idTecnologia = t.idTecnologia
 WHERE tf.idFuncionario = 8


INSERT INTO EquipeFuncionario VALUES
(6,5),
(1,3),
(2,4),
(6,1),
(3,8),
(6,2)

INSERT INTO Cliente VALUES 
('Itau', 'av paulista','04686001','1222', '22708165000155','itau.itau@gmail.com'),
('Bradesco', 'av brasil','02021368','202', '72665551000185','bradesco.bra@gmail.com'),
('Banco do Brasil', 'av nossa senhora do sabara', '02587441', '960', '4125379400012', 'bb.brasil@gmail.com')

INSERT INTO Concorrente VALUES
('BRQ', 'RUIM'),
('BSI', 'pior'),
('GFT', 'sdada')


INSERT INTO ConcorrenteCliente VALUES
(1,2,40),
(1,3, 50),
(2,1, 50)

INSERT INTO Negocio VALUES 
('IT'),
('Recursos Humanos'),
('Sustentabilidade'),
('Financeiro')






SELECT 
	f.nomeFuncionario, e.idEquipe
FROM
	Equipe AS e
	INNER JOIN EquipeFuncionario AS ef
		ON e.idEquipe = ef.idEquipe
	INNER JOIN Funcionario AS f
		ON f.idFuncionario = ef.idEquipeFuncionario
	GROUP BY f.nomeFuncionario, e.idEquipe


	
		







