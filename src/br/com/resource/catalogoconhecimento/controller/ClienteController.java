package br.com.resource.catalogoconhecimento.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
@RequestMapping("cliente")
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
	public String adicionarCliente(ClienteBean clienteBean, @RequestParam("ativo") String ativo)
			throws BusinessException {
		clienteBean.setAtivo(ativo.charAt(0));
		clienteBusiness.adicionar(clienteBean);

		return "redirect:listarCliente";
	}

	@RequestMapping(value = "adicionarConcorrenteNoCliente", method = RequestMethod.POST)
	public String adicionarConcorrenteNoCliente(Model model, @RequestParam("idCliente") String id,
			HttpServletRequest request, @RequestParam("concorrente") String[] params,
			@RequestParam("valorConcorrente") String[] valor) throws BusinessException {
		ClienteBean clienteBean = clienteBusiness.obterPorId(Integer.parseInt(id));
		ConcorrenteBean concorrente;
		ConcorrenteClienteBean concorrenteClienteBean;
		List<ConcorrenteClienteBean> listaConcorrenteCliente = new ArrayList<>();
		int i = 0;
		for (String s : params) {
			concorrente = concorrenteBusiness.obterPorNome(s);
			concorrenteClienteBean = new ConcorrenteClienteBean();
			concorrenteClienteBean.setConcorrente(concorrente);
			concorrenteClienteBean.setValorHora(Double.parseDouble(valor[i++]));
			listaConcorrenteCliente.add(concorrenteClienteBean);
		}
		clienteBean.setListaConcorrentes(listaConcorrenteCliente);
		if (clienteBean.getListaConcorrentes() != null) {
			for (ConcorrenteClienteBean concorrenteCliente : clienteBean.getListaConcorrentes()) {
				concorrenteCliente.setCliente(clienteBean);
				concorrenteCliente.setConcorrente(concorrenteCliente.getConcorrente());
				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteCliente);
			}
		}
		request.setAttribute("idCliente", clienteBean.getId());
		return "forward:listarConcorrentePorCliente";
	}

	@RequestMapping(value = "formularioAlterarCliente", method = RequestMethod.GET)
	public String formularioAlterarCliente(Model model, @RequestParam("idCliente") String id) throws BusinessException {
		int idCliente = Integer.parseInt(id);
		model.addAttribute("cliente", clienteBusiness.obterPorId(idCliente));
		return "cliente/formularioAlterarCliente";
	}

	@RequestMapping(value = "alterarCliente", method = RequestMethod.POST)
	public String alterarCliente(ClienteBean clienteBean) throws BusinessException {
		clienteBean.setAtivo('S');
		clienteBusiness.alterar(clienteBean);
		return "redirect:listarCliente";
	}

	@RequestMapping(value = "listarCliente", method = { RequestMethod.GET, RequestMethod.POST })
	public String listarCliente(Model model) throws BusinessException {
		model.addAttribute("listaCliente", clienteBusiness.listar());
		return "cliente/listarClientes";
	}

	@RequestMapping(value = "listarConcorrentePorCliente", method = { RequestMethod.GET, RequestMethod.POST })
	public String listarClientePorConcorrente(Model model, @RequestParam("idCliente") String id)
			throws BusinessException {

		int idCliente = Integer.parseInt(id);
		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteBusiness.listarPorCliente(idCliente);
		model.addAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		model.addAttribute("clienteBean", clienteBusiness.obterPorId(idCliente));
		model.addAttribute("listaConcorrente", concorrenteBusiness.listar());

		return "cliente/listarConcorrentePorCliente";
	}

	@RequestMapping(value = "removerCliente", method = RequestMethod.GET)
	public String removerCliente(@RequestParam("idCliente") String idClienteParam) throws BusinessException {
		int idCliente = Integer.parseInt(idClienteParam);
		ClienteBean clienteBean = clienteBusiness.obterPorId(idCliente);
		clienteBean.setAtivo('N');
		
		clienteBusiness.remover(clienteBean);
		concorrenteBusiness.removerConcorrenteCliente(idCliente);
		return "redirect:listarCliente";
	}

	@RequestMapping(value = "removerConcorrenteDoCliente", method = RequestMethod.GET)
	public String removerConcorrenteDoCliente(Model model,
			@RequestParam(value = "idCliente", required = false) String idClienteParam,
			@RequestParam(value = "idConcorrenteCliente", required = false) String idConcorrenteClienteParam,
			HttpServletRequest request) throws BusinessException {

		int idCliente = Integer.parseInt(idClienteParam);
		int idConcorrenteCliente = Integer.parseInt(idConcorrenteClienteParam);
		concorrenteBusiness.removerEntidadeDaLista(idConcorrenteCliente);
		request.setAttribute("idCliente", idCliente);

		return "forward:listarConcorrentePorCliente";
	}

	@ExceptionHandler(BusinessException.class)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "forward:listarCliente";
	}
}
