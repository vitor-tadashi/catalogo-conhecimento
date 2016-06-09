<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserir Area</title>
</head>
<body>											   
	<form method="POST" action="mvc">
	             
		<p>Area:<input type="text" name="areaAtuacao" required></p>
		<input type="hidden" name="logica" value="negocio.InserirNegocioLogica">
		<p>
			<input type="submit" value="Inserir">
		</p>
	
	</form>
</body>
</html>