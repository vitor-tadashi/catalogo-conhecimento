package br.com.resource.catalogoconhecimento.logica.concorrentecliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAlterarConcorrenteClienteLogic implements Logica {

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int idConcorrenteCliente = Integer.parseInt(req.getParameter("idConcorrenteCliente"));
		ConcorrenteClienteBusiness concorrenteclienteBusiness = new ConcorrenteClienteBusiness();

		ConcorrenteClienteBean concorrenteclienteBean = new ConcorrenteClienteBean();
		concorrenteclienteBean.setIdConcorrenteCliente(idConcorrenteCliente);
		concorrenteclienteBusiness.listar();

		req.setAttribute("idConcorrentecliente", idConcorrenteCliente);

		return "/WEB-INF/jsp/concorrentecliente/alterarconcorrentecliente.jsp";

	}

}
