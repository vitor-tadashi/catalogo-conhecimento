<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Catálogo de Conhecimentos</title>
</head>
<body>

	<h2>Equipe(Listar)</h2>

	<table border="1px">

		<tr>
			<th>Nome</th>
			<th>Observação</th>
			<th>Ações</th>

		</tr>

		<c:forEach var="equipe" items="${equipes}">
			<tr>
				<td>${equipe.nome}</td>
				<td>${equipe.observacao}</td>

				<td><a
					href="mvc?logica=equipe.DeletarEquipeLogica&idEquipe=${equipe.idEquipe}">
						Remover </a></td>

				<td><a
					href="mvc?logica=equipe.FormularioAtualizarEquipeLogica&idEquipe=${equipe.idEquipe}">
						Atualizar </a></td>

			</tr>
		</c:forEach>
	</table>
	<p>
		<a href="mvc?logica=equipe.FormularioInserirEquipeLogica&idEquipe=$">Adicionar
			Equipe </a>

	</p>
</body>
</html>