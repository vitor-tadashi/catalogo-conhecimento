package br.com.resource.catalogoconhecimento.logica.cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAtualizarCargoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int idCargo = Integer.parseInt(request.getParameter("idCargo"));
		
		CargoBusiness cargoBusiness = new CargoBusiness();
		CargoBean cargo = cargoBusiness.obterPorId(idCargo);

		request.setAttribute("cargo", cargo);

		return "/WEB-INF/jsp/cargo/formularioAlteraCargo.jsp";
		}
}
