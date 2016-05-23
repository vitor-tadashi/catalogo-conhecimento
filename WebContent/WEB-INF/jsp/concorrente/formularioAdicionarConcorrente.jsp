<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar Concorrente</title>
</head>
<body>

	<form method="post" action="AdicionarConcorrenteLogica">
		<p>Nome Concorrente: <input type="text" name="nome" /></p>
		<p>Descrição: <input type="text" name="descricao" /></p> 
		
		<input type="hidden" name="logica" value="concorrente.AdicionarConcorrenteLogica">
		<input type="submit" value="Adicionar">
	</form>
</body>
</html>
