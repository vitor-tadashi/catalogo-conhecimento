<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Adicionar Tecnologia</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>
<body class="overflow-hidden">
	<c:import url="/resources/jspImport/header.jsp"></c:import>
	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.jsp">
						Principal</a></li>
				<li>Tecnologias</li>
				<li class="active">Adicionar Tecnologia</li>
			</ul>
		</div>
		<!--breadcrumb-->
		<div class="padding-md">
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="research">
							<div class="panel panel-default">
								<form class="no-margin" id="formAdd" method="POST"
								 action="<c:url value='adicionarTecnologia'>
									<c:param name='paginaAtual' value='formularioAdicionarTecnologia'/>
											</c:url>">
										<div class="panel-heading">
										<h3>Adicionar Tecnologia</h3>
									</div>
									<div class="panel-body">
										<div class="row">
											<!-- Message Erro-->
											<c:import url="/resources/jspImport/msgErro.jsp"></c:import>
											<div class="col-lg-2">
												<div class="form-group">
													<label class="control-label">Nome da Tecnologia 
														<input type="text" class="form-control" maxlength="50" name="nome" > 	
													</label>
												</div>
											</div>
											<!-- /.col -->
										</div>
										<!-- /.row -->
									
									</div>
									<div class="panel-footer text-left">
										<button class="btn btn-success" type="submit">Adicionar</button>
									</div>
								</form>
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
	<c:import url="/resources/jspImport/logout.jsp"></c:import>
	<c:import url="/resources/jspImport/footer.jsp"></c:import>

<script>
$(document).ready(function() {
    $('#formAdd').formValidation({
        err: {
            container: 'tooltip'
        },
//        trigger: 'blur',
        icon: {
            valid: 'fa fa-check',
            invalid: 'fa fa-times',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nome: {
                validators: {
                    stringLength: {
                        enabled: true,
                        min: 1,
                        max:50,
                        message: 'Mínimo de 1 e máximo de 50 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '[A-Za-zÀ-ú0-9\s\@\#\$\%\&\*]',
                        message: 'Tecnologia inválida.'
                    }
                }
            }
        }
    });
});
</script>
</body>
</html>