<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<title>Alterar funcionário</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
</head>
<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp"/>
	
	
	
		<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>

				<li>Tecnologias</li>
				<li class="active">Alterar Funcionário</li>
			</ul>
		</div>
		<!--breadcrumb-->
	<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAlt"  method="POST" action="alterarFuncionario">
										<div class="panel-heading">
											<h3>Alterar Funcionário</h3>
										</div>
								<div class="panel-body">
										<div class="row">

											<!-- Message Erro-->
											<c:import url="/resources/jspImport/msgErro.jsp" />
											<input type="hidden" name="ativo" value="S">
											
											<input type="hidden" name="id" value="${funcionario.id}">

											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Nome: <input
														type="text" class="form-control" maxlength="80"
														name="nome" value="${funcionario.nome}">
													</label>
												</div>
											</div>
											<!-- /.col -->

											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Telefone: <input
														type="text" class="form-control" maxlength="11"
														placeholder="(99)9999-9999" name="telefone" value="${funcionario.telefone}">
													</label>
												</div>
											</div>
											<!-- /.col -->

											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Cargo: <select
														class="form-control" name="cargo.id">
															<c:forEach items="${cargos}" var="cargos">
																<option value="${cargos.id}">${cargos.nome}</option>
															</c:forEach>
													</select>
													</label>
												</div>
											</div>
											<!-- /.col -->


											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Email: <input
														type="text" class="form-control" name="email" value="${funcionario.email}">
													</label>
												</div>
											</div>
											<!-- /.col -->

											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Nome de usuario: <input
														type="text" class="form-control" maxlength="50"
														name="nomeUser" value="${funcionario.nomeUser}" readonly>
													</label>
												</div>
											</div>
											<!-- /.col -->

										</div>
										<div class="row">

											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">CPF: <input
														type="text" class="form-control" maxlength="11"
														placeholder="999.999.999-99" name="cpf" value="${funcionario.cpf}" readonly>
													</label>
												</div>
											</div>
											<!-- /.col -->

											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">RG: <input type="text"
														class="form-control" maxlength="9"
														placeholder="99.999.999-9" name="rg" value="${funcionario.rg}" readonly>
													</label>
												</div>
											</div>
											<!-- /.col -->

											<div class="col-sm-2">
												<div class="form-group">
														<fmt:formatDate pattern="dd/MM/yyyy" type="date" value="${funcionario.dataNascimento}" var="dataNascimentoFormatted"/>
														<label class="control-label">Data de nascimento: <input
															type="text" id="data" class="form-control"
											 				name="dataNascimento" value="${dataNascimentoFormatted}" readonly>
														</label>
												</div>
											</div>
											<!-- /.col -->
											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Tecnologia: </label>
													<div class="checkbox">
															<c:forEach items="${listaTecnologia}" var="tecnologia"
																varStatus="count">
																<label class="control-label"> <input
																	type="checkbox"
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
											<div class="col-sm-2">
												<div class="form-group">
													<label class="control-label">Negócios: </label>
													<div class="checkbox">
														<c:forEach items="${listaNegocio}" var="negocio"
																varStatus="count">
															<label class="control-label"> <input
																type="checkbox"
																name="listaNegocio[${count.index}].id"
																value="${negocio.id}" /> <span
																class="custom-checkbox"></span> ${negocio.areaAtuacao}
															</label>
														</c:forEach>
													</div>
												</div>
											</div>
											<!-- /.col -->

										</div>
										<!-- /.row -->
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
	</div><!-- /wrapper -->
	
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
			email : {
				validators : {
					notEmpty : {
						message : '* Campo Obrigatório.'
					},
					regexp : {
						enabled : true,
						regexp : '^[a-z0-9](\.?[a-z0-9_-]){0,}@[a-z0-9-]+\.([a-z]{1,6}\.)?[a-z]{2,6}$',
						message : 'E-mail inválido, requerido um @ e um domínio completo.'
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