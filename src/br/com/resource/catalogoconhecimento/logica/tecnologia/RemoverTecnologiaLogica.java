package br.com.resource.catalogoconhecimento.logica.tecnologia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class RemoverTecnologiaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		int idTecnologia = Integer.parseInt(request.getParameter("idTecnologia"));
				
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		tecnologiaBusiness.remover(idTecnologia);
		
		return "mvc?logica=tecnologia.ListarTecnologiaLogica";
	}

}
