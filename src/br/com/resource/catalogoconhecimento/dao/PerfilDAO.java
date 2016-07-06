package br.com.resource.catalogoconhecimento.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.PerfilBean;

@Repository
public class PerfilDAO extends GenericDAOImpl<PerfilBean, Integer> {

	public List<PerfilBean> listar() {
		try {
			TypedQuery<PerfilBean> query = entityManager
					.createQuery("SELECT u FROM PerfilBean AS u WHERE u.ativo = 'S'", PerfilBean.class);
			List<PerfilBean> listaPerfis = query.getResultList();
			return listaPerfis;
		} catch (Exception e) {
			return null;
		}
	}

	public PerfilBean obterPorTipo(String tipo) throws SQLException, ClassNotFoundException {
		try {
			TypedQuery<PerfilBean> query = entityManager.createQuery(
					"SELECT u FROM PerfilBean AS u WHERE u.tipo = :tipo AND u.ativo = 'S'", PerfilBean.class);
			PerfilBean PerfilBean = query.setParameter("tipo", tipo).getSingleResult();
			return PerfilBean;
		} catch (Exception e) {
			return null;
		}
	}

	public PerfilBean obterPorId(int id) throws SQLException, ClassNotFoundException {
		try {
			TypedQuery<PerfilBean> query = entityManager
					.createQuery("SELECT p FROM PerfilBean AS p WHERE p.id = :id AND p.ativo = 'S'", PerfilBean.class);
			PerfilBean PerfilBean = query.setParameter("id", id).getSingleResult();
			return PerfilBean;
		} catch (Exception e) {
			return null;
		}
	}

}