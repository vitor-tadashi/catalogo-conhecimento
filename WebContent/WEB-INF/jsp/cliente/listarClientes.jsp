<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Lista Clientes</title>
<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>

<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp" />

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.html">
						Principal</a></li>
				<li>Clientes</li>
				<li class="active">Lista de Clientes</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive">
							<div class="panel-heading">
								<h3>Lista de Clientes</h3>
								<span class="label label-info pull-right">${fn:length(clientes)}
									registros</span>
							</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Nome</th>
											<th>CNPJ</th>
											<th>E-mail</th>
											<th>Logradouro</th>
											<th>Número</th>
											<th>CEP</th>
											<th style="width: 20px;">Alterar</th>
											<th style="width: 20px;">Excluir</th>
											<th style="width: 20px;">Listar Concorrentes</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="cliente" items="${clientes}">
											<tr>
												<td>${cliente.nome}</td>
												<td>${cliente.cnpj}</td>
												<td>${cliente.email}</td>
												<td>${cliente.logradouro}</td>
												<td>${cliente.numero}</td>
												<td>${cliente.cep}</td>
												<td style="text-align: center;"><a
													href="mvc?logica=cliente.FormularioAlterarClienteLogica&id=${cliente.id}"><i class="fa fa-edit fa-lg"></i></a>
												</td>
												<td style="text-align: center;"><a
													href="mvc?logica=cliente.RemoverClienteLogica&id=${cliente.id}"><i class="fa fa-times fa-lg"></i></a>
												</td> 
												<td style="text-align: center;">	
													<a href="mvc?logica=concorrente.ListarConcorrentePorClienteLogica&id=${cliente.id}">
														<i class="fa fa-users fa-lg" ></i></a>
												</td>													
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="panel-footer text-left">
									<a href="mvc?logica=cliente.FormularioAdicionarClienteLogica">
										<button class="btn btn-success" type="submit">Adicionar Novo Cliente</button>
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
	<!-- main-container -->
	
		<!-- Import Logout Action -->
		<c:import url="/resources/jspImport/logout.jsp" />

		<c:import url="/resources/jspImport/footer.jsp"></c:import>
</body>

</html>