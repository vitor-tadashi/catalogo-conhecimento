<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualizar Cargo</title>
</head>
<body>
<form method="post" action="mvc">
	
	<input type="hidden" name="id" value="${cargo.idCargo}">
	
	Cargo:<input type="text" name="nome" value="${cargo.nomeCargo}">

	<input type="hidden" name="logica" value="cargo.AtualizarCargoLogica">	
	<input type="submit" value="Alterar">
	
	
</form>
</body>
</html>