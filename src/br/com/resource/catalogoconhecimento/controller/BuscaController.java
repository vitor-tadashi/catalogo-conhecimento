package br.com.resource.catalogoconhecimento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class BuscaController {

	@Autowired
	private ProjetoBusiness projetoBusiness;
	
	@Autowired
	private NegocioBusiness negocioBusiness;
	
	@Autowired
	private FuncionarioBusiness funcionarioBusiness;

	@RequestMapping(value = "listarPorNegocio", method = RequestMethod.POST)
	public String listarPorNegocio(String filtro, Model model) throws BusinessException {

		String [] array = filtro.split(",");
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

		return "busca/listarBuscaNegocio";
	}

}
