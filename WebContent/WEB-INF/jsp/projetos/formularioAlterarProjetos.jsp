<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form method="post" action="mvc?logica=projeto.AtualizarProjetoLogica">

			<input type="hidden" value="${projeto.id}" name="id">
			<p>
			Nome do Projeto:<input type="text" name="nomeProjeto" value="${projeto.nome}">
			<br>
			Observação:<input type="text" name="observacao" value="${projeto.observacao}">
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
		
		<input type="hidden" name = "logicaAtual" value = "projeto.FormularioAtualizarProjetoLogica&idProjeto=${projeto.id}">
		<p>
			<input type="submit" value="Atualizar">
		</p>

</form>

</body>
</html>