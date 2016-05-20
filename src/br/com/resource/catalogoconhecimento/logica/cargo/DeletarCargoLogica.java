package br.com.resource.catalogoconhecimento.logica.cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class DeletarCargoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	int idCargo = Integer.parseInt(request.getParameter("idCargo"));

	
	CargoBusiness cargobusiness = new CargoBusiness();
	cargobusiness.deletar(idCargo);
	 
	return "mvc?logica=cargo.ListarCargoLogica";

	}
}
