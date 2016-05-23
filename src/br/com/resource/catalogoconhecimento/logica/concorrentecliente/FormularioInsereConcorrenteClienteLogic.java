package br.com.resource.catalogoconhecimento.logica.concorrentecliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioInsereConcorrenteClienteLogic implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/WEB-INF/jsp/concorrentecliente/inserirconcorrentecliente.jsp";
	}
}
