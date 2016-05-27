<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Adicionar Funcionário</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>

<body class="overflow-hidden">
				
	<c:import url="/resources/jspImport/header.jsp"/>

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.html">
						Principal</a></li>
				<li>Funcionários</li>
				<li class="active">Adicionar Funcionário</li>
			</ul>
		</div>
		<!--breadcrumb-->
			<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAdicionaFuncionario"  method="POST" action="mvc">
										<div class="panel-heading">
											<h3>Adicionar Funcionário</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Messagem Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-md-2">
													<div class="form-group">
														<label class="control-label">Nome
															<input type="text" class="form-control input-sm"  name="nome">
														</label>
														<label class="control-label">Telefone
															<input type="text" class="form-control input-sm"  name="telefone">
														</label>
														<label class="control-label">E-mail
															<input type="text" class="form-control input-sm"  name="email">
														</label>
														<label class="control-label">Nome de usuário
															<input type="text" class="form-control input-sm"  name="nomeUser">
														</label>
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-group">
														<label class="control-label">Cargo:
															<select class="form-control input-sm" name="cargo">
																<c:forEach var="cargo" items="${cargos}">
																	<option value="${cargo.id}">${cargo.nome}</option>
																</c:forEach>
															</select>
														</label>
													</div>
												</div>  
												<div class="col-md-8">
													<div class="form-group">
														<label class="control-label">Tecnologias:
														</label>
														<div class="checkbox">
															<c:forEach items="${tecnologias}" var="tecnologia">
																<label class="control-label">		
																	<input type="checkbox" name="tecnologiasArray" value="${tecnologia.id}">
																	<span class="custom-checkbox"></span>
																	${tecnologia.nome}
																</label>
															</c:forEach>
														</div>	
													</div>
												</div>
											</div><!-- /.row -->
											<input type="hidden" name="logicaAtual" value="funcionario.FormularioAdicionarFuncionarioLogica">
											<input type="hidden" name="logica" value="funcionario.AdicionarFuncionarioLogica">
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