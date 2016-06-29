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
					.createQuery("SELECT e FROM EquipeBean AS e WHERE e.ativo = 'S'", UsuarioBean.class);
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

}
