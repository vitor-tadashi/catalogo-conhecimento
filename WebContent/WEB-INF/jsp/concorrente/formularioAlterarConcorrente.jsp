<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Alterar Concorrente</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>

<body class="overflow-hidden">
				
	<c:import url="/resources/jspImport/header.jsp"/>

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.html">
						Principal</a></li>
				<li>Concorrentes</li>
				<li class="active">Alterar Concorrente</li>
			</ul>
		</div>
		<!--breadcrumb-->
			<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAlteraConcorrente"  method="POST" action="mvc">
										<div class="panel-heading">
											<h3>Alterar Concorrente</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Messagem Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-md-2">
													<div class="form-group">
														<input type="hidden" class="form-control input-sm" name="id" value="${concorrente.id}" readonly>
														<label class="control-label">Nome
															<input type="text" class="form-control input-sm"  name="nome" value= "${concorrente.nome}">
														</label>
														<label class="control-label">Descri��o
															<input type="text" class="form-control input-lg" name="descricao" value= "${concorrente.descricao}"></textarea>
														</label>
													</div>
												</div><!-- /.col -->
											</div><!-- /.row -->
											<input type="hidden" name="logicaAtual" value="concorrente.FormularioAlterarConcorrenteLogica&id=${concorrente.id}">
											<input type="hidden" name="logica" value="concorrente.AlterarConcorrenteLogica">
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

	<!-- Import Logout Action -->
	<c:import url="/resources/jspImport/logout.jsp" />
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	
</body>

</html>