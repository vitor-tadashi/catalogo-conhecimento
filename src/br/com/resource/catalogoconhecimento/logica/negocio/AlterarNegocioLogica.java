package br.com.resource.catalogoconhecimento.logica.negocio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarNegocioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String areaAtuacao = request.getParameter("areaAtuacao");

		NegocioBean negocioBean = new NegocioBean();
		negocioBean.setId(id);
		negocioBean.setAreaAtuacao(areaAtuacao.trim());

		NegocioBusiness negocioBusiness = new NegocioBusiness();
		negocioBusiness.alterar(negocioBean);

		return "mvc?logica=negocio.ListarNegocioLogica";
	}
}
