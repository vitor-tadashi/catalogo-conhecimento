package br.com.resource.catalogoconhecimento.logica.projeto;

import java.util.ArrayList;
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
import br.com.resource.catalogoconhecimento.business.ProjetoNegocioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AtualizarProjetoLogica implements Logica{
	
	
	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String nomeProjeto = request.getParameter("nomeProjeto");
		String observacao = request.getParameter("observacao");
		int idEquipe = Integer.parseInt(request.getParameter("equipe"));
		int idCliente = Integer.parseInt(request.getParameter("cliente"));
		String[] negocios = request.getParameterValues("negociosArray[]");
		
		//transferir da String para a lista
		List<NegocioBean> listaNegocio = new ArrayList<>();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		NegocioBean negocio;
		for(int i = 0 ; i < negocios.length ; i ++){
			negocio =  negocioBusiness.listarPorNome(negocios[i]);
			listaNegocio .add(negocio);
		}
		
	    EquipeBusiness equipeBusiness = new EquipeBusiness();
	    EquipeBean equipe = equipeBusiness.listarPorId(idEquipe);
	    ClienteBusiness clienteBusiness = new ClienteBusiness();
	    ClienteBean cliente = clienteBusiness.obterPorId(idCliente);
		
	    ProjetoBusiness projetoBusiness = new ProjetoBusiness();
	    
		ProjetoBean projeto = projetoBusiness.obterPorId(id);
		projeto.setCliente(cliente);
		projeto.setEquipe(equipe);
		projeto.setNome(nomeProjeto);
		projeto.setObservacao(observacao);
		
		
		projetoBusiness.atualizar(projeto);
		
		ProjetoNegocioBusiness projetoNegocio = new ProjetoNegocioBusiness();
		projetoNegocio.atualizar(projeto, listaNegocio );
		

		return "mvc?logica=projeto.ListarProjetoLogica";
	}

}
