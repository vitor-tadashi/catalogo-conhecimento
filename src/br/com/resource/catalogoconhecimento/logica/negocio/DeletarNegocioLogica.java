package br.com.resource.catalogoconhecimento.logica.negocio;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class DeletarNegocioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int idNegocio = Integer.parseInt(request.getParameter("idNegocio"));

		NegocioBusiness negocioBusiness = new NegocioBusiness();
		negocioBusiness.remover(idNegocio);


		return "mvc?logica=negocio.ListarNegocioLogica";
	}

}
