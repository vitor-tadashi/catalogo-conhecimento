<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lista de Projetos</title>
<c:import url="/resources/jspImport/head.jsp"></c:import>

<script type="text/javascript">
	
		
		
		$(document).on("click", "#btnTecnologiaPorProjeto", function() { 
			
			$.post("<c:url value = '/busca/buscarTecnologiaPorProjeto'/>", { idProjeto: $(this).attr('id-projeto') }, function(listaTecnologiaProjeto){
				$("#dataTableTecnologias tbody tr").detach();	
				$.each(listaTecnologiaProjeto, function(index, item) { // Iterate over the JSON array.
						drawRowTecnologias(item);
			        });
	            });
	        });	
		
		$(document).on("click", "#btnTecnologiaPorFuncionario", function() { 
			
			$.post("<c:url value = '/busca/buscarTecnologiaPorFuncionario'/>", { idFuncionario: $(this).attr('id-funcionario') }, function(listaTecnologiaFuncionario){
				$("#dataTableTecnologias tbody tr").detach();	
				$.each(listaTecnologiaFuncionario, function(index, item) { // Iterate over the JSON array.
						drawRowTecnologias(item);
			        });
	            });
	        });
		
		$(document).on("click", "#btnNegocioPorProjeto", function() { 
			
			$.post("<c:url value = '/busca/buscarNegocioPorProjeto'/>", { idProjeto: $(this).attr('id-projeto') }, function(listaNegocioProjeto) {
				$("#dataTableNegocio tbody tr").detach();	
				$.each(listaNegocioProjeto, function(index, item) { // Iterate over the JSON array.
					drawRowNegocios(item);
			        });
		        });
		    });	
		    
		$(document).on("click", "#btnEquipePorProjeto", function() { 
			
			$.post("<c:url value = '/busca/buscarEquipePorProjeto'/>", { idProjeto: $(this).attr('id-projeto') }, function(listaEquipeProjeto) {
				$("#dataTableEquipe tbody tr").detach();	
				$.each(listaEquipeProjeto, function(index, item) { // Iterate over the JSON array.
					drawRowEquipe(item);
			        });
		        });
		    });	
		
		$(document).on("click", "#btnFuncionarioPorEquipe", function() { 
			
			$.post("<c:url value = '/busca/buscarFuncionariosPorEquipe'/>", { idEquipe: $(this).attr('id-equipe') }, function(listaFuncionarioEquipe) {
				$("#dataTableFuncionario tbody tr").detach();	
				$.each(listaFuncionarioEquipe, function(index, item) { // Iterate over the JSON array.
					drawRowFuncionario(item);
			        });
		        });
		    });	
				
				function drawRowTecnologias(rowData) {
				    var row = $("<tr />")
				    
				    $("#dataTableTecnologias tbody").append(row);
		            row.append($("<td>"+ rowData.nome+"</td>"));
				}
				
				function drawRowNegocios(rowData) {
				    var row = $("<tr />")
				    
				    $("#dataTableNegocio tbody").append(row);
		            row.append($("<td>" + rowData.areaAtuacao + "</td>"));
				}
				
				function drawRowEquipe(rowData) {
				    var row = $("<tr />")
				    
				    $("#dataTableEquipe tbody").append(row);
		            row.append($("<td><a id='btnFuncionarioPorEquipe' id-equipe="+rowData.id+" href='#simpleModalFuncionario' role='button' data-toggle='modal' class='btn btn-primary btn-small'>"+rowData.nome+"</a></td>"));
				}
				
				function drawRowFuncionario(rowData) {
				    var row = $("<tr />")
				    
				    $("#dataTableFuncionario tbody").append(row);
		            row.append($("<td>" + rowData.nome + "</td>"));
				}
			</script>

