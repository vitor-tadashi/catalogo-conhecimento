<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Alterar Neg�cio</title>
		<c:import url="/resources/jspImport/head.jsp"></c:import>
	</head>
	<body class="overflow-hidden">
		<c:import url="/resources/jspImport/header.jsp"></c:import>
		<div id="main-container" style="width: auto">
			<div id="breadcrumb">
				<ul class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="index.jsp">
							Principal</a></li>
					<li>Neg�cios</li>
					<li class="active">Alterar Neg�cio</li>
				</ul>
			</div>
			<!--breadcrumb-->
			<div class="padding-md">
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="tab-content">
								<div class="tab-pane fade in active" id="research">
									<div class="panel panel-default">
										<form class="no-margin" id="formAlt"  method="POST" action="mvc">
											<div class="panel-heading">
												<h3>Alterar Negocio</h3>
											</div>
											<div class="panel-body">
												<div class="row">
												<!-- Message Erro-->
													<c:import url="/resources/jspImport/msgErro.jsp"></c:import>
													<input type="hidden" class="form-control input-sm" name="id" value="${negocioBean.id}" readonly>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">�rea de Atua��o
																<input type="text" class="form-control" maxlength="100" name="areaAtuacao" value="${negocioBean.areaAtuacao}">
																<input type="hidden" name="id" value="${negocioBean.id}" readonly>
															</label>
														</div>
													</div><!-- /.col -->
												</div><!-- /.row -->
												<input type="hidden" name="logica" value="negocio.AlterarNegocioLogica">		
												<input type="hidden" name="logicaAtual" value="negocio.FormularioAlterarNegocioLogica&id=${negocioBean.id}">
										 	</div> 
											<div class="panel-footer text-left">
												<button class="btn btn-success" id="submitAlterar" type="submit">Alterar</button>
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
		<c:import url="/resources/jspImport/logout.jsp"></c:import>
		<c:import url="/resources/jspImport/footer.jsp"></c:import>
		
		<script type="text/javascript">
$(document).ready(function() {
    $('#formAlt').formValidation({
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
        	nomeNegocio: {
                validators: {
                    stringLength: {
                        enabled: true,
                        min:4,
                        max:80,
                        message: 'M�nimo de 4 e m�ximo de 80 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigat�rio.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '^[A-Za-z�-�0-9\s\@\#\$\%\&\*]',
                        message: 'Negocio inv�lido.'
                    }
                }
            },
            areaAtuacao: {
	            validators: {
	                stringLength: {
	                    enabled: true,
	                    min:2,
	                    max:100,
	                    message: 'M�nimo de 2 e m�ximo de 100 caracteres.'
	                },
	                notEmpty: {
	                    message: '* Campo Obrigat�rio.'
	                },
	                regexp: {
	                    enabled: true,
	                    regexp: '^[A-Za-z�-�0-9\s\@\#\$\%\&\*]',
	                    message: 'Area de atua��o inv�lida.'
	               }
	            }
            }
        }
    });
});
</script>
		
	</body>
</html>