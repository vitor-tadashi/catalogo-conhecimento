package br.com.resource.catalogoconhecimento.logica.cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAlterarCargoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));

		CargoBusiness cargoBusiness = new CargoBusiness();
		CargoBean cargoBean = cargoBusiness.obterPorId(id);

		request.setAttribute("cargo", cargoBean);

		return "/WEB-INF/jsp/cargo/formularioAlterarCargo.jsp";
	}
}
