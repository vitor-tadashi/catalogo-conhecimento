package br.com.resource.catalogoconhecimento.logica.equipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AtualizarEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
		String observacao = request.getParameter("observacao");

		EquipeBean equipe = new EquipeBean();
		equipe.setIdEquipe(idEquipe);
		equipe.setObservacao(observacao);

		EquipeBusiness equipeBusiness = new EquipeBusiness();
		equipeBusiness.atualizar(equipe);
		
		request.setAttribute("equipes", equipeBusiness);

		return "mvc?logica=ListaEquipeLogic";
		
		
	}

}
