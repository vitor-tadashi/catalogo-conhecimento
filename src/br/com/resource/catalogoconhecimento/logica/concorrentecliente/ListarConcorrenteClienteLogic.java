package br.com.resource.catalogoconhecimento.logica.concorrentecliente;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarConcorrenteClienteLogic implements Logica{

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		List<ConcorrenteClienteBean> listConcorrenteCliente = new ConcorrenteClienteBusiness().listar();
		req.setAttribute("concorrentecliente", listConcorrenteCliente);
		
		return "/WEB-INF/jsp/concorrentecliente/listarconcorrentecliente.jsp";
	
	}

}
