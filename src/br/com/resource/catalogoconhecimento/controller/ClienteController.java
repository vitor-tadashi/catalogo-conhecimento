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

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class ClienteController {

	@Autowired
	private ClienteBusiness clienteBusiness;

	@Autowired
	private ConcorrenteBusiness concorrenteBusiness;

	@RequestMapping(value = "formularioAdicionarCliente", method = RequestMethod.GET)
	public String formularioAdicionarCliente(Model model) throws BusinessException {
		model.addAttribute("concorrentes", concorrenteBusiness.listar());
		return "cliente/formularioAdicionarCliente";
	}

	@RequestMapping(value = "adicionarCliente", method = RequestMethod.POST)
	public String adicionarCliente(ClienteBean clienteBean, @RequestParam("ativo")String ativo) throws BusinessException {
		clienteBean.setAtivo(ativo.charAt(0));
		clienteBusiness.adicionar(clienteBean);
		if (clienteBean.getListaConcorrentes() != null) {
			for (ConcorrenteClienteBean concorrenteCliente : clienteBean.getListaConcorrentes()) {
				concorrenteCliente.setIdCliente(clienteBean.getId());
				concorrenteCliente.setIdConcorrente(concorrenteCliente.getConcorrente().getId());
				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteCliente);
			}
		}
		return "redirect:listarCliente";
	}

	@RequestMapping(value = "adicionarConcorrenteNoCliente", method = RequestMethod.POST)
	public String adicionarConcorrenteNoCliente(Model model, ClienteBean clienteBean) throws BusinessException {
		if (clienteBean.getListaConcorrentes() != null) {
			for (ConcorrenteClienteBean concorrenteCliente : clienteBean.getListaConcorrentes()) {
				concorrenteCliente.setIdCliente(clienteBean.getId());
				concorrenteCliente.setIdConcorrente(concorrenteCliente.getConcorrente().getId());
				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteCliente);
			}
		}
		model.addAttribute("listaConcorrenteCliente", concorrenteBusiness.listarPorCliente(clienteBean.getId()));
		model.addAttribute("clienteBean", clienteBean);
		model.addAttribute("listaConcorrente", concorrenteBusiness.listar());
		return "redirect:listarConcorrentePorCliente";
	}

	@RequestMapping(value = "formularioAlterarCliente", method = RequestMethod.GET)
	public String formularioAlterarCliente(Model model, @RequestParam("idCliente") String id) throws BusinessException {
		int idCliente = Integer.parseInt(id);
		model.addAttribute("cliente", clienteBusiness.obterPorId(idCliente));
		return "cliente/formularioAlterarCliente";
	}

	@RequestMapping(value = "alterarCliente", method = RequestMethod.POST)
	public String alterarCliente(ClienteBean clienteBean) throws BusinessException {
		clienteBusiness.alterar(clienteBean);
		return "redirect:listarCliente";
	}

	@RequestMapping(value = "listarCliente", method = RequestMethod.GET)
	public String listarCliente(Model model) throws BusinessException {
		model.addAttribute("listaCliente", clienteBusiness.listar());
		return "cliente/listarClientes";
	}

	@RequestMapping(value = "listarConcorrentePorCliente", method = RequestMethod.GET)
	public String listarClientePorConcorrente(Model model, @RequestParam("idCliente") String id)
			throws BusinessException {

		int idCliente = Integer.parseInt(id);
		model.addAttribute("listaConcorrenteCliente", concorrenteBusiness.listarPorCliente(idCliente));
		model.addAttribute("clienteBean", clienteBusiness.obterPorId(idCliente));
		model.addAttribute("listaConcorrente", concorrenteBusiness.listar());

		return "cliente/listarConcorrentePorCliente";
	}

	@RequestMapping(value = "removerCliente", method = RequestMethod.GET)
	public String removerCliente(@RequestParam("idCliente") String idClienteParam) throws BusinessException {
		int idCliente = Integer.parseInt(idClienteParam);
		clienteBusiness.remover(clienteBusiness.obterPorId(idCliente));
		return "redirect:listarCliente";
	}

	@RequestMapping(value = "removerConcorrenteDoCliente", method = RequestMethod.GET)
	public String removerConcorrenteDoCliente(Model model, @RequestParam("idCliente") String idClienteParam,
			@RequestParam("idConcorrente") String idConcorrenteParam) throws BusinessException {

		int idCliente = Integer.parseInt(idClienteParam);
		int idConcorrente = Integer.parseInt(idConcorrenteParam);

		concorrenteBusiness.removerConcorrenteCliente(idCliente, idConcorrente);

		model.addAttribute("listaConcorrenteCliente", concorrenteBusiness.listarPorCliente(idCliente));
		model.addAttribute("clienteBean", clienteBusiness.obterPorId(idCliente));
		model.addAttribute("listaConcorrente", concorrenteBusiness.listar());

		return "redirect:listarConcorrentePorCliente";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}
}
