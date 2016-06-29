<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cadastrar Projeto</title>
<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>
<body>

	<c:import url="/resources/jspImport/header.jsp"></c:import>
	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i> <a href="<c:url value='/'/>">Principal</a></li>

				<li>Projetos</li>
				<li class="active">Cadastrar Projeto</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="research">
							<div class="panel panel-default">
								<form:form class="no-margin" id="formAdd" path = "projeto" method="POST"
									action="adicionarProjeto">
									<div class="panel-heading">
										<h3>Cadastrar Projeto</h3>
									</div>
									<div class="panel-body">
										<div class="row">

											<!-- Message Erro-->
											<c:import url="/resources/jspImport/msgErro.jsp" />
											<input type ="hidden" name="ativo" value = "S"/>
											
											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Nome do Projeto <input
														type="text" class="form-control" maxlength="80"
														name="nome">
													</label>
												</div>
											</div>
											<!-- /.col -->
											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Observação <textarea
															class="form-control" maxlength="255" rows="8"
															name="observacao"></textarea>
													</label>
												</div>
											</div>
											<!-- /.col -->
										</div>
										<div class="row">
											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Cliente <select
														class="form-control input-sm" name="cliente">
															<c:forEach items="${clientes}" var="cliente">
																<option value="${cliente.id}">${cliente.nome}</option>
															</c:forEach>
													</select>
													</label>
												</div>
											</div>
											<!-- /.col -->
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Equipe:</label>
													<div class="checkbox">
														<c:forEach items="${equipes}" var="equipe"
															varStatus="count">
															<label class="control-label">
																<input type="checkbox"
																name="listaEquipe[${count.index}].id"
																value="${equipe.id}" /> <span class="custom-checkbox"></span>
																${equipe.nome}
															</label>
															<br>
														</c:forEach>
													</div>
												</div>
											</div>
											<!-- /.col -->
										</div>
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Negócio: </label>
													<div class="checkbox">
														<c:forEach items="${negocios}" var="negocio"
															varStatus="count">
															<label class="control-label">
																<input type="checkbox"
																name="listaNegocio[${count.index}].id"
																value="${negocio.id}" /> <span class="custom-checkbox"></span>
																${negocio.areaAtuacao}
															</label>
															<br>
														</c:forEach>
													</div>
												</div>
											</div>
											<!-- /.col -->
											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Tecnologia: </label>
													<div class="checkbox">
														<c:forEach items="${tecnologias}" var="tecnologia"
															varStatus="count">
															<label class="control-label">
																<input type="checkbox"
																name="listaTecnologia[${count.index}].id"
																value="${tecnologia.id}" /> <span
																class="custom-checkbox"></span> ${tecnologia.nome}
															</label>
															<br>
														</c:forEach>
													</div>
												</div>
											</div>
											<!-- /.col -->
										</div>
									</div>
									<div class="panel-footer text-left">
										<button class="btn btn-success" type="submit">Cadastrar</button>
									</div>
								</form:form>
							</div>
							<!-- /panel -->
						</div>
					</div>
					<!-- /tab-content -->
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.padding-md -->
	</div>
	<!-- /main-container -->
	</div>
	<!-- /wrapper -->

	<!-- Import Logout Action -->
	<c:import url="/resources/jspImport/logout.jsp" />

	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#formAdd')
									.formValidation(
											{
												err : {
													container : 'tooltip'
												},
												//        trigger: 'blur',
												icon : {
													valid : 'fa fa-check',
													invalid : 'fa fa-times',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													nome : {
														validators : {
															stringLength : {
																enabled : true,
																min : 4,
																max : 80,
																message : 'Mínimo de 4 e máximo de 80 caracteres.'
															},
															notEmpty : {
																message : '* Campo Obrigatório.'
															},
															regexp : {
																enabled : true,
																regexp : '^[A-Za-zÀ-ú0-9\s\@\#\$\%\&\*]',
																message : 'Nome inválido.'
															}
														}
													},
													observacao : {
														validators : {
															stringLength : {
																enabled : true,
																min : 20,
																max : 255,
																message : 'Mínimo de 20 e máximo de 255 caracteres.'
															},
															notEmpty : {
																message : '* Campo Obrigatório.'
															},
															regexp : {
																enabled : true,
																regexp : '^[A-Za-zÀ-ú0-9\s\@\#\$\%\&\*]',
																message : 'Nome inválido.'
															}
														}
													},
													'equipeArray[]' : {
														validators : {
															choice : {
																min : 1,
																message : 'Escolha no mínimo %s Equipe.'
															}
														}
													},
													'negocioArray[]' : {
														validators : {
															choice : {
																min : 1,
																message : 'Escolha no mínimo %s Área de Negócio.'
															}
														}
													},
													'tecnologiaArray[]' : {
														validators : {
															choice : {
																min : 1,
																message : 'Escolha no mínimo %s Tecnologia.'
															}
														}
													}
												}
											});
						});
	</script>

</body>
</html>