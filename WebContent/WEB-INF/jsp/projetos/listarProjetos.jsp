<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Listar Projetos</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import> 
</head>
<body>

	<c:import url="/resources/jspImport/header.jsp"></c:import> 
	
	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.html">
					Projetos</li>
				<li class="active">Listar Projetos</li>
			</ul>
		</div>
	<Section width="100%" height="auto">
		<h3>Lista de projetos</h3>
	</Section>

	<table border="1">
	
		<tr>
			
			<th>Identificação</th>
			<th>Nome do Projeto</th>
			<th>Cliente</th>
			<th>Equipe</th>
			<th>Observação</th>
			<th colspan="2">Ações</th>
		</tr>

		<c:forEach var="projeto" items="${projetos}">
		
			
			<tr>
				
				<td>${projeto.id}</td>

				<td>${projeto.nome}</td>

				<td>${projeto.cliente.nome}</td>

				<td>${projeto.equipe.nome}</td>
				
				<td>${projeto.observacao}</td>

				<td><a href="mvc?logica=projeto.FormularioAtualizarProjetoLogica&idProjeto=${projeto.id}">Alterar</a></td>

				<td><a href="mvc?logica=projeto.DeletarProjetoLogica&idProjeto=${projeto.id}">Deletar</a></td>

			</tr>
		
		</c:forEach>

		<br>
		<a href="index.html">Retornar a página inicial</a>
		</div>
							
							
							
							<!-- /.padding-md -->
						</div>
						<!-- /panel -->
					</div>
					<!-- /tab2 -->

				</div>
				<!-- /tab-content -->
			</div>
			<!-- /.col -->

		</div>
		<!-- /.padding-md -->

	</div>
	<!-- /main-container -->
	</div> 
	
	<c:import url="/resources/jspImport/logout.jsp"></c:import>
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	

</body>
</html>