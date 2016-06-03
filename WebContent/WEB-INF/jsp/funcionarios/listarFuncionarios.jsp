<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Lista de Funcionários</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import> 
	
	<script type="text/javascript">
	

	$(document).on("click", "#btnEquipesPorFuncionario", function() { 
	
	$.post("ajax?logica=busca.BuscarEquipePorFuncionarioNoProjetoAjaxLogica", { idFuncionario: $(this).attr('id-funcionario')}, function(listaEquipes) {
		$("#dataTableEquipe tbody tr").detach();	
		$.each(listaEquipes, function(index, item) { // Iterate over the JSON array.
			drawRowEquipe(item);
	        });
        });
    });	
	
	        function drawRowEquipe(rowData) {
		    var row = $("<tr />")

		    $("#dataTableEquipe tbody").append(row);
            row.append($("<td>" + rowData.nome + "</td>"));
	}
	
	</script>
	
	
	
</head>
<body>

	<c:import url="/resources/jspImport/header.jsp"></c:import> 
	
	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.jsp">
						Principal</a></li>
				<li>Funcionários</li>
				<li class="active">Lista de Funcionários</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
			<div class=" col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive">
							<div class="panel-heading">
								<h3>Lista de Funcionários</h3>
								
								<!-- Message Erro-->
								<c:import url="/resources/jspImport/msgErro.jsp"/>
								
								<span class="label label-info pull-right">${fn:length(funcionarios)}
									registros</span>
							</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Nome</th>
											<th>Telefone</th>
											<th>Tecnologia(s)</th>
											<th>Negócio(s)</th>
											<th>Equipes</th>
											<th>Email</th>
											<th>Cargo</th>
											<th>CPF</th>
											<th>Data de Nascimento</th>
											<th style="width: 20px;">Alterar</th>
											<th style="width: 20px;">Excluir</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="funcionario" items="${funcionarios}">
											<tr>
												<td>${funcionario.nome}</td>							
												<td>${funcionario.telefone}</td>
													
												<td>
													<select>
													<c:forEach var="tecnologia" items="${funcionario.tecnologias}">
														<option>${tecnologia.nome}</option>
													</c:forEach>
													</select>										
												</td>
												
												<td>
													<select>
													<c:forEach var="negocio" items="${funcionario.negocios}">
														<option>${negocio.areaAtuacao}</option>
													</c:forEach>
													</select>										
												</td>
																			
												
												<td>
												<a id="btnEquipesPorFuncionario" id-funcionario="${funcionario.id}" href="#simpleModalEquipe" role="button" data-toggle="modal" class="btn btn-primary btn-small">Equipes</a>
												</td>
																		
												<td>${funcionario.email}</td>											
												<td>${funcionario.cargo.nome}</td>
												<td>${funcionario.cpf}</td>
												<fmt:parseDate value="${funcionario.dataNascimento}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
												<td><fmt:formatDate value="${parsedDate}"  type="date" pattern="dd/MM/yyyy" /></td>
												
							
													
												<td style="text-align: center;"><a
													href="mvc?logica=funcionario.FormularioAlterarLogica&nomeFuncionario=${funcionario.nome}">
													<i class="fa fa-edit fa-lg"></a></td>
												<td style="text-align: center;"><a
													href="mvc?logica=funcionario.RemoverFuncionarioLogica&idFuncionario=${funcionario.id}">
													<i	class="fa fa-times fa-lg"></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="panel-footer text-left">
									<a href="mvc?logica=funcionario.FormularioAdicionarFuncionarioLogica">
										<button class="btn btn-success" type="submit">Cadastrar
										Novo Funcionário</button>
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
	</div>
	<!-- /main-container -->
	
		<div class="modal fade" id="simpleModalEquipe">
 			<div class="modal-dialog">
   			<div class="modal-content">
     				<div class="modal-header">
       				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4>Equipes</h4>
     				</div>
			    <div class="modal-body">
			        <table class="table table-striped" id="dataTableEquipe">
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
	</div> 
	
	<c:import url="/resources/jspImport/logout.jsp"></c:import>
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	

</body>
</html>