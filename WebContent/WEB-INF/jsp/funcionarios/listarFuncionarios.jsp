<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<th>CPF</th>
			<th>RG</th>			
			<th>Data de Nascimento</th>
			<th>Tecnologia</th>
			<th>Ações</th>
			
		</tr>

		<c:forEach var="funcionario" items="${funcionarios}">

			<tr>
				<td>${funcionario.nome}</td>
				<td>${funcionario.telefone}</td>
				<td>${funcionario.nomeUser}</td>
				<td>${funcionario.email}</td>
				<td>${funcionario.cargo.nome}</td>
				<td>${funcionario.cpf}</td>
				<td>${funcionario.rg}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.dataNascimento}" /></td>
				<td>
					<c:forEach var="tecnologia" items="${funcionario.tecnologias}">
						<c:out value="${tecnologia.nome}" /> 
					</c:forEach>
				</td>
				<td>
					<a href="mvc?logica=funcionario.FormularioAlterarLogica&nomeFuncionario=${funcionario.nome}">Alterar</a>
					<a href="mvc?logica=funcionario.RemoverFuncionarioLogica&idFuncionario=${funcionario.id}">Remover</a>
				</td>
			</tr>

		</c:forEach>
	</table>

	<a href="mvc?logica=funcionario.FormularioInserirFuncionarioLogica">Adicionar Funcionario</a>
	<br>
	<a href="index.html">Retornar a página inicial</a>

</body>
</html>