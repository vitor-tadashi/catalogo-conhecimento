package br.com.resource.catalogoconhecimento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class EquipeController {

	@Autowired
	private EquipeBusiness equipeBusiness;
	
	@Autowired
	private FuncionarioBusiness funcionarioBusiness;

	@RequestMapping(value = "formularioAdicionarEquipe", method = RequestMethod.GET)
	public String formularioAdicionar() {
		return "equipe/adicionaEquipe";
	}

	@RequestMapping(value = "adicionarEquipe", method = RequestMethod.POST)
	public String adicionar(EquipeBean equipe) throws BusinessException {
		equipeBusiness.inserir(equipe);
		return "redirect:listarEquipe";

	}

	public String listarCargo(Model model) throws BusinessException {
		model.addAttribute("equipes", equipeBusiness.listar());
		return "equipe/listarEquipe";
	}

	@RequestMapping(value = "formularioAlterarEquipe", method = RequestMethod.GET)
	public String formularioAlterar(Model model, @RequestParam("idEquipe") String id) throws BusinessException {
		int idEquipe = Integer.parseInt(id);
		model.addAttribute("equipe", equipeBusiness.obterPorId(idEquipe));
		return "equipe/alterarEquipe";
	}

	@RequestMapping(value = "alterarEquipe", method = RequestMethod.POST)
	public String alterarEquipe(EquipeBean equipe) throws BusinessException {

		equipeBusiness.atualizar(equipe);
		return "redirect:listarEquipe";

	}
	
	@RequestMapping(value = "excluriEquipe", method = RequestMethod.POST)
	public String excluir(@RequestParam("idEquipe") String id) throws BusinessException{
		int idEquipe = Integer.parseInt(id);
		equipeBusiness.deletar(idEquipe);
		return "redirect:listarEquipe";
		
	}
	
	@RequestMapping(value = "deletarFuncionarioPorEquipe", method = RequestMethod.POST)
	public String deletarFuncionarioPorEquipe(@RequestParam("idEquipe")String idEq,@RequestParam("idFuncionario")String idFunc, RedirectAttributes redirect) throws BusinessException{
		
		int idEquipe = Integer.parseInt(idEq);
		int idFuncionario = Integer.parseInt(idFunc);
		
		equipeBusiness.deletarPorEquipe(idEquipe, idFuncionario);
		redirect.addFlashAttribute("idEquipe", idEquipe);
		return "redirect:listarFuncionarioPorEquipe";
		
		
	}
	
	public String listarFuncionarioPorEquipe(Model model, @RequestParam("IdEquipe")String idEquipe) throws BusinessException{
		
		
		List<FuncionarioBean> listaFuncionario = funcionarioBusiness.listar();
		List<FuncionarioBean> funcionarioEquipe = funcionarioBusiness.listarPorEquipe(Integer.parseInt(idEquipe));
		EquipeBean equipe = equipeBusiness.obterPorId(Integer.parseInt(idEquipe));
		
		model.addAttribute("funcionarios", listaFuncionario);
		model.addAttribute("funcionarioEquipe", funcionarioEquipe);
		model.addAttribute("equipe", equipe);
		
		return "equipe/listarFuncionariosPorEquipe";
	}
	
	
}
