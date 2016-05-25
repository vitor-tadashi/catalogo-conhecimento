package br.com.resource.catalogoconhecimento.logica.tecnologia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAlterarTecnologiaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("idTecnologia"));

		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		TecnologiaBean tecnologia = tecnologiaBusiness.obterPorId(id);

		request.setAttribute("tecnologia", tecnologia);

		return "/WEB-INF/jsp/tecnologias/formularioAlterarTecnologia.jsp";
	}

}
