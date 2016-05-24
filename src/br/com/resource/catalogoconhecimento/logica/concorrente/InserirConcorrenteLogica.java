package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class InserirConcorrenteLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");

		ConcorrenteBean concorrenteBean = new ConcorrenteBean();
		concorrenteBean.setNome(nome);
		concorrenteBean.setDescricao(descricao);

		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		concorrenteBusiness.adicionar(concorrenteBean);

		return "mvc?logica=concorrente.ListarConcorrenteLogica";
	}

}
