package br.com.resource.catalogoconhecimento.logica.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarClienteLogica implements Logica {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClienteBean clienteBean = new ClienteBean();
		clienteBean.setNome(request.getParameter("nome").trim());
		clienteBean.setLogradouro(request.getParameter("logradouro").trim());
		clienteBean.setCep(request.getParameter("cep").trim());
		clienteBean.setNumero(request.getParameter("numero").trim());
		clienteBean.setCnpj(request.getParameter("cnpj").trim());
		clienteBean.setEmail(request.getParameter("email").trim());

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		clienteBusiness.adicionar(clienteBean);

		Integer countConcorrente = Integer.parseInt(request.getParameter("countConcorrente"));
		ConcorrenteBean concorrenteBean;
		ConcorrenteClienteBean concorrenteClienteBean;
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		for (int i = 0; i <= countConcorrente; i++) {
			String nomeConcorrente = request.getParameter("txtNome" + i);
			if (nomeConcorrente != null) {
				concorrenteBean = concorrenteBusiness.obterPorNome(nomeConcorrente);
				concorrenteClienteBean = new ConcorrenteClienteBean();
				concorrenteClienteBean.setCliente(clienteBean);
				concorrenteClienteBean.setConcorrente(concorrenteBean);
				concorrenteClienteBean.setValorHora(Integer.parseInt(request.getParameter("valorHora" + i)));

				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteClienteBean);
			}
		}

		return "mvc?logica=cliente.ListarClienteLogica";
	}

}
