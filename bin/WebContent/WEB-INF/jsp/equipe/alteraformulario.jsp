<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catálogo de Conhecimentos</title>
</head>
<body>

	<form></form>
	<form method="POST" action="mvc?logica=equipe.AlteraEquipeLogic">
		<c:forEach var="equipe" items="${equipes}">

			<p>
				Observação:<input type="text" name="name">
				value="${equipe.observacao}">
			</p>
			<input type="hidden" name="logica" value="AlteraFilmeLogic">

			<input type="submit" value="Alterar">

		</c:forEach>
	</form>

</body>
</html>