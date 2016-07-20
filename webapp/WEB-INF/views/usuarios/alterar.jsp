<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<title>Alterar Usuário</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>	
</head>
<body class="overflow-hidden">
				
	<c:import url="/resources/jspImport/header.jsp"/>
	
	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>
				<li>Usuários</li>
				<li class="active">Alterar Usuário</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAlt" method="POST" 
									action="<c:url value='alterar'>
											</c:url>">
										<div class="panel-heading">
											<h3>Alterar Usuário</h3>
										</div>
										<div class="panel-body">
											<div class="row">
											
												<!-- Message Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
											 
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Nome
															<input type="hidden" class="form-control input-sm" name="id" value="${usuarios.id}">
															<input type="text" class="form-control"  maxlength="80"  name="nome" value="${usuarios.nome}">
															<input type ="hidden" name="ativo" value = "S"/>
														</label>
														
														<label class="control-label">Login
															<input type="hidden" class="form-control input-sm" name="id" value="${usuarios.id}">
															<input type="text" class="form-control"  maxlength="80"  name="login" value="${usuarios.login}">
															<input type ="hidden" name="ativo" value = "S"/>
														</label>
														
															<input type="hidden" class="form-control input-sm" name="id" value="${usuarios.id}">
															<input type="hidden" class="form-control"  maxlength="80"  name="senha" value="${usuarios.senha}">
															<input type ="hidden" name="ativo" value = "S"/>

														<label class="control-label">Perfil
														    <select class="form-control input-sm" name="perfil">
														<c:forEach var="perfil" items="${perfis}">	
															<option value="${perfil.id}">${perfil.tipo}</option>
														</c:forEach>
													        </select>
														</label>
														
													</div>
												</div><!-- /.col --> 		
											</div><!-- /.row -->
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