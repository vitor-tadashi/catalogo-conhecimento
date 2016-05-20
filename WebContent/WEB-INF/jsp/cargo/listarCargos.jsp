<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Cargos</title>
</head>
<body>

	<table border="solid 1px">
		<tr>
			<th>Cargo</th>
			<th>Ações</th>
		</tr>

		<c:forEach var="cargos" items="${cargos}">

			<tr>
				<td>${cargos.nomeCargo}</td>
				<td>
					<a href="mvc?logica=cargo.FormularioAtualizarCargoLogica&idCargo=${cargos.idCargo}">Alterar</a>
					<a href="mvc?logica=cargo.DeletarCargoLogica&idCargo=${cargos.idCargo}">Remover</a>
				</td>
			</tr>

		</c:forEach>
	</table>

	<a href="mvc?logica=cargo.FormularioInserirCargoLogica">Cadastrar Novo Cargo</a>
	<br>
	<a href="index.html">Retornar a página inicial</a>

</body>
</html>