package br.com.resource.catalogoconhecimento.controller;

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
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class ProjetoController {

	@Autowired
	ProjetoBusiness projetoBusiness;

	@Autowired
	ClienteBusiness clienteBusiness;

	@Autowired
	NegocioBusiness negocioBusiness;

	@RequestMapping(value = "formularioAdicionarProjeto", method = RequestMethod.GET)
	public String formularioAdicionar(Model model) throws BusinessException {
		return "projetos/formularioAdicionarProjeto";
	}
	
//	CLIENTE, NOME, OBSERVA��O
	@RequestMapping(value = "adicionarProjeto", method = RequestMethod.POST)
	public String adicionarProjeto(ProjetoBean projetoBean,
		@RequestParam(value = "cliente") String cliente)throws BusinessException {

		ClienteBean clienteBean = clienteBusiness.obterPorNome(cliente);
		
		projetoBean.setCliente(clienteBean);
		projetoBusiness.adicionar(projetoBean);
		
		return "redirect:listarProjetos";
	}

	@RequestMapping(value = "listarProjeto", method = RequestMethod.GET)
	public String listar(Model model) throws BusinessException {

		model.addAttribute("projeto", projetoBusiness.listar());

		return "projetos/listarProjeto";
	}
	
	@RequestMapping(value = "formularioAlterarProjeto", method = RequestMethod.GET)
	public String alterar(Model model, @RequestParam("idProjeto") String id) throws BusinessException {
		int idProjeto = Integer.parseInt(id);
		model.addAttribute("projeto", negocioBusiness.obterPorId(idProjeto));
		return "projetos/formularioAlterarProjeto";
	}

	@RequestMapping(value = "alterarProjeto", method = RequestMethod.POST)
	public String alterar(ProjetoBean projetoBean) throws BusinessException {
		projetoBusiness.alterar(projetoBean);
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
		return "index";
	}
}