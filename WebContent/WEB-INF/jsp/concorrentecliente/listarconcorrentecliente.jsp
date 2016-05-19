<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar ConcorrenteCliente</title>
</head>
<body>
	<h2>Concorrente/Cliente</h2>
	<table border="1">
		<tr>
			<th>Valor/Hora</th>
			<th>Ações</th>
		</tr>
														   	   
		<c:forEach var="listarconcorrentecliente" items="${concorrentescliente}">
			<tr>
					  
				<td>${listarconcorrentecliente.valorhora}</td>
																								                                  
				<td><a href="mvc?logica=FormularioAlterarConcorrenteClienteLogica&idConcorrenteCliente=${listarconcorrentecliente.idConcorrenteCliente} ">
						Atualizar</a></td>
																							
				<td><a href="mvc?logica=RemoverConcorrenteClienteLogica&idConcorrenteCliente=${listarconcorrentecliente.idConcorrenteCliente} ">
						Remover</a></td>

			</tr>

		</c:forEach>

	</table>
</body>
</html>