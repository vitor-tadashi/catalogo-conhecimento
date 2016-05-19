package br.com.resource.catalogoconhecimento.logica.concorrente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class RemoverConcorrenteLogic implements Logica {

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int idConcorrente = Integer.parseInt(req.getParameter("idConcorrente"));
		ConcorrenteBean concorrenteBean = new ConcorrenteBean();
		concorrenteBean.setIdConcorrente(idConcorrente);
		
		ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		concorrenteBusiness.deletar(concorrenteBean.getIdConcorrente());
		
		System.out.println("Excluindo Concorrente");
		
		return "mvc?logica=concorrente.ListarConcorrenteLogica";
		
	}

}	
