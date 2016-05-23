package br.com.resource.catalogoconhecimento.logica.concorrente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

@WebServlet("/ListarClientesPorConcorrente")
public class ListarClientePorConcorrente implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		int id = Integer.parseInt(request.getParameter("id"));
//		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
//		
//		ConcorrenteClienteBean concorrenteCliente = concorrenteBusiness.
//		
		return null;
	}

}
