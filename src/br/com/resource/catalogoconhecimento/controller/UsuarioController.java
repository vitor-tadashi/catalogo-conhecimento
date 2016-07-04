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

import br.com.resource.catalogoconhecimento.bean.UsuarioBean;
import br.com.resource.catalogoconhecimento.business.UsuarioBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
@RequestMapping("/Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@RequestMapping(value = "formularioAdicionarUsuario", method = RequestMethod.GET)
	public String formularioAdicionar() {
		return "usuario/formularioAdicionarUsuario";
	}

	@RequestMapping(value = "adicionarUsuario", method = RequestMethod.POST)
	public String adiciona(UsuarioBean usuarioBean, @RequestParam("ativo") String ativo) throws BusinessException {
		usuarioBean.setAtivo(ativo.charAt(0));
		usuarioBusiness.inserir(usuarioBean);
		return "redirect:listarCargo";
	}

	@RequestMapping(value = "listarUsuario", method = { RequestMethod.GET, RequestMethod.POST })
	public String listarUsuario(Model model) throws BusinessException {
		model.addAttribute("usuarios", usuarioBusiness.listar());
		return "cargo/listarUsuarios";
	}

	@RequestMapping(value = "formularioAlterarUsuario", method = RequestMethod.GET)
	public String alterar(Model model, @RequestParam("idUsuario") String id) throws BusinessException {
		int idUsuario = Integer.parseInt(id);
		model.addAttribute("usuario", usuarioBusiness.obterPorId(idUsuario));
		return "cargo/formularioAlterarUsuario";
	}

	@RequestMapping(value = "alterarUsuario", method = RequestMethod.POST)
	public String alterar(UsuarioBean usuarioBean) throws BusinessException {
		usuarioBusiness.atualizar(usuarioBean);
		return "redirect:listarUsuario";
	}

	@RequestMapping(value = "excluirUsuario", method = RequestMethod.GET)
	public String excluir(@RequestParam("idUsuario") String id, @RequestParam("ativo") String ativo)
			throws BusinessException {
		int idUsuario = Integer.parseInt(id);
		UsuarioBean usuario = usuarioBusiness.obterPorId(idUsuario);
		usuario.setAtivo(ativo.charAt(0));
		usuarioBusiness.deletar(usuario);
		return "redirect:listarUsuario";
	}

	@RequestMapping(value = "formularioLogarUsuario", method = RequestMethod.GET)
	public String logar(Model model, @RequestParam("idUsuario") String id) throws BusinessException {
		int idUsuario = Integer.parseInt(id);
		model.addAttribute("usuario", usuarioBusiness.obterPorId(idUsuario));
		return "usuario/formularioAlterarUsuario";
	}

	@RequestMapping(value = "logarUsuario", method = RequestMethod.GET)
	public String logar(@RequestParam("idUsuario") String id, @RequestParam("ativo") String ativo)
			throws BusinessException {
		int idUsuario = Integer.parseInt(id);
		UsuarioBean usuario = usuarioBusiness.obterPorId(idUsuario);
		usuario.setAtivo(ativo.charAt(0));
		usuarioBusiness.logar(usuario);
		return "redirect:listarUsuario";
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "forward:listarUsuario";
	}

}
