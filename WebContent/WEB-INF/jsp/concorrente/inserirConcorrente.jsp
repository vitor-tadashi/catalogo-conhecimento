<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserir Concorrente</title>
</head>
<body>

	<form method="post" action="mvc?logica=concorrente.InserirConcorrenteLogic">
		<p>Nome Concorrente: <input type="text" name="nomeConcorrente" /></p>
		<p>Descrição: <input type="text" name="descricao" /></p> 
		
		<input type="hidden" name="logica" value="concorrente.InserirConcorrenteLogic">
		<input type="submit" value="Inserir">
	</form>
</body>
</html>
