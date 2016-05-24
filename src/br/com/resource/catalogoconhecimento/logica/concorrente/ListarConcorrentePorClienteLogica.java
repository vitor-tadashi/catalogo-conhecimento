package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarConcorrentePorClienteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ConcorrenteClienteBean> listaConcorrentesClientes = new ConcorrenteBusiness()
				.obterPorCliente(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("concorrentesClientes", listaConcorrentesClientes);

		ClienteBean clienteBean = new ClienteBusiness().obterPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("cliente", clienteBean);

		return "/WEB-INF/jsp/concorrente/listarConcorrentePorCliente.jsp";
	}
}
