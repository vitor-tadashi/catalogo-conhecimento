package br.com.resource.catalogoconhecimento.logica.projeto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarProjetoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ProjetoBean projetoBean = new ProjetoBean();
		//ProjetoBusiness projetoBusiness = new ProjetoBusiness();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String observacao = request.getParameter("observacao");

			//Update
			projetoBean.setIdProjeto(id);
			projetoBean.setNomeProjeto(nome);
			projetoBean.setObservacao(observacao);
			
			List<ProjetoBean> projetos = new ProjetoBusiness().listar();
			request.setAttribute("projetos", projetos);

		return "/WEB-INF/jsp/jsp/listarProjetos.jsp";
			
	}
	
}
