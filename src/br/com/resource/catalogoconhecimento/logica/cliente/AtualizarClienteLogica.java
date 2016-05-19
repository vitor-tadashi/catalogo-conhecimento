package br.com.resource.catalogoconhecimento.logica.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AtualizarClienteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ClienteBean cliente = new ClienteBean();

		cliente.setId(Integer.parseInt(request.getParameter("id")));
		cliente.setNome(request.getParameter("nome"));
		cliente.setLogradouro(request.getParameter("logradouro"));
		cliente.setCep(request.getParameter("cep"));
		cliente.setNumero(request.getParameter("numero"));
		cliente.setCnpj(request.getParameter("cnpj"));
		cliente.setEmail(request.getParameter("email"));

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		clienteBusiness.atualizar(cliente);

		return "mvc?logica=cliente.ListarClienteLogica";
	}

}