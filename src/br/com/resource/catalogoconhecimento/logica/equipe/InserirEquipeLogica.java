package br.com.resource.catalogoconhecimento.logica.equipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class InserirEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
						
		String observacao = request.getParameter("observacao");
		String nome = request.getParameter("nome");
		
		EquipeBean equipe = new EquipeBean();
		equipe.setObservacao(observacao);
		equipe.setNome(nome);
		
		EquipeBusiness equipeBusiness = new EquipeBusiness();
		equipeBusiness.inserir(equipe);

		return "mvc?logica=equipe.ListarEquipeLogica";
		
	}

}
