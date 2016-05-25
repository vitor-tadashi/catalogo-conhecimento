<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catálogo de Conhecimentos</title>
</head>
<body>

	<h2>Equipe(Atualizar)</h2>

	<form method="POST" action="mvc">


		<p>
			Id:<input type="text" name="idEquipe" value="${equipes.id}" readonly>
		</p>
		<p>
			Nome:<input type="text" name="nome" value="${equipes.nome}" required>
		</p>
		<p>
			Observacao:<input type="text" name="observacao"
				value="${equipes.observacao}" required>
		</p>


		<input type="hidden" name="logica"
			value="equipe.AtualizarEquipeLogica"> <input type="submit"
			value="Atualizar"> 
			
		<input type="hidden" name="logicaAtual"
			value="equipe.FormularioAtualizarEquipeLogica">

	</form>

</body>
</html>