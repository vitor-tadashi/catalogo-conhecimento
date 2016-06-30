<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Alterar Perfil</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>

<body class="overflow-hidden">
				
	<c:import url="/resources/jspImport/header.jsp"/>

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>

				<li>Perfis</li>
				<li class="active">Alterar Perfil</li>
			</ul>
		</div>
		<!--breadcrumb-->
			<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAlt" method="POST" action="alterarPerfil">
										<div class="panel-heading">
											<h3>Alterar Perfil</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Messagem Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-sm-2">
													<div class="form-group">
														<input type="hidden" class="form-control input-sm" name="id" value="${perfil.id}" readonly>
														<label class="control-label">Tipo
															<input type="text" class="form-control" name="tipo" value="${perfil.tipo}">
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
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#formAlt').formValidation({
		        err: {
		            container: 'tooltip'
		        },
		        icon: {
		            valid: 'fa fa-check',
		            invalid: 'fa fa-times',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	tipo: {
		                validators: {
		                    stringLength: {
		                        enabled: true,
		                        min:4,
		                        max:30,
		                        message: 'Mínimo de 4 e máximo de 30 caracteres.'
		                    },
		                    notEmpty: {
		                        message: '* Campo Obrigatório.'
		                    },
		                    regexp: {
		                        enabled: true,
		                        regexp: '[A-Za-zÁ-ú0-9\s]{4,30}',
		                        message:'Tipo inválido.'
		                    }
		                }
		            },
		        }
		    });
		});
	</script>
	
</body>

</html>