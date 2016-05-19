<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Funcionários</title>
</head>
<body>

	<table border="solid 1px">
		<tr>
			<th>Nome</th>
			<th>Telefone</th>
			<th>Usuário</th>
			<th>Email</th>
			<th>Cargo</th>
			<th>Ações</th>
			
		</tr>

		<c:forEach var="funcionarios" items="${funcionarios}">

			<tr>
				<td>${funcionarios.nomeFuncionario}</td>
				<td>${funcionarios.telefone}</td>
				<td>${funcionarios.nomeUser}</td>
				<td>${funcionarios.email}</td>
				<td>${funcionarios.idCargo}</td>
				<td>
					<a href="mvc?logica=funcionario.FormularioAlterarLogica&idFuncionario=${funcionarios.idFuncionario}">Alterar</a>
					<a href="mvc?logica=funcionario.DeletarFuncionarioLogica&idFuncionario=${funcionarios.idFuncionario}">Remover</a>
				</td>
			</tr>

		</c:forEach>
	</table>

	<a href="mvc?logica=FormularioCriarLogica">Adicionar Funcionario</a>
	<br>
	<a href="index.html">Retornar a página inicial</a>

</body>
</html>