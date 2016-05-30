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
						Principal</a></li>
				<li>Projetos</li>
				<li class="active">Listar Projetos</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-sm">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive">
							<div class="panel-heading">
								<h3>Listar Projetos</h3>
								<span class="label label-info pull-right">${fn:length(projetos)}
									registros</span>
							</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Identificação</th>
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
												<td>${projeto.id}</td>							
												<td>${projeto.nome}</td>
												<td>
													<select>
													<c:forEach var="negocio" items = "${projeto.listaNegocio}">
														<option>${negocio.areaAtuacao}</option>
													</c:forEach>
													</select>
																							
												</td>	
												<td>
													<select>
													<c:forEach var="tecnologia" items = "${projeto.listaTecnologia}">
														<option>${tecnologia.nome}</option>
													</c:forEach>
													</select>
																							
												</td>							
												<td>${projeto.cliente.nome}</td>							
												<td>
												<select>
													<c:forEach var="equipe" items = "${projeto.listaEquipe}">
														<option>${equipe.nome}</option>
													</c:forEach>
													</select>
												
												</td>											
												<td>${projeto.observacao}</td>	
												<td style="text-align: center;"><a
													href="mvc?logica=projeto.FormularioAtualizarProjetoLogica&idProjeto=${projeto.id}">
													<i class="fa fa-edit fa-lg"></a></td>
												<td style="text-align: center;"><a
													href="mvc?logica=projeto.DeletarProjetoLogica&idProjeto=${projeto.id}">
													<i	class="fa fa-times fa-lg"></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="panel-footer text-left">
									<a href="mvc?logica=projeto.FormularioInserirProjetoLogica">
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
	</div>
	<!-- /main-container -->
	</div> 
	
	<c:import url="/resources/jspImport/logout.jsp"></c:import>
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	

</body>
</html>