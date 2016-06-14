package br.com.resource.catalogoconhecimento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

public class ClienteController {

	@Autowired
	private ClienteBusiness clienteBusiness;

	@Autowired
	private ConcorrenteBusiness concorrenteBusiness;

	@RequestMapping(value = "formularioAdicionarCliente", method = RequestMethod.GET)
	public String formularioAdicionar(Model model) throws BusinessException {
		model.addAttribute("concorrentes", concorrenteBusiness.listar());
		return "cliente/formularioAdicionarCliente";
	}

	@RequestMapping(value = "adicionarCliente", method = RequestMethod.POST)
	public String adiciona(ClienteBean clienteBean) throws BusinessException {
		clienteBusiness.adicionar(clienteBean);

		// Integer countConcorrente =
		// Integer.parseInt(request.getParameter("countConcorrente"));
		// ConcorrenteBean concorrenteBean;
		// ConcorrenteClienteBean concorrenteClienteBean;
		// ConcorrenteBusiness concorrenteBusiness = new ConcorrenteBusiness();
		// for (int i = 0; i <= countConcorrente; i++) {
		// String nomeConcorrente = request.getParameter("txtNome" + i);
		// if (nomeConcorrente != null) {
		// concorrenteBean = concorrenteBusiness.obterPorNome(nomeConcorrente);
		// concorrenteClienteBean = new ConcorrenteClienteBean();
		// concorrenteClienteBean.setCliente(clienteBean);
		// concorrenteClienteBean.setConcorrente(concorrenteBean);
		// concorrenteClienteBean.setValorHora(Integer.parseInt(request.getParameter("valorHora"
		// + i)));
		//
		// concorrenteBusiness.adicionarConcorrenteCliente(concorrenteClienteBean);
		// }
		// }

		return "redirect:listarClinete";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}
}
