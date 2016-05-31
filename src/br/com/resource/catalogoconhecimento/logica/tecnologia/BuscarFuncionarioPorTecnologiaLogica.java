package br.com.resource.catalogoconhecimento.logica.tecnologia;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class BuscarFuncionarioPorTecnologiaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
			List<FuncionarioBean> listaFuncionarios = tecnologiaBusiness.obterFuncionarioPorTecnologia(id);
			request.setAttribute("funcionarios", listaFuncionarios);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}

		return "/WEB-INF/jsp/tecnologias/buscarFuncionarioPorTecnologia.jsp";
	}

}
