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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
@RequestMapping("busca")
public class BuscaController {

	@Autowired
	private ProjetoBusiness projetoBusiness;

	@Autowired
	private NegocioBusiness negocioBusiness;

	@Autowired
	private FuncionarioBusiness funcionarioBusiness;

	@Autowired
	private TecnologiaBusiness tecnologiaBusiness;
	
	@Autowired
	private EquipeBusiness equipeBusiness;

	@RequestMapping(value = "negocio", method = RequestMethod.POST)
	public String listarPorNegocio(String filtro, Model model) throws BusinessException {

		if (filtro.trim().equals("")) {
			throw new BusinessException("Por favor, preencha o campo de busca!");
		}

		String[] array = filtro.split(",");
		String nomeNegocio = "";

		for (int i = 0; i < array.length; i++) {
			if (negocioBusiness.obterPorNome(array[i]) != null) {
				if (nomeNegocio.isEmpty()) {
					nomeNegocio += "'" + array[i] + "'";
				} else {
					nomeNegocio += ",'" + array[i] + "'";
				}
			}
		}

		if (nomeNegocio.isEmpty()) {
			throw new BusinessException("Pesquisa inválida! Este nome de negócio não existe");
		}
		List<ProjetoBean> projetos = projetoBusiness.obterPorNegocio(nomeNegocio);

		List<FuncionarioBean> listaFuncionario = funcionarioBusiness.listarPorNegocio(nomeNegocio);

		model.addAttribute("projetos", projetos);
		model.addAttribute("funcionarios", listaFuncionario);

		return "/busca/listarBuscaNegocio";
	}

	@RequestMapping(value = "tecnologia", method = RequestMethod.POST)
	public String listarPorTecnologias(String filtro, Model model) throws BusinessException {

		if (filtro.trim().equals("")) {
			throw new BusinessException("Por favor, preencha o campo de busca!");
		}

		String[] array = filtro.split(",");

		String nomeTecnologias = "";

		for (int i = 0; i < array.length; i++) {
			if (tecnologiaBusiness.obterPorNome(array[i]) != null) {
				if (nomeTecnologias.isEmpty()) {
					nomeTecnologias += "'" + array[i] + "'";
				} else {
					nomeTecnologias += ",'" + array[i] + "'";
				}
			}

		}

		if (nomeTecnologias.isEmpty()) {
			throw new BusinessException("Pesquisa inválida! Este nome de tecnologia não existe");
		}

		List<ProjetoBean> projetos = projetoBusiness.obterPorTecnologias(nomeTecnologias);

		List<FuncionarioBean> listaFuncionario = funcionarioBusiness.listarPorTecnologias(nomeTecnologias);

		model.addAttribute("projetos", projetos);
		model.addAttribute("funcionarios", listaFuncionario);

		return "/busca/listarBuscaTecnologia";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}

	@RequestMapping(value = "buscarTecnologiaPorFuncionario", method = RequestMethod.POST)
	public @ResponseBody List<TecnologiaBean> buscarTecnologiaPorFuncionario( @RequestParam("idFuncionario") String id)
			throws BusinessException {
		int idFuncionario = Integer.parseInt(id);
		return tecnologiaBusiness.obterPorFuncionario(idFuncionario);
	}
	
	@RequestMapping(value = "buscarTecnologiaPorProjeto", method = RequestMethod.POST)
	public @ResponseBody List<TecnologiaBean> buscarTecnologiaPorProjeto( @RequestParam("idProjeto") String id)
			throws BusinessException {
		int idProjeto = Integer.parseInt(id);
		
		return tecnologiaBusiness.listarPorProjeto(projetoBusiness.obterPorId(idProjeto));
	}
	
	@RequestMapping(value = "buscarNegocioPorProjeto", method = RequestMethod.POST)
	public @ResponseBody List<NegocioBean> buscarNegocioPorProjeto( @RequestParam("idProjeto") String id)
			throws BusinessException {
		int idProjeto = Integer.parseInt(id);
		
		return negocioBusiness.obterPorProjeto(projetoBusiness.obterPorId(idProjeto));
	}
	
	@RequestMapping(value = "buscarEquipePorProjeto", method = RequestMethod.POST)
	public @ResponseBody List<EquipeBean> buscarEquipePorProjeto( @RequestParam("idProjeto") String id)
			throws BusinessException {
		int idProjeto = Integer.parseInt(id);
		
		return equipeBusiness.obterPorProjeto(idProjeto);
	}
	
	@RequestMapping(value = "buscarFuncionariosPorEquipe", method = RequestMethod.POST)
	public @ResponseBody List<FuncionarioBean> FuncionarioPorEquipe( @RequestParam("idEquipe") String id)
			throws BusinessException {
		int idEquipe = Integer.parseInt(id);
		
		return funcionarioBusiness.listarPorEquipe(idEquipe);
	}
	
	@RequestMapping(value = "buscarEquipePorFuncionario", method = RequestMethod.POST)
	public @ResponseBody List<EquipeBean> EquipePorFuncionario( @RequestParam("idFuncionario") String id)
			throws BusinessException {
		int idFuncionario = Integer.parseInt(id);
		
		return equipeBusiness.obterPorFuncionario(idFuncionario);
	}

}
