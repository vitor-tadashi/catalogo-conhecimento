<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Listar Concorrentes do Cliente</title>
	</head>
	<body>
		<h2>Cliente ${cliente.nome}</h2>

		<table border="1">
			<tr>
				<th>ID</th>
				<th>Nome do Concorrente</th>
				<th>Descrição</th>
				<th>Valor / Hora</th>
			</tr>

			<c:forEach var="concorrenteCliente" items="${concorrentesClientes}">
				<tr>
					<td>${concorrenteCliente.concorrente.id}</td>
					<td>${concorrenteCliente.concorrente.nome}</td>
					<td>${concorrenteCliente.concorrente.descricao}</td>
					<td>${concorrenteCliente.valorHora}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>
