<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Adicionar Cliente</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
	
	<script type="text/javascript">
		function add() {
			var concorrente = $("#concorrente").val();
			var valorConcorrente = $("#valorConcorrente").val();
			var count = $('#tbConcorrente tbody tr').length;

			addConcorrente(concorrente, valorConcorrente, count);
		}
		
		function addConcorrente(nome, valor, count) {
		    var row = $("<tr />")

		    $("#tbConcorrente tbody").append(row);
            row.append("<td>" + nome + "</td>");
            row.append("<td>" + valor + "</td>");
            row.append("<td><button class='delete' type='button'>-</button></td>");
            row.append("<input type='hidden' name='txtNome" + count + "' id='txtNome" + count + "' />");
		}
		
		$(document).ready(
				function() {
					$("#tbConcorrente").on('click', '.delete', 
						function() {
							$(this).closest('tr').remove();
						}
					);
				}
			);
	</script>
	<script>

	
	</script>
</head>

<body class="overflow-hidden">
				
	<c:import url="/resources/jspImport/header.jsp"/>

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.html">
						Principal</a></li>
				<li>Clientes</li>
				<li class="active">Adicionar Cliente</li>
			</ul>
		</div>
		<!--breadcrumb-->
			<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAdicionaCliente"  method="POST" action="mvc">
										<div class="panel-heading">
											<h3>Adicionar Cliente</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Messagem Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-md-4">
													<div class="form-group">
														<label class="control-label">Nome
															<input type="text" class="form-control input-sm"  name="nome">
														</label>
													</div>
												</div>	
												<div class="col-md-4">
												 	<div class="form-group">
														<label class="control-label">CNPJ
															<input type="text" class="form-control input-sm"  name="cnpj">
														</label>
													</div>
												</div>	
												<div class="col-md-4">
													<div class="form-group">	
														<label class="control-label">E-mail
															<input type="text" class="form-control input-sm"  name="email">
														</label>
													</div>
												</div>	
												<div class="col-md-4">
													<div class="form-group">	
														<label class="control-label">Logradouro
															<input type="text" class="form-control input-sm"  name="logradouro">
														</label>
													</div>
												</div>	
												<div class="col-md-4">
													<div class="form-group">	
														<label class="control-label">N�mero
															<input type="text" class="form-control input-sm"  name="numero">
														</label>
													</div>
												</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label">CEP <input type="text"
														class="form-control input-sm" name="cep">
													</label>
												</div>
											</div>
											<!-- /.col -->
											<div class="col-md-4">
												<select id="concorrente">
													<option value="volvo">Volvo</option>
													<option value="saab">Saab</option>
													<option value="mercedes">Mercedes</option>
													<option value="audi">Audi</option>
												</select> <input id="valorConcorrente" type="text">
												<button type="button" onclick="add()">+</button>
												<br>
												<br>
												<table class="table table-striped" id="tbConcorrente">
													<thead>
														<tr>
															<th>Concorrente</th>
															<th>Valor</th>
															<th>Remover</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
											<!-- /.col -->
											</div><!-- /.row -->
											<input type="hidden" name="logicaAtual" value="cliente.FormularioAdicionarClienteLogica">
											<input type="hidden" name="logica" value="cliente.AdicionarClienteLogica">
										</div>
										<div class="panel-footer text-left">
											<button class="btn btn-success" type="submit">Adicionar</button>
										</div>
									</form>
								</div><!-- /panel -->
							</div>
						</div><!-- /tab-content -->
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.padding-md -->
		</div><!-- /main-container -->

	<!-- Import Logout Action -->
	<c:import url="/resources/jspImport/logout.jsp" />
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	
</body>

</html>