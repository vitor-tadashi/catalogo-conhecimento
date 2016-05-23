package br.com.resource.catalogoconhecimento.logica.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarClienteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ClienteBean clienteBean = new ClienteBean();

		clienteBean.setId(Integer.parseInt(request.getParameter("id")));
		clienteBean.setNome(request.getParameter("nome"));
		clienteBean.setLogradouro(request.getParameter("logradouro"));
		clienteBean.setCep(request.getParameter("cep"));
		clienteBean.setNumero(request.getParameter("numero"));
		clienteBean.setCnpj(request.getParameter("cnpj"));
		clienteBean.setEmail(request.getParameter("email"));

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		clienteBusiness.alterar(clienteBean);

		return "mvc?logica=cliente.ListarClienteLogica";
	}

}