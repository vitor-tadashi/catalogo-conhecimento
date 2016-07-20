<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Lista de Clientes</title>
<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>

<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp" />

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>

				<li><a href="<c:url value='/home/index'/>">Clientes</a></li>
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
								
								<!-- Message Erro-->
								<c:import url="/resources/jspImport/msgErro.jsp"/>
								
								<span class="label label-info pull-right">${fn:length(listaCliente)}
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
										<c:forEach var="cliente" items="${listaCliente}">
											<tr>
												<td>${cliente.nome}</td>
												<td>${cliente.cnpj}</td>
												<td>${cliente.email}</td>
												<td>${cliente.logradouro}</td>
												<td>${cliente.numero}</td>
												<td>${cliente.cep}</td>
												<td style="text-align: center;">
													<a href="<c:url value='/cliente/alterar'> <c:param name='idCliente' value='${cliente.id}'/> </c:url>"><i class="fa fa-edit fa-lg"></i></a>
												</td>
												<td style="text-align: center;">
													<a href="<c:url value='/cliente/remover'> <c:param name='idCliente' value='${cliente.id}'/> </c:url>"><i class="fa fa-times fa-lg"></i></a>
												</td> 
												<td style="text-align: center;">	
													<a href="<c:url value='/cliente/listarConcorrentePorCliente'> <c:param name='idCliente' value='${cliente.id}'/> </c:url>"><i class="fa fa-users fa-lg" ></i></a>
												</td>													
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="panel-footer text-left">
									<a href="<c:url value='/cliente/adicionar'> </c:url>">
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
	</div>
		<!-- Import Logout Action -->
		<c:import url="/resources/jspImport/logout.jsp" />

		<c:import url="/resources/jspImport/footer.jsp"></c:import>
</body>

</html>