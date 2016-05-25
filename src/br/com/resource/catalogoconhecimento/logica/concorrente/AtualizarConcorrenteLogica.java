package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AtualizarConcorrenteLogica implements Logica {
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("nome").trim().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else {
			ConcorrenteBean concorrenteBean = new ConcorrenteBean();
			concorrenteBean.setId(Integer.parseInt(request.getParameter("id")));
			concorrenteBean.setNome(request.getParameter("nome").trim());
			concorrenteBean.setDescricao(request.getParameter("descricao").trim());
			ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
			concorrenteBusiness.atualizar(concorrenteBean);
		}
		return "mvc?logica=concorrente.ListarConcorrenteLogica";
	}

}