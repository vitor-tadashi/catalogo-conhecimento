package br.com.resource.catalogoconhecimento.logica.busca;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.exceptions.QuantidadeTagException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarPorClienteBuscaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] array = request.getParameter("filtro").split(",");
		
		if (array.length > 1) {
			throw new QuantidadeTagException("Só é possível pesquisar um cliente por vez!");
		}
		
		String nomeCliente = array[0];
		
		List<ConcorrenteClienteBean> listaConcorrenteCliente = new ConcorrenteBusiness().listarPorNomeCliente(nomeCliente);
		List<ProjetoBean> listaProjeto = new ProjetoBusiness().listarPorNomeCliente(nomeCliente);
		
		request.setAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		request.setAttribute("listaProjeto", listaProjeto);
		
		return "WEB-INF/jsp/busca/listarBuscaCliente.jsp";
	}

}
