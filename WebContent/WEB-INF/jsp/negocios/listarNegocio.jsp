<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Lista de Negócios</title>
		<c:import url="/resources/jspImport/head.jsp"></c:import>
	</head>
	<body>
		<c:import url="/resources/jspImport/header.jsp"></c:import>
		<div id="main-container" style="width: auto">
			<div id="breadcrumb">
				<ul class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.html">
							Principal</a></li>
					<li>Negócios</li>
					<li class="active">Lista de Negócios</li>
				</ul>
			</div>
			<!--breadcrumb-->
			<div class="padding-md">
				<div class="col-md-12 col-sm-12">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="research">
							<div class="panel panel-default table-responsive">
								<div class="panel-heading">
									<h3>Lista de Negócios</h3>
									
									<!-- Message Erro-->
									<c:import url="/resources/jspImport/msgErro.jsp"/>
									
									<span class="label label-info pull-right">${fn:length(listaNegocio)} registros</span>
								</div>
								<div class="padding-md clearfix">
									<table class="table table-striped" id="dataTable">
										<thead>
											<tr>
												<th>Área de Atuação</th>
												<th style="width: 20px;">Alterar</th>
												<th style="width: 20px;">Excluir</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="negocioBean" items="${listaNegocio}">
												<tr>
													<td>${negocioBean.areaAtuacao}</td>
													<td style="text-align: center;"><a
														href="mvc?logica=negocio.FormularioAlterarNegocioLogica&id=${negocioBean.id}">
														<i class="fa fa-edit fa-lg"></a></td>
													<td style="text-align: center;"><a
														href="mvc?logica=negocio.RemoverNegocioLogica&id=${negocioBean.id}">
														<i	class="fa fa-times fa-lg"></a></td>
													</tr>
											</c:forEach>
										</tbody>
									</table>
									<div class="panel-footer text-left">
										<a
											href="mvc?logica=negocio.FormularioAdicionarNegocioLogica">
											<button class="btn btn-success" type="submit">Cadastrar
												Novo Negócio</button>
										</a>
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