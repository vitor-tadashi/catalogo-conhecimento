<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<title>Cadastrar Funcionário</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
  <script>
  	$(function() {
    	$("#dataNascimento").datepicker({ dateFormat: 'dd/mm/yy' });
  	});
  </script>
</head>
<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp"/>
	
	
	
		<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i><a href="index.jsp">
						Principal</a></li>
				<li>Funcionário</li>
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
									<form class="no-margin" id="formAdd"  method="POST" action="adicionarFuncionario">
										<div class="panel-heading">
											<h3>Adicionar Funcionário</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Message Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Nome:
															<input type="text" class="form-control" maxlength="80" name="nome">
														</label>
													</div>
												</div><!-- /.col -->
								
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Telefone:
															<input type="text" class="form-control" maxlength="11" placeholder="(99)9999-9999"  name="telefone">
														</label>
													</div>
												</div><!-- /.col -->
											
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Cargo:
																<select class="form-control"  name="cargo">
																	<c:forEach items="${cargos}" var="cargos">
																		<option value="${cargos.nome}">${cargos.nome}</option>
																	</c:forEach>
																</select>
														</label>
													</div>
												</div><!-- /.col -->

												
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Email:
															<input type="text" class="form-control" name="email">
														</label>
													</div>
												</div><!-- /.col -->
												
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Nome de usuario:
															<input type="text" class="form-control"  maxlength="50" name="nomeUser">
														</label>
													</div>
												</div><!-- /.col -->
												
											</div>
											<div class="row">	
											
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">CPF:
															<input type="text" class="form-control" maxlength="11" placeholder="999.999.999-99" name="cpf">
														</label>
													</div>
												</div><!-- /.col -->
												
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">RG:
															<input type="text" class="form-control" maxlength="9" placeholder="99.999.999-9" name="rg">
														</label>
													</div>
												</div><!-- /.col -->
												
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Data de nascimento:
															<input type="text"  id="dataNascimento" class="form-control"  name="dataNascimento">
														</label>
													</div>
												</div><!-- /.col -->
											 <div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Tecnologia:
														</label>
														<div class="checkbox">
															<c:forEach items="${tecnologias}" var="tecnologias">
																<label class="control-label">		
																	<input type="checkbox" name="tecnologiasArray[]" value="${tecnologias.nome}"data-fv-choice="true" data-fv-choice-min="1"  
																	data-fv-choice-message="Escolha no mínimo 1 Tecnologia"/>
																	<span class="custom-checkbox"></span>
																	${tecnologias.nome}
																</label>
																<br>
															</c:forEach>
														</div>	
													</div>
												</div><!-- /.col --> 
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Negócios:
														</label>
														<div class="checkbox">
															<c:forEach items="${negocios}" var="negocios">
																<label class="control-label">		
																	<input type="checkbox" name="negociosArray[]" value="${negocios.areaAtuacao}" data-fv-choice="true" data-fv-choice-min="1"  
																	data-fv-choice-message="Escolha no mínimo 1 Área de Negócio"/>
																	<span class="custom-checkbox"></span>
																	${negocios.areaAtuacao}
																</label>
																<br>
															</c:forEach>
														</div>	
													</div>
												</div><!-- /.col --> 
												
											</div><!-- /.row -->
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
	</div><!-- /wrapper -->
	
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
            telefone: {
	            validators: {
	                stringLength: {
	                    enabled: true,
	                    min:10,
	                    max:11,
	                    message: 'Digitar número de telefone com DDD.'
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
            cargo: {
	            validators: {
	                 
	                notEmpty: {
	                    message: '* Campo Obrigatório.'
	                }
	            }
	        },
	        'tecnologiasArray': {
	            validators: {
	            	choice: {
                        min: 1,
                        message: 'Escolha no mínimo %s Tecnologia.'
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
                        regexp: '^[a-z0-9](\.?[a-z0-9_-]){0,}@[a-z0-9-]+\.([a-z]{1,6}\.)?[a-z]{2,6}$',
                        message: 'E-mail inválido, requerido um @ e um domínio completo.'
                    }
                }
            },
	        nomeUser: {
	        	validators: {
	        		notEmpty: {
                		message: '* Campo Obrigatório.'
            		},
		        	stringLength: {
	                	 enabled: true,
	                     min:6,
	                     max:50,
	                     message: 'Mínimo de 6 e máximo de 50 caracteres.'
	                },
                	regexp: {
                   		enabled: true,
                    	regexp:'[a-zA-Z0-9._-]+$', //^([\\r\\e\\R\\E]{2,2})+([0-9]{6,6})$
                    	message:'Usuário inválido.'
              		}
        		}
	        },
            cpf: {
                validators: {
                    stringLength: {
                        enabled: true,
                        min:11,
                        max:11,
                        message: 'CPF contém 11 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '^[0-9]{11}$',
                        message: 'Informe somente números sem espaços e caracteres especiais.'
                    }
                }
            },
            rg: {
                validators: {
                    stringLength: {
                        enabled: true,
                        min:9,
                        max:9,
                        message: 'RG contém 11 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '^[0-9]{9}$',
                        message: 'Informe somente números sem espaços e caracteres especiais.'
                    }
                }
            },
            dataNascimento: {
            	validators: {
                    stringLength: {
                        enabled: true,
                        min:10,
                        max:10,
                        message: 'Data inválida.'
                    },
                     notEmpty: {
                        message: '* Campo Obrigatório.'
                    } 
                }
            },

	        'negociosArray': {
	            validators: {
	            	choice: {
                        min: 1,
                        message: 'Escolha no mínimo %s Área de Negócio.'
	                }
	            }
	        }            
        }
    });
});
</script>	
</body>
</html>