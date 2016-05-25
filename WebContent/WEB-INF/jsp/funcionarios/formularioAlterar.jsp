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
<form method="post" action="mvc">
	<input type="hidden" name="id" value="${funcionario.id}">
	
	Nome:<input type="text" name="nome" value="${funcionario.nome}">
	
	telefone: <input type="text" name="telefone" value="${funcionario.telefone}">
	
	Email: <input type="email" name="email" value ="${funcionario.email}">
	
	Usuario: <input type="text" name="nomeUser" value = "${funcionario.nomeUser}">
	
	Cargo:<select name="cargos">
		<c:forEach items="${cargos}" var="cargos">
			<option value="${cargos.id}">${cargos.nome}</option>
		</c:forEach>
		</select>
	
	
	Tecnologias:<br>
		<c:forEach items="${tecnologias}" var="tecnologias">
			<input type="checkbox" name="tecnologiasArray"value="${tecnologias.nome}" />${tecnologias.nome}
			<br>
		</c:forEach>

	<input type="hidden" name="logica" value="funcionario.AlterarFuncionarioLogica">	
	<input type="submit" value="Alterar">
	
	
</form>
</body>
</html>