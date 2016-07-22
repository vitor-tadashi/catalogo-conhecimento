<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Listar Funcionarios Por Equipe</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import> 
	<script type="text/javascript">
	
	$(document).on("click", "#btnTecnologia", function() { 
	$.post("<c:url value = 'buscarTecnologiaPorFuncionario'/>", { idFuncionario: $(this).attr('id-funcionario')}, function(listaTecnologias) {
	    $("#dataTableTecnologia tbody tr").detach();	
	    $.each(listaTecnologias, function(index, item) { // Iterate over the JSON array.
	        drawRowTecnologia(item);
	       });
	    });
	});	
	        	
	      function drawRowTecnologia(rowData) {
	      var row = $("<tr />")

	      $("#dataTableTecnologia tbody").append(row);
	      row.append($("<td>" + rowData.nome + "</td>"));
	      }
	</script>
</head>
<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp"></c:import> 

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>

				<li>Equipes</li>
				<li>Listar Equipes</li>
				<li class="active">Listar Funcionarios Por Equipe</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-sm">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive">
							<div class="panel-heading">
								<h3>Equipe ${equipe.nome}</h3>
								
								<!-- Message Erro-->
								<c:import url="/resources/jspImport/msgErro.jsp"></c:import>
													
								<span class="label label-info pull-right">${fn:length(funcionarioEquipe)}
									registros</span>
							</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Funcionário</th>
											<th>E-mail</th>
											<th>Tecnologia</th>
											<th style="width: 20px;">Excluir</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="funcionarioEquipe" items="${funcionarioEquipe}">
											<tr>
												<td>${funcionarioEquipe.nome}</td>
												<td>${funcionarioEquipe.email}</td>	
												<td>
													<a id="btnTecnologia" id-funcionario="${funcionarioEquipe.id}" href="#simpleModalTecnologia" role="button" data-toggle="modal" class="btn btn-primary btn-small">Tecnologias</a>										
												</td>
												<td>
													<a href="
													<c:url value='/equipe/deletarFuncionarioPorEquipe'>
													<c:param name='idEquipe' value='${equipe.id}'/>
													<c:param name='idFuncionario' value='${funcionarioEquipe.id}'/>
													</c:url>
													">
													<i class="fa fa-times fa-lg"></i></a>
												</td>
			 								<tr>
			 							</c:forEach>
			 						</tbody>	
								</table>
								<form name="formAdicionaFuncionarioEquipe" method="POST" action="adicionarFuncionarioNaEquipe">
									<div class="panel-heading">
										<h4>Inserir Funcionário na Equipe</h4>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<select class="form-control input-sm" name="idFuncionario">
														<c:forEach var="funcionario" items="${funcionarios}">	
															<option value="${funcionario.id}">${funcionario.nome}</option>
														</c:forEach>
													</select>		
													<input type="hidden" name="idEquipe" value="${equipe.id}">							
												</div>		
												<div class="form-group">
													<button class="btn btn-success" type="submit">Inserir Funcionário</button>
												</div>
											</div>
										</div>
									</div>									
								</form>
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
	<div class="modal fade" id="simpleModalTecnologia">
 			<div class="modal-dialog">
   			<div class="modal-content">
     				<div class="modal-header">
       				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4>Tecnologias</h4>
     				</div>
			    <div class="modal-body">
			        <table class="table table-striped" id="dataTableTecnologia">
						<thead >
							<tr>
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
	
	<c:import url="/resources/jspImport/logout.jsp"></c:import>
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	

</body>
</html>