package br.com.resource.catalogoconhecimento.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
@RequestMapping("equipe")
public class EquipeController {

	@Autowired
	private EquipeBusiness equipeBusiness;

	@Autowired
	private FuncionarioBusiness funcionarioBusiness;
	
	@Autowired
	private TecnologiaBusiness tecnologiaBusiness;

	@RequestMapping(value = "formularioAdicionarEquipe", method = RequestMethod.GET)
	public String formularioAdicionar() {
		return "equipe/adicionarEquipe";
	}

	@RequestMapping(value = "adicionarEquipe", method = RequestMethod.POST)
	public String adicionar(EquipeBean equipebean, @RequestParam("ativo")String ativo) throws BusinessException{
		equipebean.setAtivo(ativo.charAt(0));
		equipeBusiness.inserir(equipebean);
		return "redirect:listarEquipe";
	}

	@RequestMapping(value = "listarEquipe", method = { RequestMethod.GET, RequestMethod.POST})
	public String listarEquipe(Model model) throws BusinessException {
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
	public String alterarEquipe(EquipeBean equipe, @RequestParam("idEquipe") String id) throws BusinessException {
		equipe.setId(Integer.parseInt(id));
		equipeBusiness.atualizar(equipe);
		return "redirect:listarEquipe";

	}

	@RequestMapping(value = "excluirEquipe", method = RequestMethod.GET)
	public String excluir(@RequestParam("idEquipe") String id) throws BusinessException {
		int idEquipe = Integer.parseInt(id);
		equipeBusiness.deletar(idEquipe);
		return "redirect:listarEquipe";

	}

	@RequestMapping(value = "deletarFuncionarioPorEquipe", method = RequestMethod.GET)
	public String deletarFuncionarioPorEquipe(@RequestParam("idEquipe") String idEq,
			@RequestParam("idFuncionario") String idFunc, HttpServletRequest request) throws BusinessException {

		int idEquipe = Integer.parseInt(idEq);
		int idFuncionario = Integer.parseInt(idFunc);

		equipeBusiness.deletarPorEquipe(idEquipe, idFuncionario);
		request.setAttribute("idEquipe", idEq);

		return "forward:listarFuncionarioPorEquipe";

	}

	@RequestMapping(value = "listarFuncionarioPorEquipe", method = { RequestMethod.GET, RequestMethod.POST })
	public String listarFuncionarioPorEquipe(Model model, @RequestParam("idEquipe") String idEquipe) throws BusinessException {

		List<FuncionarioBean> listaFuncionario = funcionarioBusiness.listar();
		List<FuncionarioBean> funcionarioEquipe = funcionarioBusiness.listarPorEquipe(Integer.parseInt(idEquipe));
		EquipeBean equipe = equipeBusiness.obterPorId(Integer.parseInt(idEquipe));

		model.addAttribute("funcionarios", listaFuncionario);
		model.addAttribute("funcionarioEquipe", funcionarioEquipe);
		model.addAttribute("equipe", equipe);

		return "equipe/listarFuncionariosPorEquipe";
	}

	@RequestMapping(value = "adicionarFuncionarioNaEquipe", method = RequestMethod.POST)
	public String adicionarFuncionarioNaEquipe(@RequestParam("idEquipe") String idEq,
			@RequestParam("idFuncionario") String idFunc, HttpServletRequest request) throws Exception {

		int idEquipe = Integer.parseInt(idEq);
		int idFuncionario = Integer.parseInt(idFunc);

		equipeBusiness.inserirPorEquipe(idEquipe, idFuncionario);
		request.setAttribute("idEquipe", idEq);

		return "forward:listarFuncionarioPorEquipe";

	}
	
	@ExceptionHandler(BusinessException.class)
	public String exceptionHandler(BusinessException exception, Model model){
		model.addAttribute("msgErro", exception.getMessage());
		return "forward:listarEquipe";
	}
	
	@RequestMapping(value = "buscarTecnologiaPorFuncionario", method = RequestMethod.POST)
	public @ResponseBody List<TecnologiaBean> buscarTecnologiaPorFuncionario( @RequestParam("idFuncionario") String id)
			throws BusinessException {
		int idFuncionario = Integer.parseInt(id);
		return tecnologiaBusiness.obterPorFuncionario(idFuncionario);
	}

}
