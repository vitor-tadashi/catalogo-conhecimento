<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar ConcorrenteCliente</title>
</head>
<body>

<form method="post" action="mvc">
	Código: <input type = "text" name = "idConcorrenteCliente" value="${listarconcorrentecliente.idConcorrenteCliente}"/><br/>
	Valor/Hora: <input type = "text" name = "valorhora" value="${listarconcorrentecliente.valorhora}" /><br/>
		
	<input type="hidden" name="logica" value ="AlterarConcorrenteClienteLogica"/>
	<input type="submit" value ="Alterar">
</form>
</body>
</html>