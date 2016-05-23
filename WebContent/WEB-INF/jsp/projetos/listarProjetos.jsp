<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catálogo de Conhecimentos</title>
</head>
<body>
	<Section width="100%" height="auto">
		<h3>Lista de projetos</h3>
	</Section>

	<table border="1">
	
		<tr>
			
			<th>Identificação</th>
			<th>Nome do Projeto</th>
			<th>Cliente</th>
			<th>Equipe</th>
			<th>Observação</th>
			<th colspan="2">Ações</th>
		</tr>

		<c:forEach var="projeto" items="${projetos}">
		
			
			<tr>
				
				<td>${projeto.idProjeto}</td>

				<td>${projeto.nomeProjeto}</td>

				<td>${projeto.cliente.nome}</td>

				<td>${projeto.equipe.nome}</td>
				
				<td>${projeto.observacao}</td>

				<td><a href="mvc?logica=projeto.FormularioAtualizarProjetoLogica&idProjeto=${projeto.idProjeto}">Alterar</a></td>

				<td><a href="mvc?logica=projeto.DeletarProjetoLogica&idProjeto=${projeto.idProjeto}">Deletar</a></td>

			</tr>
		
		</c:forEach>

		<br>
		<a href="index.html">Retornar a página inicial</a>
</body>

</html>