</head>
<body>
	<c:import url="/resources/jspImport/header.jsp"></c:import>

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>
				<li>Projetos</li>
				<li class="active">Lista de Projetos</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-sm">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive">
							<div class="panel-heading">
								<h3>Lista de Projetos</h3>

								<!-- Message Erro-->
								<c:import url="/resources/jspImport/msgErro.jsp" />

								<span class="label label-info pull-right">${fn:length(projeto)}
									registros</span>
							</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Nome do Projeto</th>
											<th>Área de Atuação</th>
											<th>Tecnologia</th>
											<th>Cliente</th>
											<th>Equipe</th>
											<th>Observação</th>
											<th style="width: 20px;">Alterar</th>
											<th style="width: 20px;">Excluir</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="projeto" items="${projetos}">
											<tr>
												<td>${projeto.nome}</td>
												<td>
												<a id="btnNegocioPorProjeto" id-projeto="${projeto.id}" href="#simpleModalNegocio" role="button" data-toggle="modal" class="btn btn-primary btn-small">Negócio</a>
												</td>
												<td>
												
												<a id="btnTecnologiaPorProjeto" id-projeto="${projeto.id}" href="#simpleModalTecnologia" role="button" data-toggle="modal" class="btn btn-primary btn-small">Tecnologias</a>
												</td>
												<td>${projeto.cliente.nome}</td>
												<td>
													<a id="btnEquipePorProjeto" id-projeto="${projeto.id}" href="#simpleModalEquipe" role="button" data-toggle="modal" class="btn btn-primary btn-small">Equipes</a>
												
												</td>
												<td>${projeto.observacao}</td>
												<td style="text-align: center;"><a
													href="
													<c:url value='/projeto/formularioAlterarProjeto'>
													<c:param name='idProjeto' value='${projeto.id}'/>
													</c:url>
													">
													<i class="fa fa-edit fa-lg">
												</a></td>
												<td style="text-align: center;"><a
												href="
													<c:url value='removerProjeto'>
													<c:param name='idProjeto' value='${projeto.id}'/>
													</c:url>
													">
														<i class="fa fa-times fa-lg">
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="panel-footer text-left">
									<a href="<c:url value='/projeto/formularioAdicionarProjeto'/>">
										<button class="btn btn-success" type="submit">Cadastrar
											Novo Projeto</button>
									</a>
								</div>
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

		<!--Modal-->
	<div class="modal fade" id="simpleModalTecnologia">
 			<div class="modal-dialog">
   			<div class="modal-content">
     				<div class="modal-header">
       				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4>Tecnologias</h4>
     				</div>
			    <div class="modal-body">
			        <table class="table table-striped" id="dataTableTecnologias">
						<thead >
							<tr>
								<th>Tecnologia</th>
							</tr>
						</thead>
						<tbody >
							
						</tbody>
					</table>
			    </div>
			    <div class="modal-footer">
			        <button class="btn btn-sm btn-success" data-dismiss="modal" aria-hidden="true">Fechar</button>
			    </div>
		  	</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
		<div class="modal fade" id="simpleModalNegocio">
 			<div class="modal-dialog">
   			<div class="modal-content">
     				<div class="modal-header">
       				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4>Negocio</h4>
     				</div>
			    <div class="modal-body">
			        <table class="table table-striped" id="dataTableNegocio">
						<thead >
							<tr>
								<th>Área de Atuação</th>
							</tr>
						</thead>
						<tbody >
							
						</tbody>
					</table>
			    </div>
			    <div class="modal-footer">
			        <button class="btn btn-sm btn-success" data-dismiss="modal" aria-hidden="true">Fechar</button>
			    </div>
		  	</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div class="modal fade" id="simpleModalEquipe">
 			<div class="modal-dialog">
   			<div class="modal-content">
     				<div class="modal-header">
       				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4>Equipe</h4>
     				</div>
			    <div class="modal-body">
			        <table class="table table-striped" id="dataTableEquipe">
						<thead >
							<tr>
								<th>Equipes</th>
							</tr>
						</thead>
						<tbody >
							
						</tbody>
					</table>
			    </div>
			    <div class="modal-footer">
			        <button class="btn btn-sm btn-success" data-dismiss="modal" aria-hidden="true">Fechar</button>
			    </div>
		  	</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<div class="modal fade" id="simpleModalFuncionario">
 			<div class="modal-dialog">
   			<div class="modal-content">
     				<div class="modal-header">
       				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4>Funcionário</h4>
     				</div>
			    <div class="modal-body">
			        <table class="table table-striped" id="dataTableFuncionario">
						<thead >
							<tr>
								<th>Funcionários</th>
							</tr>
						</thead>
						<tbody >
							
						</tbody>
					</table>
			    </div>
			    <div class="modal-footer">
			        <button class="btn btn-sm btn-success" data-dismiss="modal" aria-hidden="true">Fechar</button>
			    </div>
		  	</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</div>
<!-- /main-container -->
</div>
<c:import url="/resources/jspImport/logout.jsp"></c:import>
<c:import url="/resources/jspImport/footer.jsp"></c:import>
</body>
</html>