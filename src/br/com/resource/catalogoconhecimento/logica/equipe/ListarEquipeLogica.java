package br.com.resource.catalogoconhecimento.logica.equipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;
public class ListarEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<EquipeBean> listaEquipe = new EquipeBusiness().listar();
		


		request.setAttribute("equipes", listaEquipe);

		return "/WEB-INF/jsp/equipe/listarEquipe.jsp";
		
		
		
		
		

	}

}
