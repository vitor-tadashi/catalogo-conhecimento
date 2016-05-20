package br.com.resource.catalogoconhecimento.logica.cargo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarCargoLogica implements Logica {

	
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		List<CargoBean> cargos = new CargoBusiness().listar();
		request.setAttribute("cargos", cargos);
		
		return "/WEB-INF/jsp/cargo/listarCargos.jsp";
	}
}
