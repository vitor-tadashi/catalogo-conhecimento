package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class RemoverConcorrenteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		concorrenteBusiness.remover(Integer.parseInt(request.getParameter("id")));
		return "mvc?logica=concorrente.ListarConcorrenteLogica";
	}
}
