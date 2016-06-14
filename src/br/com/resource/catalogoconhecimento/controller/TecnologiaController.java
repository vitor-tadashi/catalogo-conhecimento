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

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class TecnologiaController {
	
	@Autowired
	TecnologiaBusiness tecnologiaBusiness;
	
	
	@RequestMapping(value = "formularioAdicionarTecnologia", method = RequestMethod.GET)
	public String formularioAdicionar(){	
		return "tecnologias/formularioAdicionarTecnologia";	
	}
	
	@RequestMapping(value = "adcionarTecnologia", method = RequestMethod.POST)
	public String adicionar(TecnologiaBean tecnologiaBean) throws BusinessException{
		tecnologiaBusiness.adicionar(tecnologiaBean);
		
		return "redirect:listarTecnologia";
	}
	
	@RequestMapping(value = "listarTecnologia", method = RequestMethod.GET)
	public String listarTecnologia(Model model) throws BusinessException{
		
		model.addAttribute("tecnologias", tecnologiaBusiness.listar());
		
		return "tecnologias/listarTecnologia";
	}
	
	@RequestMapping(value = "formularioAlterarTecnologia", method = RequestMethod.GET)
	public String alterar(Model model, @RequestParam("idTecnologia") String id) throws BusinessException{
		int idTecnologia = Integer.parseInt(id);
		model.addAttribute("tecnologia", tecnologiaBusiness.obterPorId(idTecnologia));
		
		return "tecnologias/formularioAlterarTecnologia";
	}
	
	@RequestMapping(value = "alterarTecnologia", method = RequestMethod.POST)
	public String alterar(TecnologiaBean tecnologiaBean) throws BusinessException{
		tecnologiaBusiness.alterar(tecnologiaBean);
		return "redirect:listarTecnologia";
	}
	
	@RequestMapping(value = "excluirTecnologia", method = RequestMethod.GET)
	public String excluir(@RequestParam("idTecnologia") String id) throws BusinessException{
		int idTecnologia = Integer.parseInt(id);
		tecnologiaBusiness.remover(idTecnologia);
		return "redirect:listarTecnologia";		
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}

}
