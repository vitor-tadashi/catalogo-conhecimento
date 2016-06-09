<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Concorrente</title>
</head>
<body>
	<h2>Concorrente</h2>
	<table border="1">
		<tr>
			<th>Nome do Concorrente</th>
			<th>Descrição</th>
			<th>Ações</th>
			
			
		</tr>

		<c:forEach var="listarconcorrente" items="${concorrente}">
			<tr>
				<td>${listarconcorrente.nomeConcorrente}</td>
				
				<td>${listarconcorrente.descricao}</td>
				
				<td><a href="mvc?logica=FormularioAlterarLogica&idConcorrente=${listarconcorrente.idConcorrente}">Atualizar</a>
					
				<a href="mvc?logica=RemoverConcorrenteLogica&idConcorrente=${listarconcorrente.idConcorrente}">Remover</a>
					
			</td>
			</tr>
			
			
		</c:forEach>
	</table>
</body>
</html>