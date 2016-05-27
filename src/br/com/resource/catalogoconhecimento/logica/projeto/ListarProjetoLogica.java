package br.com.resource.catalogoconhecimento.logica.projeto;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarProjetoLogica implements  Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<ProjetoBean> listaProjeto = new ProjetoBusiness().listar();
		
		request.setAttribute("projetos", listaProjeto);
		
		
		return "/WEB-INF/jsp/projetos/listarProjetos.jsp";
	}
}