package br.com.resource.catalogoconhecimento.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import br.com.resource.catalogoconhecimento.bean.PerfilBean;

public class PerfilDAO extends GenericDAOImpl<PerfilBean, Integer> {
	
	public List<PerfilBean> obterPorTipo(String tipo) throws SQLException, ClassNotFoundException {

		try {
			TypedQuery<PerfilBean> query = entityManager.createQuery(
					"SELECT u FROM PerfilBean AS u WHERE u.nome = :nome AND u.ativo = 'S'", PerfilBean.class);
			List<PerfilBean> PerfilBean = query.setParameter("tipo", tipo).getResultList();
			return PerfilBean;
		} catch (Exception e) {
			return null;
		}
	}

	public List<PerfilBean> listar() {
		try {
			TypedQuery<PerfilBean> query = entityManager
					.createQuery("SELECT e FROM EquipeBean AS e WHERE e.ativo = 'S'", PerfilBean.class);
			List<PerfilBean> listaPerfis = query.getResultList();
			return listaPerfis;
		} catch (Exception e) {
			return null;
		}
	}

	public PerfilBean obterPorId(int id) throws SQLException, ClassNotFoundException {

		try {
			TypedQuery<PerfilBean> query = entityManager
					.createQuery("SELECT u FROM PerfilBean AS u WHERE u.id = :id AND u.ativo = 'S'", PerfilBean.class);
			PerfilBean PerfilBean = query.setParameter("idPerfil", id).getSingleResult();
			return PerfilBean;
		} catch (Exception e) {
			return null;
		}
	}

}
