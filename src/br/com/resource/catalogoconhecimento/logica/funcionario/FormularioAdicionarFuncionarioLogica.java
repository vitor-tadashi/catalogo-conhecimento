package br.com.resource.catalogoconhecimento.logica.funcionario;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAdicionarFuncionarioLogica implements Logica{


		public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			List<TecnologiaBean> listaTecnologia = new ArrayList<>();
			TecnologiaBusiness tecnologiabusiness = new TecnologiaBusiness();
			listaTecnologia = tecnologiabusiness.listar();
			
			List<NegocioBean> listaNegocio = new ArrayList<>();
			NegocioBusiness negocioBusiness = new NegocioBusiness();
			listaNegocio = negocioBusiness.listar();
			
			List<CargoBean> listaCargo = new ArrayList<>();
			CargoBusiness cargobusiness = new CargoBusiness();
			listaCargo = cargobusiness.listar();
			
			request.setAttribute("tecnologias", listaTecnologia);
			
			request.setAttribute("cargos", listaCargo);
			
			request.setAttribute("negocios", listaNegocio);
			
			
			return "/WEB-INF/jsp/funcionarios/formularioAdicionar.jsp";
		}
	}


