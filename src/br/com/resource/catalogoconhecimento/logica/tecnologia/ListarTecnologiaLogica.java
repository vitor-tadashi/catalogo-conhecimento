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
		List<TecnologiaBean> listaTecnologia = new TecnologiaBusiness().listar();

		request.setAttribute("tecnologias", listaTecnologia);

		return "/WEB-INF/jsp/tecnologias/listarTecnologia.jsp";
	}

}
