package br.com.resource.catalogoconhecimento.logica.equipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class InserirEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		String nome = request.getParameter("nome");
		String observacao = request.getParameter("observacao");

		if (nome.trim().equals("")) {
			throw new AtributoNuloException();
		} else if (observacao.trim().equals("")) {
			throw new AtributoNuloException();
		} else {

			EquipeBean equipe = new EquipeBean();
			equipe.setObservacao(observacao);
			equipe.setNome(nome);

			EquipeBusiness equipeBusiness = new EquipeBusiness();
			equipeBusiness.inserir(equipe);

		}

		return "mvc?logica=equipe.ListarEquipeLogica";

	}

}
