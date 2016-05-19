package br.com.resource.catalogoconhecimento.logica.projeto;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoDAO;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class DeletarProjetoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int idProjeto = Integer.parseInt(request.getParameter("idProjeto"));

		ProjetoBean projetoBean = new ProjetoBean();
		projetoBean.setIdProjeto(idProjeto);
		
		ProjetoDAO projetodao= new ProjetoDAO();
		projetodao.deletar(projetoBean);
		

		return "mvc?logica=projeto.ListarProjetoLogica";
	}

}
