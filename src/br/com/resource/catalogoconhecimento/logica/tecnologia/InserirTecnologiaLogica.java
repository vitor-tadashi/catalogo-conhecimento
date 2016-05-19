package br.com.resource.catalogoconhecimento.logica.tecnologia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class InserirTecnologiaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nome = request.getParameter("nome");
		TecnologiaBean tecnologia = new TecnologiaBean();
		tecnologia.setNomeTecnologia(nome);
		
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		tecnologiaBusiness.inserir(tecnologia);
		
		return "mvc?logica=tecnologia.ListarTecnologiaLogica";
	}

}
