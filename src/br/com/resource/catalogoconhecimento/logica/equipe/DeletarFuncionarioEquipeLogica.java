package br.com.resource.catalogoconhecimento.logica.equipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class DeletarFuncionarioEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
		int idFuncionario = Integer.parseInt(request.getParameter("idFuncionario"));
		
		EquipeBusiness equipebusiness = new EquipeBusiness();
		equipebusiness.deletarPorEquipe(idEquipe, idFuncionario);
		

		String url = "/mvc?logica=equipe.ListarFuncionariosPorEquipeLogica&idEquipe="+request.getParameter("idEquipe");
	
		return url;
		
		
	}


	
}
