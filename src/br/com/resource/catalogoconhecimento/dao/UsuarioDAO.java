package br.com.resource.catalogoconhecimento.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.UsuarioBean;

@Repository
public class UsuarioDAO extends GenericDAOImpl<UsuarioBean, Integer> {

	public List<UsuarioBean> obterPorNome(String nome) throws SQLException, ClassNotFoundException {

		try {
			TypedQuery<UsuarioBean> query = entityManager.createQuery(
					"SELECT u FROM UsuarioBean AS u WHERE u.nome = :nome AND u.ativo = 'S'", UsuarioBean.class);
			List<UsuarioBean> usuarioBean = query.setParameter("nome", nome).getResultList();
			return usuarioBean;
		} catch (Exception e) {
			return null;
		}
	}

	public List<UsuarioBean> listar() {
		try {
			TypedQuery<UsuarioBean> query = entityManager
					.createQuery("SELECT u FROM UsuarioBean AS u WHERE u.ativo = 'S'", UsuarioBean.class);
			List<UsuarioBean> listaUsuario = query.getResultList();
			return listaUsuario;
		} catch (Exception e) {
			return null;
		}
	}

	public UsuarioBean obterPorId(int id) throws SQLException, ClassNotFoundException {

		try {
			TypedQuery<UsuarioBean> query = entityManager.createQuery(
					"SELECT u FROM UsuarioBean AS u WHERE u.id = :id AND u.ativo = 'S'", UsuarioBean.class);
			UsuarioBean usuarioBean = query.setParameter("id", id).getSingleResult();
			return usuarioBean;
		} catch (Exception e) {
			return null;
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

//	@Override
//	public UsuarioBean alterar(UsuarioBean usuario) {
//
//		Query query = entityManager.createQuery(
//				"UPDATE UsuarioBean as u SET u.nome = :nome, u.login = :login, u.senha = :senha WHERE  u.id = :id AND ativo = 'S' ");
//		query.setParameter("id", usuario.getId());
//		query.setParameter("nome", usuario.getNome());
//		query.setParameter("login", usuario.getLogin());
//		query.setParameter("senha", usuario.getSenha());
//		query.executeUpdate();
//		return null;
//	}

}
