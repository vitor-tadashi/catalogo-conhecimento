<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Adicionar Cliente</title>
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
				<li class="active">Adicionar Cliente</li>
			</ul>
		</div>
		<!--breadcrumb-->
			<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAdicionaCliente"  method="POST" action="mvc">
										<div class="panel-heading">
											<h3>Adicionar Cliente</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Messagem Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-md-4">
													<div class="form-group">
														<label class="control-label">Nome
															<input type="text" class="form-control input-sm"  name="nome">
														</label>
													</div>
												</div>	
												<div class="col-md-4">
												 	<div class="form-group">
														<label class="control-label">CNPJ
															<input type="text" class="form-control input-sm"  name="cnpj">
														</label>
													</div>
												</div>	
												<div class="col-md-4">
													<div class="form-group">	
														<label class="control-label">E-mail
															<input type="text" class="form-control input-sm"  name="email">
														</label>
													</div>
												</div>	
												<div class="col-md-4">
													<div class="form-group">	
														<label class="control-label">Logradouro
															<input type="text" class="form-control input-sm"  name="logradouro">
														</label>
													</div>
												</div>	
												<div class="col-md-4">
													<div class="form-group">	
														<label class="control-label">N�mero
															<input type="text" class="form-control input-sm"  name="numero">
														</label>
													</div>
												</div>	
												<div class="col-md-4">
													<div class="form-group">	
														<label class="control-label">CEP
															<input type="text" class="form-control input-sm"  name="cep">
														</label>
													</div>
												</div><!-- /.col -->
											</div><!-- /.row -->
											<input type="hidden" name="logicaAtual" value="cliente.FormularioAdicionarClienteLogica">
											<input type="hidden" name="logica" value="cliente.AdicionarClienteLogica">
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
	
	<script type="text/javascript">
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
        	nomeCliente: {
                validators: {
                    stringLength: {
                        enabled: true,
                        min:4,
                        max:100,
                        message: 'M�nimo de 4 e m�ximo de 100 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigat�rio.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '^[A-Za-z�-�0-9\s\@\#\$\%\&\*]',
                        message: 'Nome inv�lido.'
                    }
                }
            },
            cnpj: {
	            validators: {
	                stringLength: {
	                    enabled: true,
	                    min:20,
	                    max:255,
	                    message: 'M�nimo de 2 e m�ximo de 50 caracteres.'
	                },
	                notEmpty: {
	                    message: '* Campo Obrigat�rio.'
	                },
	                regexp: {
	                    enabled: true,
	                    regexp: '^[A-Za-z�-�0-9\s\@\#\$\%\&\*]',
	                    message: 'CNPJ inv�lido.'
	                }
	            }
	        }
        }
    });
});
</script>	
	
</body>

</html>