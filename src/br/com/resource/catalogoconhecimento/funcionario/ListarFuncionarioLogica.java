package br.com.resource.catalogoconhecimento.funcionario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarFuncionarioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		List<FuncionarioBean> funcionarios = new FuncionarioBusiness().listar();
		
		request.setAttribute("funcionarios", funcionarios);
		
		return "/WEB-INF/jsp/funcionarios/listarFuncionarios.jsp";
		
	}

}
 