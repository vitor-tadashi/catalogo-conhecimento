package br.com.resource.catalogoconhecimento.logica.cargo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarCargoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome").trim();

		if (nome.equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else {
			CargoBean cargoBean = new CargoBean();
			cargoBean.setId(id);
			cargoBean.setNome(nome);
			
			CargoBusiness cargoBusiness = new CargoBusiness();
			cargoBusiness.alterar(cargoBean);
		}
		
		return "mvc?logica=cargo.ListarCargoLogica";
	}
}