package br.com.resource.catalogoconhecimento.logica.equipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAtualizarEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));

		EquipeBean equipe = new EquipeBean();
		equipe.setId(idEquipe);
		
		EquipeDAO equipeDAO = new EquipeDAO();
		request.setAttribute("equipes", equipeDAO.obterPorId(idEquipe));

		return "WEB-INF/jsp/equipe/alteraformulario.jsp";
			   
	}

}
