package br.com.resource.catalogoconhecimento.logica.equipe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarFuncionariosPorEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
		
		List<FuncionarioBean> listaFuncionario = new FuncionarioBusiness().listar();
		List<FuncionarioBean> funcionarioEquipe = new FuncionarioBusiness().listarPorEquipe(idEquipe);
		EquipeBean equipe = new EquipeBusiness().obterPorId(idEquipe);
		
		request.setAttribute("funcionarios", listaFuncionario);
		request.setAttribute("funcionarioEquipe", funcionarioEquipe);
		request.setAttribute("equipe", equipe);
		

		return "/WEB-INF/jsp/equipe/listarFuncionariosPorEquipe.jsp";
	}
}