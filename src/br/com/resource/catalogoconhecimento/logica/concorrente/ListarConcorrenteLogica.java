package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarConcorrenteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ConcorrenteBean> listaConcorrentes = new ConcorrenteBusiness().listar();
		request.setAttribute("concorrentes",listaConcorrentes );

		return "/WEB-INF/jsp/concorrente/listarConcorrente.jsp";
	}
}
