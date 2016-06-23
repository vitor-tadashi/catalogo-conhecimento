<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Lista de Concorrentes do Cliente</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
	
	<script type="text/javascript">
	
		function add() {
			var cliente = $("#cliente option:selected").text();
			var idCliente = $("#cliente option:selected").val();
			var valorConcorrente = $("#valorConcorrente").val();
			var count = $('#tbCliente tbody tr').length;
			addCliente(cliente, valorConcorrente, count);
		}
	
		function addCliente(nome, valor, count) {
			var row = $("<tr />")
			$("#tbCliente tbody").append(row);
			row.append("<td>" + nome + "</td>");
			row.append("<td>" + valor + "</td>");
			row.append("<td><button class='delete' type='button'>-</button></td>");
			row.append("<input type='hidden' name='listaClientes[" + count + "].cliente.id' id='listaClientes[" + count + "].cliente.id' value='" + idCliente + "'  />");
			row.append("<input type='hidden' name='listaClientes[" + count + "].valorHora' id='listaClientes[" + count + "].valorHora' value='" + valor + "' /></td>");
			$("#countCliente").val(count);
		}

		$(document).ready(function() {
			$("#tbCliente").on('click', '.delete', function() {
				$(this).closest('tr').remove();
			});
		});
		
	</script>
</head>

<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp" />

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>

				<li>Concorrentes</li>
				<li class="active">Lista de Concorrentes do Cliente</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive">
							<div class="panel-heading">
								<h3>Concorrente ${concorrenteBean.nome}</h3>
								
								<!-- Message Erro-->
								<c:import url="/resources/jspImport/msgErro.jsp"/>
								
								<span class="label label-info pull-right">${fn:length(listaConcorrenteCliente)}
									registros</span>
							</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Nome do Cliente</th>
											<th>Valor/hora</th>
											<th style="width: 20px;">Excluir</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="concorrenteCliente" items="${listaConcorrenteCliente}">
											<tr>
												<td>${concorrenteCliente.cliente.nome}</td>
												<td>${concorrenteCliente.valorHora}</td>
												<td style="text-align: center;">
													<a href="<c:url value='removerClientedoConcorrente'> <c:param name='idConcorrente' value='${concorrente.id}'/> <c:param name='idCliente' value='${concorrenteCliente.cliente.id}'/> </c:url>"><i class="fa fa-times fa-lg"></i></a>
												</td> 
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<form class="no-margin" id="formAdd"  method="POST" action="mvc">
								<div class="panel-heading">
									<h3>Adicionar Cliente</h3>
								</div>
								<div class="panel-body">
									<div class="row">
										
										<!-- Messagem Erro-->
										<c:import url="/resources/jspImport/msgErro.jsp"/>
										<div class="col-md-8">
											<h4>Adicionar Clientes</h4>
											<select id="cliente">
												<c:forEach var="cliente" items="${listaCliente}">
													<option>${cliente.nome}</option>
												</c:forEach>
											</select> 
											<input id="valorConcorrente" type="text">
											<button type="button" onclick="add()">+</button>
											<br> <br>
											<table class="table table-striped" id="tbCliente">
												<thead>
													<tr>
														<th>Cliente</th>
														<th>Valor</th>
														<th>Remover</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div><!-- /.col -->
									</div><!-- /.row -->
									<input type="hidden" id="idConcorrente" name="idConcorrente" value="${concorrenteBean.id}">
								</div>
								<div class="panel-footer text-left">
									<button class="btn btn-success" type="submit">Adicionar</button>
								</div>
							</form>
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