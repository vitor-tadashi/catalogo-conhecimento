package br.com.resource.catalogoconhecimento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String adicionarCliente(ClienteBean clienteBean,
			@RequestParam("countConcorrente") String countConcorrenteParam, @RequestParam("txtNome") String txtNome,
			@RequestParam("valorHora") String valorHoraParam) throws BusinessException {

		clienteBusiness.adicionar(clienteBean);

		int countConcorrente = Integer.parseInt(countConcorrenteParam);
		ConcorrenteBean concorrenteBean;
		ConcorrenteClienteBean concorrenteClienteBean;

		for (int i = 0; i <= countConcorrente; i++) {
			String nomeConcorrente = txtNome + i;
			if (nomeConcorrente != null) {
				concorrenteBean = concorrenteBusiness.obterPorNome(nomeConcorrente);
				concorrenteClienteBean = new ConcorrenteClienteBean();
				concorrenteClienteBean.setCliente(clienteBean);
				concorrenteClienteBean.setConcorrente(concorrenteBean);
				concorrenteClienteBean.setValorHora(Integer.parseInt(valorHoraParam + i));
				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteClienteBean);
			}
		}
		return "redirect:listarClientes";
	}

	@RequestMapping(value = "formularioAlterarCliente", method = RequestMethod.GET)
	public String formularioAlterarCliente(Model model, @RequestParam("id") String id) throws BusinessException {
		int idCliente = Integer.parseInt(id);
		model.addAttribute("cliente", clienteBusiness.obterPorId(idCliente));
		return "cliente/formularioAlterarCliente";
	}

	@RequestMapping(value = "alterarCliente", method = RequestMethod.PUT)
	public String alterarCliente(ClienteBean clienteBean) throws BusinessException {
		clienteBusiness.alterar(clienteBean);
		return "redirect:listarClientes";
	}

	@RequestMapping(value = "listarCliente", method = RequestMethod.GET)
	public String listarCliente(Model model) throws BusinessException {
		model.addAttribute("listaCliente", clienteBusiness.listar());
		return "cliente/listarClientes";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}
}
