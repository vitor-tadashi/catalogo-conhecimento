<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Adicionar Cliente</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>

	<script type="text/javascript">
		function add() {
			var concorrente = $("#concorrente").val();
			var valorConcorrente = $("#valorConcorrente").val();
			var count = $('#tbConcorrente tbody tr').length;
			addConcorrente(concorrente, valorConcorrente, count);
		}
	
		function addConcorrente(nome, valor, count) {
			var row = $("<tr />")
			$("#tbConcorrente tbody").append(row);
			row.append("<td>" + nome + "</td>");
			row.append("<td>" + valor + "</td>");
			row.append("<td><button class='delete' type='button'>-</button></td>");
			row.append("<input type='hidden' name='txtNome" + count + "' id='txtNome" + count + "' value='" + nome + "'  />");
			row.append("<input type='hidden' name='valorHora" + count + "' id='valorHora" + count + "' value='" + valor + "' />");
			$("#countConcorrente").val(count);
		}

		$(document).ready(function() {
			$("#tbConcorrente").on('click', '.delete', function() {
				$(this).closest('tr').remove();
			});
		});
	</script>
</head>

<body class="overflow-hidden">
				
	<c:import url="/resources/jspImport/header.jsp"/>

	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
					<a href="<c:url value='/'/>">Principal</a></li>

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
									<form class="no-margin" id="formAdd"  method="POST" action="<c:url value='adicionarCliente'> <c:param name='countConcorrente'/> <c:param name='txtNome'/> <c:param name='valorHora'/> </c:url>">
										<div class="panel-heading">
											<h3>Adicionar Cliente</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Messagem Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Nome
															<input type="text" class="form-control" name="nome" maxlength="150">
														</label>
													</div>
												</div>	
												<div class="col-sm-2">
												 	<div class="form-group">
														<label class="control-label">CNPJ
															<input type="text" class="form-control" name="cnpj" maxlength="14">
														</label>
													</div>
												</div>	
												<div class="col-sm-2">
													<div class="form-group">	
														<label class="control-label">E-mail
															<input type="text" class="form-control"  name="email">
														</label>
													</div>
												</div>	
												<div class="col-sm-2">
													<div class="form-group">	
														<label class="control-label">Logradouro
															<input type="text" class="form-control"  name="logradouro">
														</label>
													</div>
												</div>	
												<div class="col-sm-2">
													<div class="form-group">	
														<label class="control-label">Número
															<input type="text" class="form-control"  name="numero">
														</label>
													</div>
												</div>	
												<div class="col-sm-2">
													<div class="form-group">	
														<label class="control-label">CEP
															<input type="text" class="form-control"  name="cep">
														</label>
													</div>
												</div><!-- /.col -->
												<div class="col-md-4">
												<h4>Adicionar Concorrentes</h4>
													
												<div class="col-sm-6">
													<div class="form-group">
														<select id="concorrente" class="form-control">
															<c:forEach var="concorrente" items="${concorrentes}">
																<option>${concorrente.nome}</option>
															</c:forEach>
														</select> 
													</div>
												</div>			
												<div class="col-sm-4">
													<div class="form-group">
														<input id="valorConcorrente" class="form-control" placeholder="Valor/Hora"type="text">
													</div>
												</div>		
												<div class="col-md-2">
													<div class="form-group">		
														<button type="button" class="form-control" onclick="add()">+</button>
													</div>
												</div>			
												<br> <br>
												<table class="table table-striped" id="tbConcorrente">
													<thead>
														<tr>
															<th>Concorrente</th>
															<th>Valor</th>
															<th>Remover</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
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
                        min:2,
                        max:150,
                        message: 'Mínimo de 2 e máximo de 150 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '[A-Za-zÁ-ú0-9\s]+$',
                        message:'Nome inválido.'
                    }
                }
            },
            cnpj: {
	            validators: {
	                stringLength: {
	                    enabled: true,
	                    min:14,
	                    max:14,
	                    message: 'Requerido mínimo e máximo de 14 caracteres.'
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
                        regexp: '[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,9}$',
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