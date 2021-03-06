package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarConcorrenteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConcorrenteBean concorrenteBean = new ConcorrenteBean();
		concorrenteBean.setNome(request.getParameter("nome").trim());
		concorrenteBean.setDescricao(request.getParameter("descricao").trim());
		
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		concorrenteBusiness.adicionar(concorrenteBean);
		
		Integer countCliente = Integer.parseInt(request.getParameter("countCliente"));
		ClienteBean clienteBean;
		ConcorrenteClienteBean concorrenteClienteBean;
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		for (int i = 0; i <= countCliente; i++) {
			String nomeCliente = request.getParameter("txtNome" + i);
			if (nomeCliente != null) {
				clienteBean = clienteBusiness.obterPorNome(nomeCliente);
				concorrenteClienteBean = new ConcorrenteClienteBean();
				concorrenteClienteBean.setCliente(clienteBean);
				concorrenteClienteBean.setConcorrente(concorrenteBean);
				concorrenteClienteBean.setValorHora(Integer.parseInt(request.getParameter("valorHora" + i)));

				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteClienteBean);
			}
		}
		
		return "mvc?logica=concorrente.ListarConcorrenteLogica";
	}

}
