package br.com.resource.catalogoconhecimento.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.UsuarioBean;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Repository
public class UsuarioDAO extends GenericDAOImpl<UsuarioBean, Integer> {

	public List<UsuarioBean> obterPorNome(String nome) throws BusinessException {
		TypedQuery<UsuarioBean> query = entityManager.createQuery(
				"SELECT u FROM UsuarioBean AS u WHERE u.nome = :nome AND u.ativo = 'S'", UsuarioBean.class);
		query.setParameter("nome", nome);
		List<UsuarioBean> listaUsuario = query.getResultList();
		if (listaUsuario.isEmpty()) {
			return null;
		} else {
			return listaUsuario;
		}
	}

	public List<UsuarioBean> listar() {
		TypedQuery<UsuarioBean> query = entityManager.createQuery("SELECT u FROM UsuarioBean AS u WHERE u.ativo = 'S'",
				UsuarioBean.class);
		List<UsuarioBean> listaUsuario = query.getResultList();
		if (listaUsuario.isEmpty()) {
			return null;
		} else {
			return listaUsuario;
		}
	}

	public UsuarioBean obterPorId(int id) throws BusinessException {
		TypedQuery<UsuarioBean> query = entityManager
				.createQuery("SELECT u FROM UsuarioBean AS u WHERE u.id = :id AND u.ativo = 'S'", UsuarioBean.class);
		query.setParameter("id", id);
		List<UsuarioBean> listaUsuario = query.getResultList();
		if (listaUsuario.isEmpty()) {
			return null;
		} else {
			return listaUsuario.get(0);
		}
	}

	public List<UsuarioBean> login(String login, String senha) {
		TypedQuery<UsuarioBean> query = entityManager.createQuery(
				"SELECT u FROM UsuarioBean AS u WHERE u.login = :login AND u.senha = :senha", UsuarioBean.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		List<UsuarioBean> listaUsuario = query.getResultList();
		return listaUsuario;
	}

	public UsuarioBean obterPorLogin(String login, String senha) {
		TypedQuery<UsuarioBean> query = entityManager.createQuery(
				"SELECT u FROM UsuarioBean AS u WHERE u.login = :login AND u.senha = :senha AND u.ativo ='S'",
				UsuarioBean.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		List<UsuarioBean> listaUsuario = query.getResultList();
		if (listaUsuario.isEmpty()) {
			return null;
		} else {
			return listaUsuario.get(0);
		}
	}

}
