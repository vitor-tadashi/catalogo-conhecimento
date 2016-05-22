<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Tecnologias</title>
</head>
<body>
	<table border="1px">
		<tr>
			<th>Nome</th>
			<th>Ações</th>
		</tr>

		<c:forEach var="tecnologia" items="${tecnologias}">
			<tr>
				<td>${tecnologia.nomeTecnologia}</td>

				<td>
					<a href="mvc?logica=tecnologia.DeletarTecnologiaLogica&idTecnologia=${tecnologia.idTecnologia}">Remover</a>
					<a href="mvc?logica=tecnologia.FormularioAtualizarTecnologiaLogica&idTecnologia=${tecnologia.idTecnologia}">Atualizar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<a href="mvc?logica=tecnologia.FormularioInserirTecnologiaLogica">Adicionar
			uma nova tecnologia</a>
	</p>
</body>
</html>