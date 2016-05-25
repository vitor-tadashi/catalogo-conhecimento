package br.com.resource.catalogoconhecimento.logica.tecnologia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarTecnologiaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");

		if(nome.trim().equals("")){
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else {
		
		TecnologiaBean tecnologia = new TecnologiaBean();
		tecnologia.setId(id);
		tecnologia.setNome(nome);

		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		tecnologiaBusiness.alterar(tecnologia);
		}
		return "mvc?logica=tecnologia.ListarTecnologiaLogica";
	}

}
