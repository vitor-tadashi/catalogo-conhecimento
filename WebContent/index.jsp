<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head> 
    <meta charset="utf-8">
    <title>Catálogo de Conhecimentos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

<c:import url="/resources/jspImport/head.jsp"></c:import> 
	
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			$("#filtro").tagit();
		})
		
		$(document).on("click", "#btnTeste", function() { 
			$.post("ajax?logica=busca.BuscarPorTecnologiaAjaxLogica", { idProjeto: $(this).attr('id-projeto') }, function(responseJson) {
				$("#dataTableTecnologias tbody tr").detach();	
				$.each(responseJson, function(index, item) { // Iterate over the JSON array.
						drawRowTecnologias(item);
			        });
	            });
	        });		
		
		function drawRowTecnologias(rowData) {
		    var row = $("<tr />")
		    
		    $("#dataTableTecnologias tbody").append(row);
		    row.append($("<td>" + rowData.id + "</td>"));
		    row.append($("<td>" + rowData.nome + "</td>"));
		}
	</script>

	</head>


	
<body class="overflow-hidden">

<c:import url="/resources/jspImport/header.jsp"></c:import> 

		<!-- Conteúdo-->
		<div id="main-container" style="width:auto">
			<div id="breadcrumb">
				<ul class="breadcrumb">
					 <li><i class="fa fa-home"></i><a href="index.jsp"> Principal</a></li> 
				</ul>
			</div>
			<!--breadcrumb-->
			<div class="padding-sm">
				<div class="col-md-12 col-sm-12">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="research">
							<div class="panel panel-default table-responsive">
								<div class="padding-md clearfix" style="text-align:center">
								<img src="resources/img/logo-resource.png" class="img-circle" alt="Cinque Terre" width="700" height="290">								
	 								<!--  <img src="resources/img/logo-resource.png"/> -->
			
								</div>
							    <div class="row">    
							        <div class="col-xs-8 col-xs-offset-2"><!-- col-md-6 -->
									    <div class="input-group">
									    	<c:import url="/resources/jspImport/msgErro.jsp"/>
							                
							                 <div> 
							                  <form method="post" action ="mvc">
							                    	<select name = "logica">

							                      <option value="busca.ListarPorTecnologiaBuscaLogica">Tecnologia</option>
							                      <option value="busca.ListarPorProjetoBuscaLogica">Projeto</option>
							                      <option value="busca.ListarPorFuncionarioBuscaLogica">Funcionário</option>
							                      <option value="busca.ListarPorClienteBuscaLogica">Cliente</option>
							                     	
							                      
							                    </select>
							               		
							                     
							                <input type="hidden" id="filtro"class="form-control" name="filtro" />
							                <span class="input-group-btn">
												 <!-- <button class="form-control" type="button"><span class="fa fa-search"></span></button> -->
						                         <input type="submit" class="form-control"><span class="fa fa-search"></span>
							                </span>
							              </form>      
							            </div>
							        </div>
								</div> 
							</div>
						</div>
					</div>
				</div>
			</div> 
	</div><!-- /main-container -->
	</div><!-- /wrapper -->

	<a href="" id="scroll-to-top" class="hidden-print"><i class="fa fa-chevron-up"></i></a>

	<!-- Logout confirmation -->
	<div class="custom-popup width-100" id="logoutConfirm">
		<div class="padding-md">
			<h4 class="m-top-none"> Do you want to logout?</h4>
		</div>

		<div class="text-center">
			<a class="btn btn-success m-right-sm" href="login.html">Logout</a>
			<a class="btn btn-danger logoutConfirm_close">Cancel</a>
		</div>
	</div>
	


	<c:import url="/resources/jspImport/logout.jsp"></c:import>
	
	<c:import url="/resources/jspImport/footer.jsp"></c:import>

</body>
</html>