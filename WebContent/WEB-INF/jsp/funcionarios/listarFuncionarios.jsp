<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Listar Funcionários</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import> 
</head>
<body>

	<c:import url="/resources/jspImport/header.jsp"></c:import> 
	
	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.html">
						Principal</a></li>
				<li>Projetos</li>
				<li class="active">Listar Projetos</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive">
							<div class="panel-heading">
								<h3>Listar Funcionários</h3>
								<span class="label label-info pull-right">${fn:length(funcionarios)}
									registros</span>
							</div>
							<div class="padding-md clearfix">
								<table class="table table-striped" id="dataTable">
									<thead>
										<tr>
											<th>Nome</th>
											<th>Telefone</th>
											<th>Tecnologia(s)</th>
											<th>Usuário</th>
											<th>Email</th>
											<th>Cargo</th>
											<th>CPF</th>
											<th>RG</th>
											<th>Data de Nascimento</th>
											<th style="width: 20px;">Alterar</th>
											<th style="width: 20px;">Excluir</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="funcionario" items="${funcionarios}">
											<tr>
												<td>${funcionario.nome}</td>							
												<td>${funcionario.telefone}</td>
													
												<td>
													<select>
													<c:forEach var="tecnologia" items="${funcionario.tecnologias}">
														<option>${tecnologia.nome}</option>
													</c:forEach>
													</select>										
												</td>							
												<td>${funcionario.nomeUser}</td>							
												<td>${funcionario.email}</td>											
												<td>${funcionario.cargo.nome}</td>
												<td>${funcionario.cpf}</td>
												<td>${funcionario.rg}</td>
												<td><fmt:formatDate pattern="dd/MM/yyyy" value="${funcionario.dataNascimento}" /></td>
													
												<td style="text-align: center;"><a
													href="mvc?logica=funcionario.FormularioAlterarLogica&nomeFuncionario=${funcionario.nome}">
													<i class="fa fa-edit fa-lg"></a></td>
												<td style="text-align: center;"><a
													href="mvc?logica=funcionario.RemoverFuncionarioLogica&idFuncionario=${funcionario.id}">
													<i	class="fa fa-times fa-lg"></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="panel-footer text-left">
									<a href="mvc?logica=funcionario.FormularioInserirFuncionarioLogica">
										<button class="btn btn-success" type="submit">Cadastrar
										Novo Funcionário</button>
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
	<!-- /main-container -->
	</div> 
	
	<c:import url="/resources/jspImport/logout.jsp"></c:import>
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>
	

</body>
</html>