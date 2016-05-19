package br.com.resource.catalogoconhecimento.logica.equipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.logica.Logica;


public class DeletarEquipeLogic implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
		
		EquipeBean equipe = new EquipeBean();
		equipe.setIdEquipe(idEquipe);
		
		EquipeBusiness equipebusiness = new EquipeBusiness();
		equipebusiness.deletar(equipe);
				
		
		EquipeDAO equipeDAO = new EquipeDAO();
		equipeDAO.listarPorId(idEquipe);
		equipeDAO.deletar(equipe);
		
		
		return "mvc?logica=equipe.ListaEquipeLogic";
			
	}
	
}
