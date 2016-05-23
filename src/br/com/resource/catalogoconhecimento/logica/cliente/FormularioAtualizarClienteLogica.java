package br.com.resource.catalogoconhecimento.logica.cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAtualizarClienteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("id"));

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ClienteBean cliente = clienteBusiness.listarPorId(id);

		request.setAttribute("cliente", cliente);

		return "WEB-INF/jsp/cliente/formularioAtualizarCliente.jsp";
	}

}