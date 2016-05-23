package br.com.resource.catalogoconhecimento.logica.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class RemoverClienteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ClienteBean clienteBean = new ClienteBean();

		clienteBean.setId(Integer.parseInt(request.getParameter("id")));

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		clienteBusiness.remover(clienteBean);

		return "mvc?logica=cliente.ListarClienteLogica";
	}
}
