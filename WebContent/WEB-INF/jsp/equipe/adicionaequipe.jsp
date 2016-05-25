<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserir Equipe</title>
</head>
<body>
<h2>Equipe(Adicionar)</h2>
	<form method="POST" action="mvc">
	
		<p>Nome:<input type="text" name="nome" required></p>
		<p>Observação:<input type="text" name="observacao" required></p>
		<input type="hidden" name="logica" value="equipe.InserirEquipeLogica">
		<input type="hidden" name="logicaAtual" value="equipe.FormularioInserirEquipeLogica">
		
		<p>
			<input type="submit" value="Inserir">
		</p>
		
		
		
	</form>
</body>
</html>