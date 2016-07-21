package br.com.resource.catalogoconhecimento.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
@RequestMapping("concorrente")
public class ConcorrenteController {

	@Autowired
	private ClienteBusiness clienteBusiness;

	@Autowired
	private ConcorrenteBusiness concorrenteBusiness;

	@RequestMapping(value = "formularioAdicionarConcorrente", method = RequestMethod.GET)
	public String formularioAdicionarConcorrente(Model model) throws BusinessException {
		model.addAttribute("listaCliente", clienteBusiness.listar());
		return "concorrente/formularioAdicionarConcorrente";
	}

	@RequestMapping(value = "adicionarConcorrente", method = RequestMethod.POST)
	public String adicionarConcorrente(ConcorrenteBean concorrenteBean, @RequestParam("ativo") String ativo)
			throws BusinessException {
		concorrenteBean.setAtivo(ativo.charAt(0));
		concorrenteBusiness.adicionar(concorrenteBean);

		return "redirect:listarConcorrente";
	}

	@RequestMapping(value = "adicionarClienteNoConcorrente", method = RequestMethod.POST)
	public String adicionarClienteNoConcorrente(Model model, @RequestParam("idConcorrente") String id,
			@RequestParam("cliente") String[] params, @RequestParam("valorConcorrente") String[] valor,
			HttpServletRequest request) throws BusinessException {
		ConcorrenteBean concorrenteBean = concorrenteBusiness.obterPorId(Integer.parseInt(id));
		ClienteBean cliente;
		ConcorrenteClienteBean concorrenteClienteBean;
		List<ConcorrenteClienteBean> listaConcorrenteCliente = new ArrayList<>();
		int i = 0;
		for (String s : params) {
			cliente = clienteBusiness.obterPorNome(s);
			concorrenteClienteBean = new ConcorrenteClienteBean();
			concorrenteClienteBean.setCliente(cliente);
			concorrenteClienteBean.setValorHora(Double.parseDouble(valor[i++]));
			listaConcorrenteCliente.add(concorrenteClienteBean);
		}
		concorrenteBean.setListaClientes(listaConcorrenteCliente);
		if (concorrenteBean.getListaClientes() != null) {
			for (ConcorrenteClienteBean concorrenteCliente : concorrenteBean.getListaClientes()) {
				concorrenteCliente.setCliente(concorrenteCliente.getCliente());
				concorrenteCliente.setConcorrente(concorrenteBean);
				concorrenteBusiness.adicionarConcorrenteCliente(concorrenteCliente);
			}
		}
		request.setAttribute("idConcorrente", concorrenteBean.getId());

		return "forward:listarClientePorConcorrente";
	}

	@RequestMapping(value = "formularioAlterarConcorrente", method = RequestMethod.GET)
	public String formularioAlterarConcorrente(Model model, @RequestParam("idConcorrente") String idConcorrenteParam)
			throws BusinessException {
		int idConcorrente = Integer.parseInt(idConcorrenteParam);
		model.addAttribute("concorrente", concorrenteBusiness.obterPorId(idConcorrente));
		return "concorrente/formularioAlterarConcorrente";
	}

	@RequestMapping(value = "alterarConcorrente", method = RequestMethod.POST)
	public String alterarConcorrente(ConcorrenteBean concorrenteBean) throws BusinessException {
		concorrenteBean.setAtivo('S');
		concorrenteBusiness.alterar(concorrenteBean);
		return "redirect:listarConcorrente";
	}

	@RequestMapping(value = "listarConcorrente", method = { RequestMethod.GET, RequestMethod.POST })
	public String listarConcorrente(Model model) throws BusinessException {
		model.addAttribute("concorrentes", concorrenteBusiness.listar());
		return "concorrente/listarConcorrente";
	}

	@RequestMapping(value = "listarClientePorConcorrente", method = { RequestMethod.GET, RequestMethod.POST })
	public String listarClientePorConcorrente(Model model, @RequestParam("idConcorrente") String id)
			throws BusinessException {

		int idConcorrente = Integer.parseInt(id);
		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteBusiness.listarPorConcorrente(idConcorrente);
		model.addAttribute("listaConcorrenteCliente", listaConcorrenteCliente);
		model.addAttribute("concorrenteBean", concorrenteBusiness.obterPorId(idConcorrente));
		model.addAttribute("listaCliente", clienteBusiness.listar());

		return "concorrente/listarClientePorConcorrente";
	}

	@RequestMapping(value = "removerConcorrente", method = RequestMethod.GET)
	public String removerConcorrente(@RequestParam("idConcorrente") String idConcorrenteParam)
			throws BusinessException {
		int idConcorrente = Integer.parseInt(idConcorrenteParam);
		ConcorrenteBean concorrenteBean = concorrenteBusiness.obterPorId(idConcorrente);
		concorrenteBean.setAtivo('N');

		concorrenteBusiness.remover(concorrenteBean);
		concorrenteBusiness.removerClienteConcorrente(idConcorrente);

		return "redirect:listarConcorrente";
	}

	@RequestMapping(value = "removerClientedoConcorrente", method = RequestMethod.GET)
	public String removerClientedoConcorrente(Model model, @RequestParam("idConcorrente") String idConcorrenteParam,
			@RequestParam("idConcorrenteCliente") String idConcorrenteClienteParam, HttpServletRequest request)
			throws BusinessException {
		int idConcorrente = Integer.parseInt(idConcorrenteParam);
		int idConcorrenteCliente = Integer.parseInt(idConcorrenteClienteParam);
		concorrenteBusiness.removerEntidadeDaLista(idConcorrenteCliente);
		request.setAttribute("idConcorrente", idConcorrente);
		return "forward:listarClientePorConcorrente";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "forward:listarConcorrente";
	}
}
