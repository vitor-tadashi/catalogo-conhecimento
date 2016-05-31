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
		String nomeTecnologias = "";
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		
		for(int i = 0; i < array.length; i ++){
			if(tecnologiaBusiness.obterPorNome(array[i]) != null){
				if(nomeTecnologias.isEmpty()){
					nomeTecnologias += "'"+array[i]+"'";
				}else{
					nomeTecnologias += ",'"+array[i]+"'";
				}
			}
		}
		
		List<ProjetoBean> projetos = new ProjetoBusiness().obterPorTecnologias(nomeTecnologias);
		
		request.setAttribute("projetos", projetos);
		
		return "WEB-INF/jsp/projetos/listarProjetos.jsp";
	}

}
