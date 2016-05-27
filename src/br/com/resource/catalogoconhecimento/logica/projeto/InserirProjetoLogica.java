package br.com.resource.catalogoconhecimento.logica.projeto;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoNegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoTecnologiaBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class InserirProjetoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nomeProjeto = request.getParameter("nomeProjeto");
		String observacao = request.getParameter("observacao");
		int idEquipe = Integer.parseInt(request.getParameter("equipe"));
		int idCliente = Integer.parseInt(request.getParameter("cliente"));
		String[] negocios = request.getParameterValues("negociosArray[]");
		String[] tecnologias = request.getParameterValues("tecnologiasArray[]");
		

		if(nomeProjeto.trim().equals("")){
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else if( observacao.trim().equals("")){
			throw new AtributoNuloException("Por favor, digite uma observação válida!");
		}
		
		//transferir da String para a lista
		List<NegocioBean> listaNegocio = new ArrayList<>();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		NegocioBean negocio;
		for(int i = 0 ; i < negocios.length ; i ++){
			negocio =  negocioBusiness.obterPorNome(negocios[i]);
			listaNegocio.add(negocio);
		}
		
		//transferir da String para a lista
		List<TecnologiaBean> listaTecnologia = new ArrayList<>();
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		TecnologiaBean tecnologia;
		for(int i = 0 ; i < tecnologias.length ; i ++){
			tecnologia =  tecnologiaBusiness.obterPorNome(tecnologias[i]);
			listaTecnologia.add(tecnologia);
		}
		
	    EquipeBusiness equipeBusiness = new EquipeBusiness();
	    EquipeBean equipe = equipeBusiness.listarPorId(idEquipe);
	    ClienteBusiness clienteBusiness = new ClienteBusiness();
	    ClienteBean cliente = clienteBusiness.obterPorId(idCliente);
		
		ProjetoBean projeto = new ProjetoBean();
		projeto.setCliente(cliente);
		projeto.setEquipe(equipe);
		projeto.setNome(nomeProjeto);
		projeto.setObservacao(observacao);
		
		ProjetoBusiness projetoBusiness = new ProjetoBusiness();
		projetoBusiness.inserir(projeto);
	
		
		ProjetoNegocioBusiness projetoNegocio = new ProjetoNegocioBusiness();
		projetoNegocio.inserir(projeto, listaNegocio);
		
		ProjetoTecnologiaBusiness projetoTecnologia = new ProjetoTecnologiaBusiness();
		projetoTecnologia.inserir(projeto, listaTecnologia);
		

		return "mvc?logica=projeto.ListarProjetoLogica";
		
	}
}
