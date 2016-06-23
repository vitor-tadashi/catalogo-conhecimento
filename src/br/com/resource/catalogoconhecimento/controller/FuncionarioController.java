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

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioNegocioBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaFuncionarioBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
@RequestMapping(value = "funcionario")
public class FuncionarioController {

	@Autowired
	FuncionarioBusiness funcionarioBusiness;

	@Autowired
	TecnologiaBusiness tecnologiaBusiness;

	@Autowired
	CargoBusiness cargoBusiness;

	@Autowired
	NegocioBusiness negocioBusiness;
	
	@Autowired
	TecnologiaFuncionarioBusiness funcionariotecnologia;
	
	@Autowired
	FuncionarioNegocioBusiness funcionarioNegocio;
	
	@Autowired
	EquipeBusiness equipeBusiness;

	@RequestMapping(value = "formularioAdicionarFuncionario", method = RequestMethod.GET)
	public String formularioAdicionar(Model model) throws BusinessException {
		List<TecnologiaBean> listaTecnologia = tecnologiaBusiness.listar();
		List<CargoBean> listaCargo = cargoBusiness.listar();
		List<NegocioBean> listaNegocio = negocioBusiness.listar();

		model.addAttribute("tecnologias", listaTecnologia);
		model.addAttribute("cargos", listaCargo);
		model.addAttribute("negocios", listaNegocio);
		return "funcionarios/formularioAdicionar";
	}

	@RequestMapping(value = "listarFuncionarios", method = RequestMethod.GET)
	public String listar(Model model) throws BusinessException {
		model.addAttribute("listaFuncionario", funcionarioBusiness.listar());
		return "funcionarios/listarFuncionarios";
	}
	
	

	@RequestMapping(value = "adicionarFuncionario", method = RequestMethod.POST)
	public String adicionarFuncionario(FuncionarioBean funcionarioBean) throws BusinessException {
		
		funcionarioBusiness.adicionar(funcionarioBean);
		funcionariotecnologia.adicionar(funcionarioBean, funcionarioBean.getListaTecnologia());
		funcionarioNegocio.adicionar(funcionarioBean, funcionarioBean.getListaNegocio());
		
		return "redirect:listarFuncionarios";

	}
	
	@RequestMapping(value = "formularioAlterarFuncionario", method = RequestMethod.GET)
	public String formularioAlterar(Model model, @RequestParam("idFuncionario") String id) throws BusinessException{
		List<TecnologiaBean> listaTecnologia = tecnologiaBusiness.listar();
		List<CargoBean> listaCargo = cargoBusiness.listar();
		List<NegocioBean> listaNegocio = negocioBusiness.listar();
		int idFuncionario = Integer.parseInt(id);

		model.addAttribute("funcionario", funcionarioBusiness.obterPorId(idFuncionario));
		model.addAttribute("tecnologias", listaTecnologia);
		model.addAttribute("cargos", listaCargo);
		model.addAttribute("negocios", listaNegocio);
		
		return "funcionarios/formularioAlterar";
	}
	

	
	@RequestMapping(value = "alterarFuncionario", method = RequestMethod.POST)
	public String alterar(FuncionarioBean funcionarioBean) throws BusinessException{
		funcionarioBusiness.alterar(funcionarioBean);
		funcionariotecnologia.atualizar(funcionarioBean, funcionarioBean.getListaTecnologia());
		funcionarioNegocio.atualizar(funcionarioBean, funcionarioBean.getListaNegocio());
		
		return "redirect:listarFuncionarios";
	}
	
	@RequestMapping(value = "removerFuncionario", method = RequestMethod.GET)
	public String remover(@RequestParam("idFuncionario") String id) throws BusinessException{
		int idFuncionario = Integer.parseInt(id);
		FuncionarioBean funcionarioBean = funcionarioBusiness.obterPorId(idFuncionario);
		funcionarioBusiness.remover(funcionarioBean);
		
		return "redirect:listarFuncionarios";
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "redirect:listarFuncionarios";
	}
}
