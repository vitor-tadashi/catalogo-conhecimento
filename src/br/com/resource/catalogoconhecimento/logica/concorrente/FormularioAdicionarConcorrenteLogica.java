package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAdicionarConcorrenteLogica implements Logica {
	
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ClienteBean> listaCliente = new ClienteBusiness().listar();
		request.setAttribute("listaCliente", listaCliente);
		
		return "/WEB-INF/jsp/concorrente/formularioAdicionarConcorrente.jsp";
	}
}
