package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarConcorrentePorClienteLogica implements Logica{

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<ConcorrenteClienteBean> concorrentesClientes = new ConcorrenteBusiness().obterPorCliente(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("concorrentesClientes", concorrentesClientes);
		
		ClienteBean cliente = new ClienteBusiness().obterPorId(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("cliente", cliente);
		
		return "/WEB-INF/jsp/concorrente/listarConcorrentePorCliente.jsp";
	}
	
}
