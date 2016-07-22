package br.com.resource.catalogoconhecimento.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.PerfilBean;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

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

	public PerfilBean obterPorTipo(String tipo) throws BusinessException {
		TypedQuery<PerfilBean> query = entityManager
				.createQuery("SELECT u FROM PerfilBean AS u WHERE u.tipo = :tipo AND u.ativo = 'S'", PerfilBean.class);
		query.setParameter("tipo", tipo);
		List<PerfilBean> listaPerfil = query.getResultList();
		if (listaPerfil.isEmpty()) {
			return null;
		} else {
			return listaPerfil.get(0);
		}
	}

	public PerfilBean obterPorId(int id) throws BusinessException {
		TypedQuery<PerfilBean> query = entityManager
				.createQuery("SELECT p FROM PerfilBean AS p WHERE p.id = :id AND p.ativo = 'S'", PerfilBean.class);
		query.setParameter("id", id);
		List<PerfilBean> listaPerfil = query.getResultList();
		if (listaPerfil.isEmpty()) {
			return null;
		} else {
			return listaPerfil.get(0);
		}
	}

}
