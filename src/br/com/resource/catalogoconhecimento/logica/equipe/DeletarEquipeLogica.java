package br.com.resource.catalogoconhecimento.logica.equipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;


public class DeletarEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
		
		EquipeBusiness equipebusiness = new EquipeBusiness();
		equipebusiness.deletar(idEquipe);
		
		return "mvc?logica=equipe.ListarEquipeLogica";
			
	}
	
}
