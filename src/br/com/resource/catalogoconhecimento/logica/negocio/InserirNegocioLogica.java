package br.com.resource.catalogoconhecimento.logica.negocio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class InserirNegocioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String areaAtuacao = request.getParameter("areaAtuacao").trim();
		
		if(areaAtuacao.equals("")){
			throw new AtributoNuloException("Por favor, digite um valor válido");
		}else{       								   
			
		NegocioBean negocioBean = new NegocioBean();
		negocioBean.setAreaAtuacao(areaAtuacao);
		
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		negocioBusiness.adicionar(negocioBean);

		}
		return "mvc?logica=negocio.ListarNegocioLogica";
		
	}
}
