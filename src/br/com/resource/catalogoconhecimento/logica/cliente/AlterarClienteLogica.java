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

		clienteBean.setId(Integer.parseInt(request.getParameter("id").trim()));
		clienteBean.setNome(request.getParameter("nome").trim());
		clienteBean.setLogradouro(request.getParameter("logradouro").trim());
		clienteBean.setCep(request.getParameter("cep").trim());
		clienteBean.setNumero(request.getParameter("numero").trim());
		clienteBean.setCnpj(request.getParameter("cnpj").trim());
		clienteBean.setEmail(request.getParameter("email").trim());

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		clienteBusiness.alterar(clienteBean);

		return "mvc?logica=cliente.ListarClienteLogica";
	}

}