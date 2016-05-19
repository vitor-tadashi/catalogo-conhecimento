package br.com.resource.catalogoconhecimento.funcionario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class RemoverFuncionarioLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		funcionarioBusiness.deletar(id);
		
		return "mvc?logica=funcionario.ListarFuncionarioLogica";
		
	}

}
