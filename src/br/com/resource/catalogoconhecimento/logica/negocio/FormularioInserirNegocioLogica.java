package br.com.resource.catalogoconhecimento.logica.negocio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioInserirNegocioLogica implements Logica {
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "WEB-INF/jsp/negocios/formularioInserirNegocio.jsp";
		
	
	}

}
