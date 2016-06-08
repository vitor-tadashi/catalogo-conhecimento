package br.com.resource.catalogoconhecimento.logica.busca;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class BuscarEquipeNoProjetoAjaxLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 int idProjeto = Integer.parseInt(request.getParameter("idProjeto"));
	 
	 List<EquipeBean> listaEquipe = new EquipeBusiness().obterPorProjeto(idProjeto);
	 
	 return new Gson().toJson(listaEquipe);
	}
	

}
