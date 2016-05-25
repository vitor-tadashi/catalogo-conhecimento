<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Alterar Negócio</title>
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
				<li class="active">Alterar Negócio</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAlterarNegocio" data-validate="parsley" novalidate method="POST" action="mvc">
										<div class="panel-heading">
											<h3>Alterar Negocio</h3>
										</div>
										<div class="panel-body">
											<div class="row">
											
											<!-- Message Erro-->
											<c:import url="/resources/jspImport/msgErro.jsp"/>
											
															<input type="hidden" class="form-control input-sm" name="id" value="${negocio.id}" readonly>
													
												
												<div class="col-md-4">
													<div class="form-group">
														<label class="control-label">Área de Atuação
															<input type="text" class="form-control input-sm" name="areaAtuacao" value="${negocio.areaAtuacao}">
															<input type="hidden" class="form-control input-sm" name="id" value="${negocio.id}">
														</label>
													</div>
												</div><!-- /.col -->
											</div><!-- /.row -->
											<input type="hidden" name="logica" value="negocio.AtualizarNegocioLogica">		
											<input type="hidden" name="logicaAtual" value="tecnologia.FormularioAtualizarNegocioLogica&id=${tecnologia.id}">
									 	</div> 
										<div class="panel-footer text-left">
											<button class="btn btn-success" id="submitAlterar" type="submit">Aterar</button>
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