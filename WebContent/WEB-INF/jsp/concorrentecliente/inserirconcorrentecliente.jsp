<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserir ConcorrenteCliente</title>
</head>
<body>

<form method="post" action="mvc">
	
	Código: <input type = "text" name = "idConcorrenteCliente" /><br/>
	Valor/Hora: <input type = "text" name = "valorhora" /><br/>
	
	<input type="hidden" name="logica" value="InserirConcorrenteClienteLogica">
	<input type="submit" value ="Adicionar">
</form>

</body>
</html>