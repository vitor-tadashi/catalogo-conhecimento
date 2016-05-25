package br.com.resource.catalogoconhecimento.logica.equipe;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.logica.Logica;


public class FormularioInserirEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "WEB-INF/jsp/equipe/adicionaequipe.jsp";
	
	
	}
}
