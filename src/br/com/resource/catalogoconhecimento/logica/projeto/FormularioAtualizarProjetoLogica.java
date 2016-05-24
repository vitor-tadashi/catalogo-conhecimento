package br.com.resource.catalogoconhecimento.logica.projeto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAtualizarProjetoLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("idProjeto"));
		
		ProjetoBusiness projetoBusiness = new ProjetoBusiness();
		ProjetoBean projeto = projetoBusiness.obterPorId(id);
		
		EquipeBusiness equipeBusiness = new EquipeBusiness();
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		
		List<EquipeBean> listaEquipe = equipeBusiness.listar();
		List<ClienteBean> listaCliente = clienteBusiness.listar();
		List<NegocioBean> listaNegocio = negocioBusiness.listar();
		
		request.setAttribute("equipes",listaEquipe);
		request.setAttribute("clientes", listaCliente);
		request.setAttribute("negocios", listaNegocio);
		request.setAttribute("projeto", projeto);
		
		return "WEB-INF/jsp/projetos/formularioAlterarProjetos.jsp";
		
	}

}
