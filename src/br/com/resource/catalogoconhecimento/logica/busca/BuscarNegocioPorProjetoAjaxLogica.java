package br.com.resource.catalogoconhecimento.logica.busca;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class BuscarNegocioPorProjetoAjaxLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int idProjeto = Integer.parseInt(request.getParameter("idProjeto"));
		
		ProjetoBean projetoBean = new ProjetoBusiness().obterPorId(idProjeto);
		
		List<NegocioBean>listaNegocio = new NegocioDAO().obterPorProjeto(projetoBean);
		
		return new Gson().toJson(listaNegocio);
		
		
	}

}
