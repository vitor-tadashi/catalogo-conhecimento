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

			<c:forEach var="funcionarioEquipe" items="${funcionarioEquipe}">

			<tr>
				<td>${funcionarioEquipe.nome}</td>
				<td>${funcionarioEquipe.email}</td>		
				<td>
					<c:forEach var="tecnologia" items="${funcionarioEquipe.tecnologias}">
						<c:out value="${tecnologia.nome}" /> 
					</c:forEach>
			</c:forEach>
		</table>
		<form name="" method="POST" action="mvc">
				<p>
			<input type="submit" value="Inserir">
				</p>
					
			<select class="form-control input-sm" name="equipe">
				<c:forEach var="funcionario" items="${funcionarios}">	
					<option value="${funcionario.id}">${funcionario.nome}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="logicaAtual" value="equipe.ListarFuncionariosPorEquipeLogica" />
			<input type="hidden" name="logica" value="equipe.InserirNegocioLogica">
										
			
		</form>
				
	</body>
</html>
