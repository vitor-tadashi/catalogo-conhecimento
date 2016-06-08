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

public class RemoverConcorrenteDoClienteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		int idConcorrente = Integer.parseInt(request.getParameter("idConcorrente"));
		
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		concorrenteBusiness.removerConcorrenteCliente(idCliente, idConcorrente);
		
		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteBusiness.listarPorCliente(idCliente);
		List<ConcorrenteBean> listaConcorrente = concorrenteBusiness.listar();
		ClienteBean clienteBean = new ClienteBusiness().obterPorId(idCliente);

		request.setAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		request.setAttribute("clienteBean", clienteBean);
		request.setAttribute("listaConcorrente", listaConcorrente);
		
		return "/WEB-INF/jsp/cliente/listarConcorrentePorCliente.jsp";
	}

}
