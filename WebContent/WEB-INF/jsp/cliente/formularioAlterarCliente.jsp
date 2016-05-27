<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Alterar Cliente</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>

<body class="overflow-hidden">
				
	<c:import url="/resources/jspImport/header.jsp"/>

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.html">
						Principal</a></li>
				<li>Clientes</li>
				<li class="active">Alterar Cliente</li>
			</ul>
		</div>
		<!--breadcrumb-->
			<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAlteraCliente"  method="POST" action="mvc?logica=cliente.AlterarClienteLogica">
										<div class="panel-heading">
											<h3>Alterar Cliente</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Message Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-md-2">
													<div class="form-group">
														<label class="control-label">Nome
															<input type="text" class="form-control input-md"  name="nome" value="${cliente.nome}">
														</label>
														<label class="control-label">CNPJ
															<input type="text" class="form-control input-sm"  name="cnpj" value="${cliente.cnpj}">
														</label>
														<label class="control-label">E-mail
															<input type="text" class="form-control input-sm"  name="email" value="${cliente.email}">
														</label>
														<label class="control-label">Logradouro
															<input type="text" class="form-control input-sm"  name="logradouro" value="${cliente.logradouro}">
														</label>
														<label class="control-label">Número
															<input type="text" class="form-control input-sm"  name="numero" value="${cliente.numero}">
														</label>
														<label class="control-label">CEP
															<input type="text" class="form-control input-sm"  name="cep" value="${cliente.cep}">
														</label>
													</div>
												</div><!-- /.col -->
											</div><!-- /.row -->
											<input type="hidden" name="logicaAtual" value="cliente.FormularioAlterarClienteLogica&id=${cliente.id}"/>
											<input type="hidden" name="logica" value="cliente.AlterarClienteLogica">
										</div>
										<div class="panel-footer text-left">
											<button class="btn btn-success" type="submit">Alterar</button>
										</div>
									</form>
								</div><!-- /panel -->
							</div>
						</div><!-- /tab-content -->
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.padding-md -->
		</div><!-- /main-container -->
	</div><!-- /wrapper -->

	<!-- Import Logout Action -->
	<c:import url="/resources/jspImport/logout.jsp" />
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	
</body>

</html>