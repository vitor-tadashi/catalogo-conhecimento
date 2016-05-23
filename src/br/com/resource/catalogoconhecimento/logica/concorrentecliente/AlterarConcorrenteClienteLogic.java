package br.com.resource.catalogoconhecimento.logica.concorrentecliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarConcorrenteClienteLogic implements Logica {

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ConcorrenteClienteBean concorrenteclienteBean = new ConcorrenteClienteBean();
			
		concorrenteclienteBean.setIdConcorrenteCliente(Integer.parseInt(req.getParameter("idConcorrentecliente")));
		concorrenteclienteBean.setValorHora(Integer.parseInt(req.getParameter("valorhora")));
				
		ConcorrenteClienteBusiness concorrenteclienteBusiness = new ConcorrenteClienteBusiness();
		
		concorrenteclienteBusiness.atualizar(concorrenteclienteBean);
		System.out.println("ConcorrenteCliente Atualizado com Sucesso!");		
		
		return "mvc?logica=concorrentecliente.ListarConcorrenteClienteLogica";
		
		
	}

}
