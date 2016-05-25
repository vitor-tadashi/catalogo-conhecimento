<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Inserir Projeto</title>
</head>
<body>
	<form method="POST"
		action="mvc">

		<p>
			Nome do Projeto:<input type="text" name="nomeProjeto">
			<br>
			Observação:<input type="text" name="observacao">
			<br>
			Equipe:<select name="equipe">
			<c:forEach items="${equipes}" var="equipe">
				<option value="${equipe.id}">${equipe.id}</option>
			</c:forEach>
			</select>
		<br>
		Negócio
		<br>
		<c:forEach items="${negocios}" var="negocios">
			<input type="checkbox" name="negociosArray[]"value="${negocios.areaAtuacao}" />${negocios.areaAtuacao}
			<br>
		</c:forEach>
		
		Cliente:<select name="cliente">
			<c:forEach items="${clientes}" var="cliente">
				<option value="${cliente.id}">${cliente.nome}</option>
			</c:forEach>
			</select>
		</p>
		<input type="hidden" name="logica" value="projeto.InserirProjetoLogica">
		<input type="hidden" name = "logicaAtual" value = "projeto.FormularioInserirProjetoLogica">
		<p>
			<input type="submit" value="Inserir">
		</p>
	</form>
</body>
</html>