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
	$(document).ready(function() {
		$("#filtro").tagit();
		
		$("#selectsearch").change(function() {
			  var action = $(this).val() == "listarPorTecnologia" ? "listarPorTecnologia" : "listarPorNegocio";
			  $("#search-form").attr("action", action);
			  console.log(action);
			});
	})


</script>

</head>



<body class="overflow-hidden">

	<c:import url="/resources/jspImport/header.jsp"></c:import>

	<!-- Conteúdo-->
	<div id="main-container" style="width: auto">
		<div id="breadcrumb">
			<ul class="breadcrumb">
				<li><i class="fa fa-home"></i>
				<a href="">Principal</a></li>
			</ul>
		</div>

		<!--breadcrumb-->
		<div class="padding-sm">
			<div class="col-md-12 col-sm-12">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="research">
						<div class="panel panel-default table-responsive"
							style="height:700px; text-align: center">
							<div id="carousel-example-generic" class="carousel slide"
								data-ride="carousel" style="width: 60%; margin: 0 auto;"
								data-interval="3500">
								<!-- Indicators -->
								<ol class="carousel-indicators">
									<li data-target="#carousel-example-generic" data-slide-to="0"
										class="active"></li>
									<li data-target="#carousel-example-generic" data-slide-to="1"></li>
									<li data-target="#carousel-example-generic" data-slide-to="2"></li>
									<li data-target="#carousel-example-generic" data-slide-to="3"></li>
									<li data-target="#carousel-example-generic" data-slide-to="4"></li>
									<li data-target="#carousel-example-generic" data-slide-to="5"></li>
								</ol>

								<!-- Wrapper for slides -->
								<div class="carousel-inner">
									<div class="item active">
										<img src="<c:url value='/resources/img/Carousel_empresas-main1.png'/>" alt="...">
										<div class="carousel-caption"></div>
									</div>
									<div class="item">
										<img src="<c:url value='/resources/img/Carousel_Resource_2.png'/>" alt="...">
										<div class="carousel-caption"></div>
									</div>
									<div class="item">
										<img src="<c:url value='/resources/img/Carousel_Resource_3.png'/>" alt="...">
										<div class="carousel-caption"></div>
									</div>
									<div class="item">
										<img src="<c:url value='/resources/img/Carousel_Resource_4.png'/>" alt="...">
										<div class="carousel-caption"></div>
									</div>
									<div class="item">
										<img src="<c:url value='/resources/img/Carousel_Resource_5.png'/>" alt="...">
										<div class="carousel-caption"></div>
									</div>
									<div class="item">
										<img src="<c:url value='/resources/img/Carousel_Resource_6.png'/>" alt="...">
										<div class="carousel-caption"></div>
									</div>
								</div>

								<!-- Controls -->
								<a class="left carousel-control"
									href="#carousel-example-generic" role="button"
									data-slide="prev" style="top: 50%"> <span
									class="fa fa-chevron-left"></span>
								</a> <a class="right carousel-control"
									href="#carousel-example-generic" role="button"
									data-slide="next" style="top: 50%"> <span
									class="fa fa-chevron-right"></span>
								</a>
							</div>
							<!-- Carousel -->

							<hr>

							<div class="row">
								<header> 
									<h3>Catálogo de Conhecimentos</h3>  
								</header>
								<div class="col-xs-8 col-xs-offset-2">
									<form id = "search-form"name="busca" method="post" action="listarPorTecnologia">
									<div class="input-group input-group">
										<div class="input-group-btn search-panel">
												<span class="input-group-btn"><select id="selectsearch" class="btn btn-primary btn-xs" class="selectpicker"
													name="logica" style="margin-bottom: 11px; height: 33.5px;">
													<optgroup label="Filtros">
														<option value="listarPorTecnologia">Tecnologia</option>
														<option value = "listarPorNegocio">Negócio</option>
													</optgroup>
												</select></span>
										</div>
										<input type="hidden" id="filtro" class="form-control"
											name="filtro" /> <span class="input-group-btn">
											<button type="submit" class="btn btn-primary btn-xs"
												value="Submit Button"
												style="margin-bottom: 11px; height: 33.5px;">
												<i class="fa fa-search"></i> Pesquisar</button>
										</span>
									</div>
								</form> 
								</div>
							</div>
							<c:import url="/resources/jspImport/msgErro.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /main-container -->

	<c:import url="/resources/jspImport/footer.jsp"></c:import>

	<!-- /wrapper -->

	<a href="" id="scroll-to-top" class="hidden-print"><i
		class="fa fa-chevron-up"></i></a>
	<c:import url="/resources/jspImport/logout.jsp"></c:import>

</body>
</html>