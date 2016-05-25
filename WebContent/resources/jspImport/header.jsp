<!-- Overlay Div -->
	<div id="overlay" class="transparent"></div>

	<div id="wrapper" class="preload">
		<div id="top-nav" class="skin-1 fixed">
			<div class="brand" >
				<a href="index.html"><img src="resources/img/logo_resource.png" style="height:40px; padding-left:10px;padding-right:10px;" ></a>
			</div><!-- /brand -->

      <!-- Button menu layout mobile -->
			<button type="button" class="navbar-toggle pull-left" id="sidebarToggle">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>

      <!-- Button menu layout full -->
			<button type="button" class="navbar-toggle pull-left hide-menu" id="menuToggle">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>

			<ul class="nav-notification clearfix">
				<li class="profile dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						<strong>Usuário</strong>
						<span><i class="fa fa-chevron-down"></i></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a class="clearfix" href="#">
								<div class="detail">
									<strong>Usuário</strong>
									<p class="grey">usuário@resource.com</p>
								</div>
							</a>
						</li>
						<li><a tabindex="-1" class="main-link logoutConfirm_open" href="#logoutConfirm"><i class="fa fa-lock fa-lg"></i> Sair</a></li>
					</ul>
				</li>
			</ul>
		</div><!-- /top-nav-->

		<aside class="fixed skin-1">
			<div class="sidebar-inner ">
				<div class="main-menu">
					<ul>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Tecnologias
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=tecnologia.ListarTecnologiaLogica"><span class="submenu-label">Listar Tecnologias</span></a></li>
								<li><a href="mvc?logica=tecnologia.FormularioAdicionarTecnologiaLogica"><span class="submenu-label">Cadastrar Tecnologia</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Negócios
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=negocio.ListarNegocioLogica"><span class="submenu-label">Listar Negócios</span></a></li>
								<li><a href="mvc?logica=negocio.FormularioInserirNegocioLogica"><span class="submenu-label">Cadastrar Negócio</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Projetos
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=projeto.ListarProjetoLogica"><span class="submenu-label">Listar Projetos</span></a></li>
								<li><a href="mvc?logica=projeto.FormularioInserirProjetoLogica"><span class="submenu-label">Cadastrar Projetos</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Equipes
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=equipe.ListarEquipeLogica"><span class="submenu-label">Listar Equipes</span></a></li>
								<li><a href="mvc?logica=equipe.FormularioInserirEquipeLogica"><span class="submenu-label">Cadastrar Equipe</span></a></li>
								<li><a href="mvc?logica=funcionario.ListarFuncionarioLogica"><span class="submenu-label">Listar Funcionários</span></a></li>
								<li><a href="mvc?logica=funcionario.FormularioInserirFuncionarioLogica"><span class="submenu-label">Cadastrar Funcionário</span></a></li>
								<li><a href="mvc?logica=cargo.ListarCargoLogica"><span class="submenu-label">Listar Cargos</span></a></li>
								<li><a href="mvc?logica=cargo.FormularioAdicionarCargoLogica"><span class="submenu-label">Cadastrar Cargo</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Clientes
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=cliente.ListarClienteLogica"><span class="submenu-label">Listar Clientes</span></a></li>
								<li><a href="mvc?logica=cliente.FormularioAdicionarClienteLogica"><span class="submenu-label">Cadastrar Cliente</span></a></li>
							</ul>
						</li>
						<li class="openable open">
							<a href="#">
								<span class="menu-icon">
									<i class="fa fa-tag fa-lg"></i> 
								</span>
								<span class="text">
									Concorrentes
								</span>
								<span class="menu-hover"></span>
							</a>
							<ul class="submenu">
								<li><a href="mvc?logica=concorrente.ListarConcorrenteLogica"><span class="submenu-label">Listar Concorrentes</span></a></li>
								<li><a href="mvc?logica=concorrente.FormularioAdicionarConcorrenteLogica"><span class="submenu-label">Cadastrar Concorrentes</span></a></li>
							</ul>
						</li>
					</ul>
				</div><!-- /main-menu -->
			</div><!-- /sidebar-inner scrollable-sidebar -->
		</aside>
	