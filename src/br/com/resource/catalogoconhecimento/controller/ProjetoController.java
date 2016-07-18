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

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
@RequestMapping("/projeto")
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



	@RequestMapping(value = "formularioAdicionarProjeto", method = RequestMethod.GET)
	public String formularioAdicionar(Model model) throws BusinessException {
		model.addAttribute("negocios", negocioBusiness.listar());
		model.addAttribute("clientes", clienteBusiness.listar());
		model.addAttribute("tecnologias", tecnologiaBusiness.listar());
		model.addAttribute("equipes", equipeBusiness.listar());

		return "projetos/formularioAdicionarProjeto";
	}

	@RequestMapping(value = "adicionarProjeto", method = RequestMethod.POST)
	public String adicionarProjeto(ProjetoBean projetoBean) throws BusinessException {
		projetoBusiness.adicionar(projetoBean);

		return "redirect:listarProjeto";
	}

	@RequestMapping(value = "listarProjeto", method = { RequestMethod.GET, RequestMethod.POST })
	public String listar(Model model) throws BusinessException {
		model.addAttribute("projetos", projetoBusiness.listar());

		return "projetos/listarProjeto";
	}

	@RequestMapping(value = "formularioAlterarProjeto", method = RequestMethod.GET)
	public String alterar(Model model, @RequestParam("idProjeto") String id) throws BusinessException {
		int idProjeto = Integer.parseInt(id);

		model.addAttribute("projeto", projetoBusiness.obterPorId(idProjeto));
		model.addAttribute("negocios", negocioBusiness.listar());
		model.addAttribute("clientes", clienteBusiness.listar());
		model.addAttribute("tecnologias", tecnologiaBusiness.listar());
		model.addAttribute("equipes", equipeBusiness.listar());

		return "projetos/formularioAlterarProjeto";
	}

	@RequestMapping(value = "alterarProjeto", method = RequestMethod.POST)
	public String alterar(ProjetoBean projetoBean) throws BusinessException {
		projetoBusiness.atualizar(projetoBean);

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
		return "forward:listarProjeto";
	}
}