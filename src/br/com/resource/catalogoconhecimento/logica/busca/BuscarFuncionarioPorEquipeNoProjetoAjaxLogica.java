package br.com.resource.catalogoconhecimento.logica.busca;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class BuscarFuncionarioPorEquipeNoProjetoAjaxLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));

		List<FuncionarioBean>funcionarios = new FuncionarioBusiness().listarPorEquipe(idEquipe);
		
		return new Gson().toJson(funcionarios);
	}

	
}
