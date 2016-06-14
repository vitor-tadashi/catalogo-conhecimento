<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Alterar Cliente</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>

<body class="overflow-hidden">
				
	<c:import url="/resources/jspImport/header.jsp"/>

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.jsp">
						Principal</a></li>
				<li>Clientes</li>
				<li class="active">Alterar Cliente</li>
			</ul>
		</div>
		<!--breadcrumb-->
			<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAlt"  method="POST" action="alterarCliente">
										<div class="panel-heading">
											<h3>Alterar Cliente</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Messagem Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-sm-2">
													<div class="form-group">
														<input type="hidden" class="form-control input-sm" name="id" value="${cliente.id}" readonly>
														<label class="control-label">Nome
															<input type="text" class="form-control"  name="nome" value="${cliente.nome}">
														</label>
													</div>	
												</div>
												<div class="col-sm-2">
													<div class="form-group">		
														<label class="control-label">CNPJ
															<input type="text" class="form-control"  name="cnpj" value="${cliente.cnpj}">
														</label>
													</div>	
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">E-mail
															<input type="text" class="form-control"  name="email" value="${cliente.email}">
														</label>
													</div>	
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Logradouro
															<input type="text" class="form-control"  name="logradouro" value="${cliente.logradouro}">
														</label>
													</div>	
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Número
															<input type="text" class="form-control"  name="numero" value="${cliente.numero}">
														</label>
													</div>	
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">CEP
															<input type="text" class="form-control"  name="cep" value="${cliente.cep}">
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
                        min:4,
                        max:80,
                        message: 'Mínimo de 4 e máximo de 80 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '[A-Za-zÁ-ú\s]+$',
                        message:'Informe apenas letras.'
                    }
                }
            },
            cnpj: {
	            validators: {
	                stringLength: {
	                    enabled: true,
	                    min:14,
	                    max:14,
	                    message: 'CNPJ inválido.'
	                },
	                notEmpty: {
	                    message: '* Campo Obrigatório.'
	                },
	                regexp: {
	                    enabled: true,
	                    regexp: '^[0-9]+$',
	                    message: 'Informe somente números sem espaços e caracteres especiais.'
	                }
	            }
	        },
	        email: {
                validators: {
                    notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}$',
                        message: 'E-mail inválido, requerido um @ e um domínico completo'
                    }
                }
            },
            logradouro: {
            	validators: {
                    stringLength: {
                        enabled: true,
                        min:4,
                        max:100,
                        message: 'Logadouro inválido.'
                    },
                     notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '[A-Za-zÁ-ú\s]+$',
                        message:'Informe apenas letras.'
                    }
                }
            }, 
            numero: {
	            validators: {
	                stringLength: {
	                    enabled: true,
	                    min:1,
	                    max:10,
	                    message: 'Número inválido.'
	                },
	                notEmpty: {
	                    message: '* Campo Obrigatório.'
	                },
	                regexp: {
	                    enabled: true,
	                    regexp: '^[0-9]+$',
	                    message: 'Informe apenas números.'
	                }
	            }
	        },
            cep: {
                validators: {
                    stringLength: {
                        enabled: true,
                        min:8,
                        max:8,
                        message: 'CEP inválido.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '^[0-9]{8}$',
                        message: 'Informe somente números sem espaços e caracteres especiais'
                    }
                }
            }
        }
    });
});
</script>
	
</body>

</html>