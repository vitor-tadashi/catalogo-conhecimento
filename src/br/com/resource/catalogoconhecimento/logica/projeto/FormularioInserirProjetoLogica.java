package br.com.resource.catalogoconhecimento.logica.projeto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioInserirProjetoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		EquipeBusiness equipeBusiness = new EquipeBusiness();
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		
		List<EquipeBean> listaEquipe = equipeBusiness.listar();
		List<ClienteBean> listaCliente = clienteBusiness.listar();
		List<NegocioBean> listaNegocio = negocioBusiness.listar();
		
		request.setAttribute("equipes",listaEquipe);
		request.setAttribute("clientes", listaCliente);
		request.setAttribute("negocios", listaNegocio);
		
		return "/WEB-INF/jsp/projetos/inserirProjetos.jsp";
	}
}
