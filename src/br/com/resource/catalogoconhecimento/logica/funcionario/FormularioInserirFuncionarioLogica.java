package br.com.resource.catalogoconhecimento.logica.funcionario;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioInserirFuncionarioLogica implements Logica{


		public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			List<TecnologiaBean> tecnologiasLista = new ArrayList<>();
			TecnologiaBusiness tecnologiabusiness = new TecnologiaBusiness();
			tecnologiasLista = tecnologiabusiness.listar();
			
			List<CargoBean> cargosLista = new ArrayList<>();
			CargoBusiness cargobusiness = new CargoBusiness();
			cargosLista = cargobusiness.listar();
			
			request.setAttribute("tecnologias", tecnologiasLista);
			
			request.setAttribute("cargos", cargosLista);
			
			
			return "/WEB-INF/jsp/funcionarios/formularioCriar.jsp";
		}
	}


