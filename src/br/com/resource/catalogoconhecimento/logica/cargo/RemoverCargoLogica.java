package br.com.resource.catalogoconhecimento.logica.cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class RemoverCargoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));

		CargoBusiness cargobusiness = new CargoBusiness();
		cargobusiness.remover(id);

		return "mvc?logica=cargo.ListarCargoLogica";
	}
	
}
