<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Atualizar Tecnologia</title>
	</head>
	<body>
		<form method="POST" action="mvc">
			<p>Id:<input type="text" name="id" value="${tecnologia.idTecnologia}" readonly></p>
			<p>Nome:<input type="text" name="nome" value="${tecnologia.nomeTecnologia}"></p>
			<input type="hidden" name="logica" value="tecnologia.AtualizarTecnologiaLogica">
			<p><input type="submit" value="Atualizar"></p>
		</form>
	</body>
</html>