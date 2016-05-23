<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Atualizar Cliente</title>
	</head>
	<body>
		<form method="POST" action="mvc?logica=cliente.AlterarClienteLogica">
			<input type="hidden" name="id" value="${cliente.id}">
			<p>Nome:<input type="text" name="nome" value="${cliente.nome}"></p>
			<p>Logradouro:<input type="text" name="logradouro" value="${cliente.logradouro}"></p>
			<p>Numero:<input type="text" name="numero" value="${cliente.numero}"></p>
			<p>CEP:<input type="text" name="cep" value="${cliente.cep}"></p>
			<p>CNPJ:<input type="text" name="cnpj" value="${cliente.cnpj}"></p>
			<p>Email:<input type="text" name="email" value="${cliente.email}"></p>
			<p><input type="submit" value="Atualizar"></p>
		</form>
	</body>
</html>