package br.com.resource.catalogoconhecimento.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.resource.catalogoconhecimento.bean.PerfilBean;
import br.com.resource.catalogoconhecimento.bean.UsuarioBean;
import br.com.resource.catalogoconhecimento.business.PerfilBusiness;
import br.com.resource.catalogoconhecimento.business.UsuarioBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.utils.SessionUtil;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioBusiness usuarioBusiness;
	
	@Autowired
	private PerfilBusiness perfilBusiness;

	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
	public String indexLogin(Model model, HttpServletRequest request) throws BusinessException {
		model.addAttribute("perfis", perfilBusiness.listar());
		model.addAttribute("usuario",request.getSession().getAttribute("usuario"));
		return "login/login";
	}

	@RequestMapping(value = "formularioAdicionarUsuario", method = RequestMethod.GET)
	public String formularioAdicionar() {
		return "usuario/formularioAdicionarUsuario";
	}
	
	@RequestMapping(value = "fazerLogoff")
	public String fazerLogoff(HttpServletRequest request){
		request.getSession().removeAttribute("usuario");
		return "redirect:/usuario";
	}

	@RequestMapping(value = "adicionarUsuario", method = RequestMethod.POST)
	public String adiciona(UsuarioBean usuarioBean, @RequestParam("perfil") String id) throws BusinessException {
		int idPerfil = Integer.parseInt(id);
		usuarioBean.setAtivo('S');
		PerfilBean perfilBean = perfilBusiness.obterPorId(idPerfil);
		usuarioBean.setPerfilBean(perfilBean);
		usuarioBusiness.inserir(usuarioBean);
		return "login/login";
	}

	@RequestMapping(value = "listarUsuarios", method = { RequestMethod.GET,RequestMethod.POST })
	public String listarUsuario(Model model) throws BusinessException {
		model.addAttribute("usuarios", usuarioBusiness.listar());
		return "usuarios/listarUsuario";
	}
	
	@RequestMapping(value = "formularioAlterarUsuario", method = RequestMethod.GET)
	public String alterar(Model model,UsuarioBean usuarioBean, @RequestParam("idUsuario") String id) throws BusinessException {
		int idUsuario = Integer.parseInt(id);
		
		model.addAttribute("usuarios", usuarioBusiness.obterPorId(idUsuario));
		model.addAttribute("perfis", perfilBusiness.listar());
		
		return "usuarios/alterarUsuario";
	}

	@RequestMapping(value = "alterarUsuario", method ={RequestMethod.GET, RequestMethod.POST})
	public String alterar(UsuarioBean usuarioBean,@RequestParam("perfil")String idP) throws BusinessException {
		
		int idPerfil = Integer.parseInt(idP);
		PerfilBean perfilBean = perfilBusiness.obterPorId(idPerfil);
		usuarioBean.setPerfilBean(perfilBean);
		usuarioBusiness.atualizar(usuarioBean);
		return "redirect:listarUsuarios";
	}
	
	@RequestMapping(value = "excluirUsuario", method = RequestMethod.GET)
	public String excluir(@RequestParam("idUsuario") String id, @RequestParam("ativo") String ativo)
			throws BusinessException {
		int idUsuario = Integer.parseInt(id);
		UsuarioBean usuario = usuarioBusiness.obterPorId(idUsuario);
		usuario.setAtivo(ativo.charAt(0));
		usuarioBusiness.deletar(usuario);
		return "redirect:listarUsuarios";
	}

	@RequestMapping(value = "logarUsuario", method = RequestMethod.POST)
	public ModelAndView logar(UsuarioBean usuarioBean, HttpServletRequest request) throws BusinessException {
		UsuarioBean usuario = usuarioBusiness.logar(usuarioBean.login, usuarioBean.senha);
		HttpSession session = SessionUtil.getInstance(request);
		session.setAttribute("usuario", usuario);
		return new ModelAndView("redirect:/home/index");
	}
	
	


	@ExceptionHandler(BusinessException.class)
	public String exceptionHandler(Model model, BusinessException exception) {
		model.addAttribute("msgErro", exception.getMessage());
		return "forward:/usuario";
	}

}