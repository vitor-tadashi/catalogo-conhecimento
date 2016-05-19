package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioInsereConcorrenteLogic implements Logica{
	
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/WEB-INF/jsp/concorrente/inserirConcorrente.jsp";
		
		
	}

}
