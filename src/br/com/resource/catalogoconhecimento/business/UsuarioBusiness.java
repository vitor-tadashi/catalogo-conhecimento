package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.UsuarioBean;
import br.com.resource.catalogoconhecimento.dao.UsuarioDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class UsuarioBusiness {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Transactional
	public void inserir(UsuarioBean usuarioBean) throws BusinessException {

		try {
			UsuarioBean usuarioIgual = (UsuarioBean) usuarioDAO.obterPorNome(usuarioBean.getNome().trim());
			if (usuarioIgual != null && usuarioIgual.getId() != usuarioBean.getId()) {
				throw new NomeRepetidoException("Este usuario já consta na base de dados");
			} else if (usuarioBean.getNome().length() > 500) {
				throw new TamanhoCampoException("Número limite de caracteres excedido(máxs.500)");
			} else {
				usuarioDAO.adicionar(usuarioBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<UsuarioBean> listar() throws BusinessException {
		try {
			List<UsuarioBean> listaUsuario = usuarioDAO.listar();

			if (listaUsuario.isEmpty()) {
				throw new ConsultaNulaException("Não há usuários cadastrados");
			} else {
				return listaUsuario;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void atualizar(UsuarioBean usuario) throws BusinessException {
		try {

			UsuarioBean usuarioIgual = (UsuarioBean) usuarioDAO.obterPorNome(usuario.getNome().trim());

			 if (usuarioIgual != null && usuarioIgual.getId() != usuario.getId()) {
				throw new NomeRepetidoException("Este nome já consta na base de dados");
			} else if (usuario.getNome().length() > 50) {
				throw new TamanhoCampoException("Número limite de caracteres excedido(máx.500)");
			} else {
				usuarioDAO.alterar(usuario);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public UsuarioBean obterPorId(int id) throws BusinessException {
		try {
			
			return usuarioDAO.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void deletar(UsuarioBean usuario) throws BusinessException {
		try{
//		if (equipeDAO.verificarPorFuncionarios(equipe)) {
			usuarioDAO.remover(usuario);
//		} else {
//			throw new RegistroVinculadoException(
//					"Essa Equipe n�o pode ser removida, pois possui vínculos com Funcion�rios");
//		}
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}
	}

	public void logar(UsuarioBean usuario) {
		// TODO Auto-generated method stub
		
	}

	
}
