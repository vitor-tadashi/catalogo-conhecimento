package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarConcorrenteLogic implements  Logica{

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		List<ConcorrenteClienteBean> listconcorrente = new ConcorrenteBusiness().listar();
		req.setAttribute("concorrente", listconcorrente);
		
		return "/WEB-INF/jsp/concorrente/listarConcorrente.jsp";
		
		
	}
	

}
