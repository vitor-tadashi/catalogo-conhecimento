package br.com.resource.catalogoconhecimento.logica.funcionario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAlterarLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		
		FuncionarioBean funcionario = funcionarioBusiness.listarPorId(id);
		
		request.setAttribute("funcionario", funcionario);
		
		return "/WEB-INF/jsp/funcionarios/formularioAlterar.jsp";
	}

}
