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
		List<NegocioBean> listaNegocio = new NegocioBusiness().listar();

		request.setAttribute("listaNegocio", listaNegocio);

		return "/WEB-INF/jsp/negocios/listarNegocio.jsp";
	}
}
