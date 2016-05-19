package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class InserirConcorrenteLogic implements Logica {

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		String Nomeconcorrente = req.getParameter("nomeConcorrente");
		String Descricao = req.getParameter("descricao");
		
		ConcorrenteBean concorrenteBean = new ConcorrenteBean();
		concorrenteBean.setNomeConcorrente(Nomeconcorrente);
		concorrenteBean.setdescricao(Descricao);
		
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		concorrenteBusiness.inserir(concorrenteBean);
		
				
		return "mvc?logica=concorrente.ListarConcorrenteLogic";
		
		
	}
	
}	
	
