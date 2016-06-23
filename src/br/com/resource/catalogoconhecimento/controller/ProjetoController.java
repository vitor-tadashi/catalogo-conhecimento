package br.com.resource.catalogoconhecimento.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoEquipeBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoNegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoTecnologiaBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class ProjetoController {

	@Autowired
	ProjetoBusiness projetoBusiness;

	@Autowired
	ClienteBusiness clienteBusiness;

	@Autowired
	NegocioBusiness negocioBusiness;

	@Autowired
	TecnologiaBusiness tecnologiaBusiness;

	@Autowired
	EquipeBusiness equipeBusiness;
	
	@Autowired
	ProjetoEquipeBusiness projetoEquipe;
	
	@Autowired
	ProjetoNegocioBusiness projetoNegocio;
	
	@Autowired
	ProjetoTecnologiaBusiness projetoTecnologia;

	@RequestMapping(value = "formularioAdicionarProjeto", method = RequestMethod.GET)
	public String formularioAdicionar(Model model) throws BusinessException {
		List<NegocioBean> listaNegocio = negocioBusiness.listar();
		List<TecnologiaBean> listaTecnologia = tecnologiaBusiness.listar();
		List<EquipeBean> listaEquipe = equipeBusiness.listar();
		List<ClienteBean> listaCliente = clienteBusiness.listar();

		model.addAttribute("negocios", listaNegocio);
		model.addAttribute("clientes", listaCliente);
		model.addAttribute("tecnologias", listaTecnologia);
		model.addAttribute("equipes", listaEquipe);
		
		return "projetos/formularioAdicionarProjeto";
	}

	@RequestMapping(value = "adicionarProjeto", method = RequestMethod.POST)
	public String adicionarProjeto(ProjetoBean projetoBean)
			throws BusinessException {

		projetoBusiness.adicionar(projetoBean);
		projetoEquipe.adicionar(projetoBean, projetoBean.getListaEquipe());
		projetoNegocio.adicionar(projetoBean, projetoBean.getListaNegocio());
		projetoTecnologia.adicionar(projetoBean, projetoBean.getListaTecnologia());

		return "redirect:listarProjetos";
	}

	@RequestMapping(value = "listarProjeto", method = RequestMethod.GET)
	public String listar(Model model) throws BusinessException {

		model.addAttribute("projeto", projetoBusiness.listar());

		return "projetos/listarProjeto";
	}

	@RequestMapping(value = "formularioAlterarProjeto", method = RequestMethod.GET)
	public String alterar(Model model, @RequestParam("idProjeto") String id) throws BusinessException {
		List<NegocioBean> listaNegocio = negocioBusiness.listar();
		List<TecnologiaBean> listaTecnologia = tecnologiaBusiness.listar();
		List<EquipeBean> listaEquipe = equipeBusiness.listar();
		List<ClienteBean>listaCliente = clienteBusiness.listar();
		int idProjeto = Integer.parseInt(id);
		
		model.addAttribute("projeto", projetoBusiness.obterPorId(idProjeto));
		model.addAttribute("negocios", listaNegocio);
		model.addAttribute("tecnologias", listaTecnologia);
		model.addAttribute("equipes", listaEquipe);
		model.addAttribute("clientes", listaCliente);
		
		
		return "projetos/formularioAlterarProjeto";
	}

	@RequestMapping(value = "alterarProjeto", method = RequestMethod.POST)
	public String alterar(ProjetoBean projetoBean, @RequestParam("nome")String nome) throws BusinessException {
		projetoBean.setNome(nome);
		projetoBusiness.atualizar(projetoBean);
		projetoNegocio.atualizar(projetoBean, projetoBean.getListaNegocio());
		projetoTecnologia.atualizar(projetoBean, projetoBean.getListaTecnologia());
		projetoEquipe.atualizar(projetoBean, projetoBean.getListaEquipe());
		
		return "redirect:listarProjeto";
	}

	@RequestMapping(value = "removerProjeto", method = RequestMethod.GET)
	public String remover(@RequestParam("idProjeto") String id) throws BusinessException {
		int idProjeto = Integer.parseInt(id);
		projetoBusiness.remover(idProjeto);
		
		return "redirect:listarProjeto";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "redirect:listarProjeto";
	}
}