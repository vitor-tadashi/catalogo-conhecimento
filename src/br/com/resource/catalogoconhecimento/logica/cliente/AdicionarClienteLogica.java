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

		String nome = request.getParameter("nome");
		String logradouro = request.getParameter("logradouro");
		String cep = request.getParameter("cep");
		String numero = request.getParameter("numero");
		String cnpj = request.getParameter("cnpj");
		String email = request.getParameter("email");

		ClienteBean clienteBean = new ClienteBean();

		clienteBean.setNome(nome.trim());
		clienteBean.setLogradouro(logradouro.trim());
		clienteBean.setCep(cep.trim());
		clienteBean.setNumero(numero.trim());
		clienteBean.setCnpj(cnpj.trim());
		clienteBean.setEmail(email.trim());

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		clienteBusiness.adicionar(clienteBean);

		ConcorrenteBean concorrenteBean;
		Integer countConcorrente = Integer.parseInt(request.getParameter("countConcorrente"));
		
		for (int i = 0; i <= countConcorrente; i++) {
			String nomeConcorrente = request.getParameter("txtNome" + i);
			concorrenteBean = concorrenteBusiness.obterPorNome(nomeConcorrente);
			ConcorrenteClienteBean concorrenteClienteBean = new ConcorrenteClienteBean();
			concorrenteClienteBean.setCliente(clienteBean);
			concorrenteClienteBean.setConcorrente(concorrenteBean);
			concorrenteClienteBean.setValorHora(Integer.parseInt(request.getParameter("valorHora" + i)));
			
			concorrenteBusiness.adicionarConcorrenteCliente(concorrenteClienteBean);
         }

		return "mvc?logica=cliente.ListarClienteLogica";
	}

}
