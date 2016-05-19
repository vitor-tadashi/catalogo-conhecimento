package br.com.resource.catalogoconhecimento.logica.projeto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AtualizarProjetoLogica implements Logica{
	
	
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));

		ProjetoBean projetoBean = new ProjetoBean();
		projetoBean.setIdProjeto(id);

		ProjetoBusiness projetoBusiness = new ProjetoBusiness();
		ProjetoBean projeto = projetoBusiness.listarPorId(id);

		request.setAttribute("projetos", projeto);


		return "/WEB-INF/jsp//projetos/alterarProjetos.jsp";
	}

}
