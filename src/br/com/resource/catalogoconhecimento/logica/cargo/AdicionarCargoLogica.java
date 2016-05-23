package br.com.resource.catalogoconhecimento.logica.cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarCargoLogica implements Logica {
	
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String nome = request.getParameter("nome");
		CargoBean cargoBean = new CargoBean();
		cargoBean.setNome(nome);
		
		CargoBusiness cargoBusiness = new CargoBusiness();
		cargoBusiness.adicionar(cargoBean);
		
		return "mvc?logica=cargo.ListarCargoLogica";
	}

}
