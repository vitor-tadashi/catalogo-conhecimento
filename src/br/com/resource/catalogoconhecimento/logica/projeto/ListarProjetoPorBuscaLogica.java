package br.com.resource.catalogoconhecimento.logica.projeto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarProjetoPorBuscaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String [] array = request.getParameter("tags").split(",");
		
		List<String> palavras = Arrays.asList(array);
		TecnologiaBean tecnologia = null;
		List<ProjetoBean> projetos = new ArrayList<>();
		for(String s:palavras){
			tecnologia = new TecnologiaBusiness().obterPorNome(s);
			projetos.addAll(new ProjetoBusiness().obterPorTecnologia(tecnologia));
		}
		
		request.setAttribute("projetos", projetos);
		
		return "WEB-INF/jsp/projetos/listarProjetos.jsp";
	}

}
