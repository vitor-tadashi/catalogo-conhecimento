<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Listar Funcionarios Por Equipe</title>
	</head>
	<body>
		<h2>Equipe ${equipe.nome}</h2>

		<table border="1">
			<tr>
				<th>Funcionário</th>
				<th>E-mail</th>
				<th>Tecnologia</th>
			</tr>

			<c:forEach var="Funcionario" items="${Funcionarios}">
				<tr>
						<td>${funcionario.nomeFuncionario}</td>
						<td>${funcionario.email}</td>

				</tr>
			</c:forEach>
		</table>
	
				<p>
			<input type="submit" value="Inserir">
				</p>
					
					
					<select size="1" name="D1">			
					<option selected value="Selecione">Funcionários</option>
					<option value="${funcionario.nomeFuncionario}">Lucas Pagialai</option>
					<option value="${funcionario.nomeFuncionario}">Rodrigo Ribeiro Nascimento</option>
					<option value="${funcionario.nomeFuncionario}">André Luiz Da Silva De Oliveira</option>
					<option value="${funcionario.nomeFuncionario}">Pedro Henrique Sobrinho De Jesus</option>
					<option value="${funcionario.nomeFuncionario}">José Silva De Araújo e Silva</option>
					<option value="${funcionario.nomeFuncionario}">Juliana Almeida Francisco Carapicuiba</option>
							
					</select>

				
	</body>
</html>
