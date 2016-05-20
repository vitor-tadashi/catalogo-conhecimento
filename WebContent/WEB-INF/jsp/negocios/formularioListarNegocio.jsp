<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de negocio</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>Área de Atuação</th>
			<th>Ações</th>
		</tr>
		<c:forEach var="negocio" items="${negocios}">
			<tr>
				<td>${negocio.areaAtuacao}</td>
				<td><a
					href="mvc?logica=negocio.DeletarNegocioLogica&idNegocio=${negocio.idNegocio}">Remover</a>
				</td>
				<td><a
					href="mvc?logica=negocio.FormularioAtualizarNegocioLogica&idNegocio=${negocio.idNegocio}">Atualizar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<p>
		<a href="mvc?logica=negocio.FormularioInserirNegocioLogica"> Cadastrar Negócio</a>
	</p>
</body>
</html>