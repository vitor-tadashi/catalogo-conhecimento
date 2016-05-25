<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Adicionar Cargo</title>
	</head>
	<body>
		<h3>Adicionar Cargo</h3>
		<form method="POST" action="mvc">
			<p>
				Cargo:<input type="text" name="nome">
			</p>
			<input type="hidden" name="logicaAtual" value="cargo.FormularioAdicionarCargoLogica">
			<input type="hidden" name="logica" value="cargo.AdicionarCargoLogica">
			<p>
				<input type="submit" value="Adicionar">
			</p>
		</form>
	</body>
</html>