<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<title>Lista de Equipes</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import> 
</head>
<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp"></c:import> 

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>

				<li>Equipes</li>
				<li class="active">Listar Equipes</li>
				
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-sm">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive">
							<div class="panel-heading">
								<h3>Lista de Equipes</h3>
								
								<!-- Message Erro-->
								<c:import url="/resources/jspImport/msgErro.jsp"/>
								
								<span class="label label-info pull-right">${fn:length(equipes)}
									registros</span>
							</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Nome</th>
											<th>Observação</th>
											<th style="width: 20px;">Alterar</th>
											<th style="width: 20px;">Excluir</th>
											<th style="width: 20px;">Listar Funcionários</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="equipe" items="${equipes}">
											<tr>
												<td>${equipe.nome}</td>
												<td>${equipe.observacao}</td>
												<td style="text-align: center;">
													<a href="
													<c:url value='/equipe/formularioAlterarEquipe'>
													<c:param name='idEquipe' value='${equipe.id}'/>
													</c:url>
													">
													<i class="fa fa-edit fa-lg"></a></td>
												<td style="text-align: center;">
													<a href="
													<c:url value='excluirEquipe'>
													<c:param name='idEquipe' value='${equipe.id}'/>
													<c:param name='paginaAtual' value='listarEquipe'/>
													<c:param name='ativo' value='N'/>
													</c:url>
													">
													<i class="fa fa-times fa-lg"></a></td>
													
												<td style="text-align: center;">	
													<a href="
													<c:url value='/equipe/listarFuncionarioPorEquipe'>
													<c:param name='idEquipe' value='${equipe.id}'/>
													</c:url>
													">
														<i class="fa fa-users fa-lg" ></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="panel-footer text-left">
								<a href="<c:url value='/equipe/formularioAdicionarEquipe'/>">
										<button class="btn btn-success" type="submit">Cadastrar Nova Equipe</button>
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
	
	<c:import url="/resources/jspImport/logout.jsp"></c:import>
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	

</body>
</html>
				