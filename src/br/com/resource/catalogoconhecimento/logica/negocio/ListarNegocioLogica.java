package br.com.resource.catalogoconhecimento.logica.negocio;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarNegocioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			NegocioBusiness negocioBusiness = new NegocioBusiness();
			List<NegocioBean> listaNegocio = negocioBusiness.listar();
			
			request.setAttribute("listaNegocio", listaNegocio);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}

		return "/WEB-INF/jsp/negocios/listarNegocio.jsp";
	}
}
