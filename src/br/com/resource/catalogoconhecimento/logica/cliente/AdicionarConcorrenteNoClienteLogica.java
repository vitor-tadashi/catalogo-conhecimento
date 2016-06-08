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

public class AdicionarConcorrenteNoClienteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		ClienteBean clienteBean = new ClienteBusiness().obterPorId(idCliente);

		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();

		ConcorrenteBean concorrenteBean;
		Integer countConcorrente = Integer.parseInt(request.getParameter("countConcorrente"));

		for (int i = 0; i <= countConcorrente; i++) {
			String nomeConcorrente = request.getParameter("txtNome" + i);
			if (nomeConcorrente != null) {
				concorrenteBean = concorrenteBusiness.obterPorNome(nomeConcorrente);
				ConcorrenteClienteBean concorrenteClienteBean = new ConcorrenteClienteBean();
				concorrenteClienteBean.setCliente(clienteBean);
				concorrenteClienteBean.setConcorrente(concorrenteBean);
				concorrenteClienteBean.setValorHora(Integer.parseInt(request.getParameter("valorHora" + i)));

				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteClienteBean);
			}
		}
		
		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteBusiness.listarPorCliente(idCliente);
		List<ConcorrenteBean> listaConcorrente = concorrenteBusiness.listar();

		request.setAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		request.setAttribute("clienteBean", clienteBean);
		request.setAttribute("listaConcorrente", listaConcorrente);
		
		return "/WEB-INF/jsp/concorrente/listarConcorrentePorCliente.jsp";
	}

}
