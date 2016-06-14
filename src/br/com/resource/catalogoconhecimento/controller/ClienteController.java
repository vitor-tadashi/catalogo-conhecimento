package br.com.resource.catalogoconhecimento.controller;

import java.util.List;

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

	@RequestMapping(value = "adicionarConcorrenteNoClienteLogica", method = RequestMethod.POST)
	public String adicionarConcorrenteNoClienteLogica(Model model,
			@RequestParam("countConcorrente") String countConcorrenteParam,
			@RequestParam("idCliente") String idClienteParam, @RequestParam("txtNome") String txtNome,
			@RequestParam("valorHora") String valorHoraParam) throws BusinessException {

		int idCliente = Integer.parseInt(idClienteParam);
		ClienteBean clienteBean = clienteBusiness.obterPorId(idCliente);
		ConcorrenteBean concorrenteBean;
		Integer countConcorrente = Integer.parseInt(countConcorrenteParam);

		for (int i = 0; i <= countConcorrente; i++) {
			String nomeConcorrente = txtNome + i;
			if (nomeConcorrente != null) {
				concorrenteBean = concorrenteBusiness.obterPorNome(nomeConcorrente);
				ConcorrenteClienteBean concorrenteClienteBean = new ConcorrenteClienteBean();
				concorrenteClienteBean.setCliente(clienteBean);
				concorrenteClienteBean.setConcorrente(concorrenteBean);
				concorrenteClienteBean.setValorHora(Integer.parseInt(valorHoraParam + i));
				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteClienteBean);
			}
		}

		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteBusiness.listarPorCliente(idCliente);
		List<ConcorrenteBean> listaConcorrente = concorrenteBusiness.listar();

		model.addAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		model.addAttribute("clienteBean", clienteBean);
		model.addAttribute("listaConcorrente", listaConcorrente);

		return "redirect:listarConcorrentePorCliente";
	}

	@RequestMapping(value = "formularioAlterarCliente", method = RequestMethod.GET)
	public String formularioAlterarCliente(Model model, @RequestParam("id") String id) throws BusinessException {
		int idCliente = Integer.parseInt(id);
		model.addAttribute("cliente", clienteBusiness.obterPorId(idCliente));
		return "cliente/formularioAlterarCliente";
	}

	@RequestMapping(value = "alterarCliente", method = RequestMethod.POST)
	public String alterarCliente(ClienteBean clienteBean) throws BusinessException {
		clienteBusiness.alterar(clienteBean);
		return "redirect:listarClientes";
	}

	@RequestMapping(value = "listarCliente", method = RequestMethod.GET)
	public String listarCliente(Model model) throws BusinessException {
		model.addAttribute("listaCliente", clienteBusiness.listar());
		return "cliente/listarClientes";
	}

	@RequestMapping(value = "listarClientePorConcorrente", method = RequestMethod.GET)
	public String listarClientePorConcorrente(Model model, @RequestParam("idCliente") String id) throws BusinessException {
		int idCliente = Integer.parseInt(id);

		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteBusiness.listarPorCliente(idCliente);
		ClienteBean clienteBean = clienteBusiness.obterPorId(idCliente);
		List<ConcorrenteBean> listaConcorrente = concorrenteBusiness.listar();

		model.addAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		model.addAttribute("clienteBean", clienteBean);
		model.addAttribute("listaConcorrente", listaConcorrente);

		return "cliente/listarConcorrentePorCliente";
	}

	@RequestMapping(value = "removerCliente", method = RequestMethod.GET)
	public String removerCliente(ClienteBean clienteBean) throws BusinessException {
		clienteBusiness.remover(clienteBean);
		return "redirect:listarClientes";
	}

	@RequestMapping(value = "RemoverConcorrenteDoClienteLogica", method = RequestMethod.GET)
	public String RemoverConcorrenteDoClienteLogica(Model model, @RequestParam("idCliente") String idClienteParam,
			@RequestParam("idConcorrente") String idConcorrenteParam) throws BusinessException {

		int idCliente = Integer.parseInt(idClienteParam);
		int idConcorrente = Integer.parseInt(idConcorrenteParam);

		concorrenteBusiness.removerConcorrenteCliente(idCliente, idConcorrente);

		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteBusiness.listarPorCliente(idCliente);
		List<ConcorrenteBean> listaConcorrente = concorrenteBusiness.listar();
		ClienteBean clienteBean = clienteBusiness.obterPorId(idCliente);

		model.addAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		model.addAttribute("clienteBean", clienteBean);
		model.addAttribute("listaConcorrente", listaConcorrente);

		return "redirect:listarConcorrentePorCliente";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "index";
	}
}
