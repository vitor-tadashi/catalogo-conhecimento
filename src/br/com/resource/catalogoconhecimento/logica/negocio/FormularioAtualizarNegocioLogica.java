package br.com.resource.catalogoconhecimento.logica.negocio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAtualizarNegocioLogica implements Logica {
	
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		int id = Integer.parseInt(request.getParameter("id"));

		NegocioBusiness negocioBusiness = new NegocioBusiness();
		NegocioBean negocio = negocioBusiness.listarPorId(id);

		request.setAttribute("negocio", negocio);

		return "/WEB-INF/jsp/negocios/formularioAtualizarNegocio.jsp";
	}
}
