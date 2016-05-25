package br.com.resource.catalogoconhecimento.logica.funcionario;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.bean.EquipeFuncionarioBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarFuncionariosPorEquipeLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FuncionarioBusiness funcionariobusiness = new FuncionarioBusiness();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		
	

		return "/WEB-INF/jsp/concorrente/listarFuncionariosPorEquipe.jsp";
	}
}
