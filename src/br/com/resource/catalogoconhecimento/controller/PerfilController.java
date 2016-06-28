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

import br.com.resource.catalogoconhecimento.bean.PerfilBean;
import br.com.resource.catalogoconhecimento.business.PerfilBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class PerfilController {

	@Autowired
	private PerfilBusiness perfilBusiness;

	@RequestMapping(value = "formularioAdicionarPerfil", method = RequestMethod.GET)
	public String formularioAdicionarCliente(Model model) throws BusinessException {
		model.addAttribute("listaPerfis", perfilBusiness.listar());
		return "perfil/formularioAdicionarPerfil";
	}

	@RequestMapping(value = "adicionarPerfil", method = RequestMethod.POST)
	public String adicionarCliente(PerfilBean perfilBean) throws BusinessException {
		perfilBusiness.adicionar(perfilBean);
		return "redirect:listarPerfis";
	}

	@RequestMapping(value = "formularioAlterarPerfil", method = RequestMethod.GET)
	public String formularioAlterarCliente(Model model, @RequestParam("idPerfil") String id) throws BusinessException {
		int idPerfil = Integer.parseInt(id);
		model.addAttribute("perfil", perfilBusiness.obterPorId(idPerfil));
		return "perfil/formularioAlterarPerfil";
	}

	@RequestMapping(value = "alterarPerfil", method = RequestMethod.POST)
	public String alterarCliente(PerfilBean perfilBean) throws BusinessException {
		perfilBusiness.alterar(perfilBean);
		return "redirect:listarPerfis";
	}

	@RequestMapping(value = "listarPerfis", method = RequestMethod.GET)
	public String listarCliente(Model model) throws BusinessException {
		model.addAttribute("listaPerfis", perfilBusiness.listar());
		return "perfil/listarPerfis";
	}

	@RequestMapping(value = "removerPerfil", method = RequestMethod.GET)
	public String removerCliente(PerfilBean perfilBean) throws BusinessException {
		perfilBusiness.remover(perfilBean);
		return "redirect:listarPerfis";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}

}
