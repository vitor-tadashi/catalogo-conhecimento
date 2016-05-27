<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Listar Concorrente</title>
</head>
<body>
	<h2>Concorrente</h2>
	<table border="1">
		<tr>
			<th>Nome</th>
			<th>Descrição</th>
			<th>Ações</th>
		</tr>
		<c:forEach var="concorrente" items="${concorrentes}">
			<tr>
				<td>${concorrente.nome}</td>
				<td>${concorrente.descricao}</td>
				<td><a href="mvc?logica=concorrente.FormularioAtualizarConcorrenteLogica&id=${concorrente.id}">Atualizar</a>
					<a href="mvc?logica=concorrente.RemoverConcorrenteLogica&id=${concorrente.id}">Remover</a>
					<a href="mvc?logica=concorrente.ListarClientePorConcorrenteLogica&id=${concorrente.id}">Listar Clientes</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<a href="mvc?logica=concorrente.FormularioAdicionarConcorrenteLogica">Adicionar Concorrente</a>
	<br />
	<a href="index.html">Retornar a página inicial</a>
</body>
</html>