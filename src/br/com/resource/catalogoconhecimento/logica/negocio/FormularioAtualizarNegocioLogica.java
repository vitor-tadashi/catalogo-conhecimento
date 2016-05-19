package br.com.resource.catalogoconhecimento.logica.negocio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;

public class FormularioAtualizarNegocioLogica {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int id = Integer.parseInt(request.getParameter("id"));

		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		TecnologiaBean tecnologia = tecnologiaBusiness.obterPorId(id);

		request.setAttribute("tecnologia", tecnologia);

		return "/WEB-INF/jsp/tecnologias/formularioAtualizarTecnologia.jsp";
	}
}
