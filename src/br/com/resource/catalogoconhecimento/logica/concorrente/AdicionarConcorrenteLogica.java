package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarConcorrenteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConcorrenteBean concorrenteBean = new ConcorrenteBean();
		concorrenteBean.setNome(request.getParameter("nome"));
		concorrenteBean.setDescricao(request.getParameter("descricao"));
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		concorrenteBusiness.adicionar(concorrenteBean);
		return "mvc?logica=concorrente.ListarConcorrenteLogica";
	}

}
