package br.com.resource.catalogoconhecimento.logica.tecnologia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarTecnologiaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nome = request.getParameter("nome");
		
		TecnologiaBean tecnologiaBean = new TecnologiaBean();
		tecnologiaBean.setNome(nome.trim());
		
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		tecnologiaBusiness.adicionar(tecnologiaBean);
		
		return "mvc?logica=tecnologia.ListarTecnologiaLogica";
	}

}
