package br.com.resource.catalogoconhecimento.logica.concorrentecliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteClienteBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class InserirConcorrenteClienteLogic implements Logica{

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int idConcorrenteCliente = Integer.parseInt(req.getParameter("idConcorrenteCliente"));
		ConcorrenteClienteBean concorrenteClienteBean = new ConcorrenteClienteBean();
		concorrenteClienteBean.setIdConcorrenteCliente(idConcorrenteCliente);
		
		ConcorrenteClienteBusiness concorrenteClienteBusiness = new ConcorrenteClienteBusiness();
		concorrenteClienteBusiness.inserir(concorrenteClienteBean);
		
				
		return "mvc?logica=concorrentecliente.ListarConcorrenteClienteLogica";
		
	}

}
