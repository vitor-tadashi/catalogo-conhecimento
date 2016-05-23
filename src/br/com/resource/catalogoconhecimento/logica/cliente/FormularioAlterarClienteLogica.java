package br.com.resource.catalogoconhecimento.logica.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAlterarClienteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("id"));

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ClienteBean clienteBean = clienteBusiness.obterPorId(id);

		request.setAttribute("cliente", clienteBean);

		return "WEB-INF/jsp/cliente/formularioAlterarCliente.jsp";
	}

}