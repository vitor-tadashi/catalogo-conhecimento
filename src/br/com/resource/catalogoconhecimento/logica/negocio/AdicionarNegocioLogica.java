package br.com.resource.catalogoconhecimento.logica.negocio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarNegocioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		NegocioBean negocioBean = new NegocioBean();
		negocioBean.setAreaAtuacao(request.getParameter("areaAtuacao").trim());

		NegocioBusiness negocioBusiness = new NegocioBusiness();
		negocioBusiness.adicionar(negocioBean);
		return "mvc?logica=negocio.ListarNegocioLogica";
	}
}
