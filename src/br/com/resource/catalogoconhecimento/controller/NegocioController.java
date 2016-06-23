package br.com.resource.catalogoconhecimento.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class NegocioController {

	@Autowired
	private NegocioBusiness negocioBusiness;

	@RequestMapping(value = "formularioAdicionarNegocio", method = RequestMethod.GET)
	public String formularioAdicionar() {
		return "negocios/formularioAdicionarNegocio";
	}

	@RequestMapping(value = "adicionarNegocio", method = RequestMethod.POST)
	public String adiciona(NegocioBean negocioBean, @RequestParam("ativo")String ativo) throws BusinessException {
			negocioBean.setAreaAtuacao(negocioBean.getAreaAtuacao().trim());
			negocioBean.setAtivo(ativo.charAt(0)); 
			negocioBusiness.adicionar(negocioBean); 
		return "redirect:listarNegocio";
	}

	@RequestMapping(value = "listarNegocio", method = RequestMethod.GET)
	public String listarNegocio(Model model) throws BusinessException {

		model.addAttribute("negocio", negocioBusiness.listar());
		return "negocios/listarNegocio";
	}

	@RequestMapping(value = "formularioAlterarNegocio", method = RequestMethod.GET)
	public String alterar(Model model, @RequestParam("idNegocio") String id) throws BusinessException {
		int idNegocio = Integer.parseInt(id);
		model.addAttribute("negocio", negocioBusiness.obterPorId(idNegocio));
		return "negocios/formularioAlterarNegocio";
	}

	@RequestMapping(value = "alterarNegocio", method = RequestMethod.POST)
	public String alterar(NegocioBean negocioBean, @RequestParam("id") String id, @RequestParam("ativo")String ativo) throws BusinessException {
		negocioBean.setAreaAtuacao(negocioBean.getAreaAtuacao().trim());
		negocioBean.setAtivo(ativo.charAt(0)); 
		negocioBusiness.alterar(negocioBean);
		return "redirect:listarNegocio";
	}

	@RequestMapping(value = "removerNegocio", method = RequestMethod.GET)
	public String remover( @RequestParam("idNegocio") String id, @RequestParam("ativo") String ativo, HttpServletRequest request) throws BusinessException {
		int idNegocio = Integer.parseInt(id);
		NegocioBean negocio = negocioBusiness.obterPorId(idNegocio);
		negocio.setAtivo(ativo.charAt(0));
		negocioBusiness.remover(negocio);		
		return "redirect:listarNegocio";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "forward:listarNegocio";
	}
}
