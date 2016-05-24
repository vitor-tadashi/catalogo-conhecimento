<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Funcion�rios</title>
</head>
<body>

	<table border="solid 1px">
		<tr>
			<th>Nome</th>
			<th>Telefone</th>
			<th>Usu�rio</th>
			<th>Email</th>
			<th>Cargo</th>
			<th>Tecnologia</th>
			<th>A��es</th>
			
		</tr>

		<c:forEach var="funcionario" items="${funcionarios}">

			<tr>
				<td>${funcionario.nome}</td>
				<td>${funcionario.telefone}</td>
				<td>${funcionario.nomeUser}</td>
				<td>${funcionario.email}</td>
				<td>${funcionario.cargo.nome}</td>		
				<td>
					<c:forEach var="tecnologia" items="${funcionario.tecnologias}">
						<c:out value="${tecnologia.nome}" /> 
					</c:forEach>
				</td>		
				<td>
					<a href="mvc?logica=funcionario.FormularioAlterarLogica&idFuncionario=${funcionario.id}">Alterar</a>
					<a href="mvc?logica=funcionario.RemoverFuncionarioLogica&idFuncionario=${funcionario.id}">Remover</a>
				</td>
			</tr>

		</c:forEach>
	</table>

	<a href="mvc?logica=funcionario.FormularioCriarLogica">Adicionar Funcionario</a>
	<br>
	<a href="index.html">Retornar a p�gina inicial</a>

</body>
</html>