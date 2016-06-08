package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarClienteNoConcorrenteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idConcorrente = Integer.parseInt(request.getParameter("idConcorrente"));
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		ConcorrenteBean concorrenteBean = concorrenteBusiness.obterPorId(idConcorrente);

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ClienteBean clienteBean;
		Integer countCliente = Integer.parseInt(request.getParameter("countCliente"));
		for (int i = 0; i <= countCliente; i++) {
			String nomeCliente = request.getParameter("txtNome" + i);
			if (nomeCliente != null) {
				clienteBean = clienteBusiness.obterPorNome(nomeCliente);
				ConcorrenteClienteBean concorrenteClienteBean = new ConcorrenteClienteBean();
				concorrenteClienteBean.setCliente(clienteBean);
				concorrenteClienteBean.setConcorrente(concorrenteBean);
				concorrenteClienteBean.setValorHora(Integer.parseInt(request.getParameter("valorHora" + i)));

				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteClienteBean);
			}
		}
		
		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteBusiness.listarPorConcorrente(idConcorrente);
		List<ClienteBean> listaCliente = clienteBusiness.listar();

		request.setAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		request.setAttribute("concorrenteBean", concorrenteBean);
		request.setAttribute("listaConcorrente", listaCliente);
		
		return "/WEB-INF/jsp/concorrente/listarClientePorConcorrente.jsp";
	}

}
