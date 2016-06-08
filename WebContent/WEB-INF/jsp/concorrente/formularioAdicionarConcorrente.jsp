<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<title>Adicionar Concorrente</title>
	<c:import url="/resources/jspImport/head.jsp"></c:import>
	<script type="text/javascript">
		function add() {
			var cliente = $("#cliente").val();
			var valorConcorrente = $("#valorConcorrente").val();
			var count = $('#tbCliente tbody tr').length;
			addCliente(cliente, valorConcorrente, count);
		}
	
		function addCliente(nome, valor, count) {
			var row = $("<tr />")
			$("#tbCliente tbody").append(row);
			row.append("<td>" + nome + "</td>");
			row.append("<td>" + valor + "</td>");
			row.append("<td><button class='delete' type='button'>-</button></td>");
			row.append("<input type='hidden' name='txtNome" + count + "' id='txtNome" + count + "' value='" + nome + "'  />");
			row.append("<input type='hidden' name='valorHora" + count + "' id='valorHora" + count + "' value='" + valor + "' />");
			$("#countCliente").val(count);
		}

		$(document).ready(function() {
			$("#tbCliente").on('click', '.delete', function() {
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
				<li><i class="fa fa-home"></i><a href="index.jsp">
						Principal</a></li>
				<li>Concorrentes</li>
				<li class="active">Adicionar Concorrente</li>
			</ul>
		</div>
		<!--breadcrumb-->
			<div class="padding-md">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="tab-content">
							<div class="tab-pane fade in active" id="research">
								<div class="panel panel-default">
									<form class="no-margin" id="formAdd"  method="POST" action="mvc">
										<div class="panel-heading">
											<h3>Adicionar Concorrente</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												
												<!-- Messagem Erro-->
												<c:import url="/resources/jspImport/msgErro.jsp"/>
												
												<div class="col-md-8">
													<div class="form-group">
														<label class="control-label">Nome
															<input type="text" class="form-control"  name="nome">
														</label> 
													</div>	 
													<div class="form-group">
														<label class="control-label">Descrição
															<textarea class="form-control" name="descricao"></textarea>
														</label>
													</div>
												</div><!-- /.col -->
												<div class="col-md-8">
													<h4>Adicionar Clientes</h4>
													<select id="cliente">
														<c:forEach var="cliente" items="${listaCliente}">
															<option>${cliente.nome}</option>
														</c:forEach>
													</select> 
													<input id="valorConcorrente" type="text">
													<button type="button" onclick="add()">+</button>
													<br> <br>
													<table class="table table-striped" id="tbCliente">
														<thead>
															<tr>
																<th>Cliente</th>
																<th>Valor</th>
																<th>Remover</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div><!-- /.col -->
											</div><!-- /.row -->
											<input type="hidden" name="logicaAtual" value="concorrente.FormularioAdicionarConcorrenteLogica">
											<input type="hidden" name="logica" value="concorrente.AdicionarConcorrenteLogica">
											<input type="hidden" id="countCliente" name="countCliente" value="0">
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
                        min:4,
                        max:100,
                        message: 'Mínimo de 4 e máximo de 100 caracteres.'
                    },
                    notEmpty: {
                        message: '* Campo Obrigatório.'
                    },
                    regexp: {
                        enabled: true,
                        regexp: '^[A-Za-zÀ-ú0-9\s\@\#\$\%\&\*]',
                        message: 'Nome inválido.'
                    }
                }
            },
            descricao: {
	            validators: {
	                stringLength: {
	                    enabled: true,
	                    max:255,
	                    message: 'Máximo de 255 caracteres.'
	                },
	                regexp: {
	                    enabled: true,
	                    regexp: '^[A-Za-zÀ-ú0-9\s\@\#\$\%\&\*]',
	                    message: 'Area de atuação inválida.'
	               }
	            }
	        }
        }
    });
});
</script>	
	
</body>

</html>
