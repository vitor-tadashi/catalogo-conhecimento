package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarConcorrenteLogic implements Logica {

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse res) throws Exception {

		ConcorrenteBean concorrenteBean = new ConcorrenteBean();
	
		
		concorrenteBean.setIdConcorrente(Integer.parseInt(req.getParameter("idConcorrente")));
		concorrenteBean.setNomeConcorrente(req.getParameter("nomeConcorrente"));
		concorrenteBean.setdescricao(req.getParameter("descricaoConcorrente"));
		
		
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		
		concorrenteBusiness.atualizar(concorrenteBean);
		System.out.println("Concorrente Atualizado com Sucesso!");		
		
		return "mvc?logica=concorrente.ListarConcorrenteLogica";
		
	}
	
}