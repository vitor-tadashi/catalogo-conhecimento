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
@RequestMapping("tecnologia")
public class TecnologiaController {
	
	@Autowired
	TecnologiaBusiness tecnologiaBusiness;
	
	@RequestMapping(value = "adicionar", method = RequestMethod.GET)
	public String formularioAdicionar() {
		return "tecnologias/adicionar";	
	}
	
	@RequestMapping(value = "adicionar", method = RequestMethod.POST)
	public String adicionar(TecnologiaBean tecnologiaBean, @RequestParam("ativo")String ativo) throws BusinessException {
		tecnologiaBean.setNome(tecnologiaBean.getNome().trim());
		tecnologiaBean.setAtivo(ativo.charAt(0));
		tecnologiaBusiness.adicionar(tecnologiaBean);
		return "redirect:listar";
	}
	
	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public String listarTecnologia(Model model) throws BusinessException {
		model.addAttribute("tecnologias", tecnologiaBusiness.listar());
		return "tecnologias/listar";
	}
	
	@RequestMapping(value = "alterar", method = RequestMethod.GET)
	public String formularioAlterar(Model model, @RequestParam("idTecnologia") String id) throws BusinessException {
		int idTecnologia = Integer.parseInt(id);
		model.addAttribute("tecnologia", tecnologiaBusiness.obterPorId(idTecnologia));
		return "tecnologias/alterar";
	}
	
	@RequestMapping(value = "alterar", method = RequestMethod.POST)
	public String alterar(TecnologiaBean tecnologiaBean) throws BusinessException {
		tecnologiaBean.setNome(tecnologiaBean.getNome().trim());
		tecnologiaBusiness.alterar(tecnologiaBean);
		return "redirect:listar";
	}
	
	@RequestMapping(value = "remover", method = RequestMethod.GET)
	public String remover(@RequestParam("idTecnologia") String id, @RequestParam("ativo") String ativo) throws BusinessException {
		int idTecnologia = Integer.parseInt(id);
		TecnologiaBean tecnologiaBean = tecnologiaBusiness.obterPorId(idTecnologia);
		tecnologiaBean.setAtivo(ativo.charAt(0));
		tecnologiaBusiness.remover(tecnologiaBean);
		return "redirect:listar";		
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}

}
