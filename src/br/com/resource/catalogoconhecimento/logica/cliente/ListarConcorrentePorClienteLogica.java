package br.com.resource.catalogoconhecimento.logica.cliente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarConcorrentePorClienteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ConcorrenteClienteBean> listaConcorrenteCliente = new ConcorrenteBusiness()
				.listarPorCliente(Integer.parseInt(request.getParameter("id")));
		
		ClienteBean clienteBean = new ClienteBusiness().obterPorId(Integer.parseInt(request.getParameter("id")));
		List<ConcorrenteBean> listaConcorrente = new ConcorrenteBusiness().listar();
		
		request.setAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		request.setAttribute("clienteBean", clienteBean);
		request.setAttribute("listaConcorrente", listaConcorrente);
		
		return "/WEB-INF/jsp/cliente/listarConcorrentePorCliente.jsp";
	}
}
