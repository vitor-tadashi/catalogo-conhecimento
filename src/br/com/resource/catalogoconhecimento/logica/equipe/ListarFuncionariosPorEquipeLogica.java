package br.com.resource.catalogoconhecimento.logica.equipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarFuncionariosPorEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
		
		List<FuncionarioBean> listaFuncionario = new FuncionarioBusiness().listar();
		List<FuncionarioBean> funcionarioEquipe = new FuncionarioBusiness().obterPorEquipe(idEquipe);
		
		request.setAttribute("funcionarios", listaFuncionario);
		request.setAttribute("funcionarioEquipe", funcionarioEquipe);
		request.setAttribute("idEquipe", idEquipe);

		return "/WEB-INF/jsp/equipe/listarFuncionariosPorEquipe.jsp";
	}
}