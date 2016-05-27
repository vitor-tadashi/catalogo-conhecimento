package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarClientePorConcorrenteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();

		int id = Integer.parseInt(request.getParameter("id"));
		List<ConcorrenteClienteBean> listaConcorrentesClientes = concorrenteBusiness.obterPorCliente(id);
		request.setAttribute("concorrentesClientes",listaConcorrentesClientes);
		ConcorrenteBean concorrenteBean = listaConcorrentesClientes.get(0).getConcorrente();
		request.setAttribute("concorrente", concorrenteBean);

		return "/WEB-INF/jsp/concorrente/listarClientePorConcorrente.jsp";
	}
}
