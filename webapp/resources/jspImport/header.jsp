<!-- Overlay Div -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="overlay" class="transparent"></div>

<div id="wrapper" class="preload">
	<div id="top-nav" class="skin-1 fixed">
		<div class="brand">
			<a href="<c:url value='/'/>"><img
				src="<c:url value='/resources/img/logowhite-resource.png'/>" class="pull-center"
				style="height: 45px; width: 135px; padding-left: 10px; padding-right: 10px;"></a>
		</div>
		<!-- /brand -->

		<!-- Button menu layout mobile -->
		<button type="button" class="navbar-toggle pull-left"
			id="sidebarToggle">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>

		<!-- Button menu layout full -->
		<button type="button" class="navbar-toggle pull-left hide-menu"
			id="menuToggle">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>

		<ul class="nav-notification clearfix">
			<li class="profile dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <strong>Usuário</strong> <span><i
						class="fa fa-chevron-down"></i></span>
			</a>
				<ul class="dropdown-menu">
					<li><a class="clearfix" href="#">
							<div class="detail">
								<strong>Usuário</strong>
								<p class="grey">usuário@resource.com</p>
							</div>
					</a></li>
					<li><a tabindex="-1" class="main-link logoutConfirm_open"
						href="#logoutConfirm"><i class="fa fa-lock fa-lg"></i> Sair</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- /top-nav-->

	<aside class="fixed skin-1">
		<div class="sidebar-inner ">
			<div class="main-menu">
				<ul>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-code fa-lg"></i>
						</span> <span class="text"> Tecnologias </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='listarTecnologia'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Tecnologias
								</span>
							</a></li>
							<li><a
								href="<c:url value='formularioAdicionarTecnologia'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Tecnologia
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-cogs fa-lg"></i>
						</span> <span class="text"> Negócios </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='listarNegocio'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Negócios
								</span>
							</a></li>
							<li><a href="<c:url value='formularioAdicionarNegocio'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Negócio
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-sitemap fa-lg"></i>
						</span> <span class="text"> Projetos </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li> <a href="<c:url value='listarProjeto'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Projetos
								</span>
							</a></li>
							<li><a href="<c:url value='formularioAdicionarProjeto'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Projetos
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-group fa-lg"></i>
						</span> <span class="text"> Equipes </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='/equipe/listarEquipe'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Equipes
								</span>
							</a></li>
							<li><a
								href="<c:url value='/equipe/formularioAdicionarEquipe'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Equipe
								</span>
							</a></li>
							<li><a href="<c:url value='listarFuncionarios'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Funcionários
								</span>
							</a></li>
							<li><a href="<c:url value='formularioAdicionarFuncionario'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Funcionário
								</span>
							</a></li>
							<li><a href="<c:url value='/cargo/listarCargo'/>"> <span
									class="submenu-label"> <span class="menu-icon"> <i
											class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Cargos
								</span>
							</a></li>
							<li><a href="<c:url value='/cargo/formularioAdicionarCargo'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Cargo
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-flag fa-lg"></i>
						</span> <span class="text"> Clientes </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li><a href="<c:url value='listarCliente'/>"> 
							<span class="submenu-label"> <span class="menu-icon"> <i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Clientes
								</span>
							</a></li>
							<li><a href="<c:url value='formularioAdicionarCliente'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Cliente
								</span>
							</a></li>
						</ul></li>
					<li class="openable open"><a href="#"> <span
							class="menu-icon"> <i class="fa fa-globe fa-lg"></i>
						</span> <span class="text"> Concorrentes </span> <span class="menu-hover"></span>
					</a>
						<ul class="submenu">
							<li> <a href="<c:url value='listarConcorrente'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Lista de Concorrentes
								</span>
							</a></li>
							<li><a href="<c:url value='formularioAdicionarConcorrente'/>">
									<span class="submenu-label"> <span class="menu-icon">
											<i class="fa fa-angle-right fa-lg"></i>
									</span> Cadastrar Concorrente
								</span>
							</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /main-menu -->
		</div>
		<!-- /sidebar-inner scrollable-sidebar -->
	</aside>