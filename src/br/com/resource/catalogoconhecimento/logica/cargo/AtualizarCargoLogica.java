package br.com.resource.catalogoconhecimento.logica.cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AtualizarCargoLogica implements Logica{
	
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	
	
	int idCargo = Integer.parseInt(request.getParameter("id"));
	String nomeCargo = request.getParameter("nome");
	CargoBean cargo = new CargoBean();
	cargo.setIdCargo(idCargo);
	cargo.setNomeCargo(nomeCargo);

	CargoBusiness cargoBusiness = new CargoBusiness();
	cargoBusiness.atualizar(cargo);

	return "mvc?logica=cargo.ListarCargoLogica";
	
	}
}