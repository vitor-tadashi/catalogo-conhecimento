package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAlterarConcorrenteLogica implements Logica {
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		ConcorrenteBean concorrenteBean = concorrenteBusiness.obterPorId(id);
		request.setAttribute("concorrente", concorrenteBean);
		return "/WEB-INF/jsp/concorrente/formularioAlterarConcorrente.jsp";
	}
}
