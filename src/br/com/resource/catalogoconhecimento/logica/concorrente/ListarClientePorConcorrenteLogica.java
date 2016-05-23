package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

@WebServlet("/ListarClientePorConcorrenteLogica")
public class ListarClientePorConcorrenteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();

		int id = Integer.parseInt(request.getParameter("id"));
		List<ConcorrenteClienteBean> concorrentesClientes =	concorrenteBusiness.obterPorId(id);
		request.setAttribute("concorrentesClientes", concorrentesClientes);
		ConcorrenteBean concorrente = concorrentesClientes.get(0).getConcorrente();
		request.setAttribute("concorrente", concorrente);
		
		return "/WEB-INF/jsp/concorrente/listarClientePorConcorrente.jsp";
	}

}
