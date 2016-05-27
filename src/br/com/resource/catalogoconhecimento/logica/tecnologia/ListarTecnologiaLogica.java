package br.com.resource.catalogoconhecimento.logica.tecnologia;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarTecnologiaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness(); 
			List<TecnologiaBean> listaTecnologia = tecnologiaBusiness.listar();
			
			request.setAttribute("tecnologias",listaTecnologia);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return "/WEB-INF/jsp/tecnologias/listarTecnologia.jsp";
	}
	
}
