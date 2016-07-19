package br.com.resource.catalogoconhecimento.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;

@Repository
public class TecnologiaDAO extends GenericDAOImpl<TecnologiaBean, Integer> {

	/**
	 * Listar todas as tecnologias disponíveis
	 * 
	 * @return List<TecnologiaBean>
	 */
	public List<TecnologiaBean> listar() {
		TypedQuery<TecnologiaBean> query = entityManager
				.createQuery("SELECT t FROM TecnologiaBean AS t WHERE t.ativo = 'S'", TecnologiaBean.class);
		List<TecnologiaBean> listaTecnologia = query.getResultList();
		return listaTecnologia;
	}

	/**
	 * Lista todas as tecnologias de um funcionário
	 * 
	 * @param idFuncionario
	 * @return List<TecnologiaBean>
	 */
	public List<TecnologiaBean> listarPorFuncionario(int idFuncionario) {
		TypedQuery<TecnologiaBean> query = entityManager.createQuery(
				"SELECT t FROM FuncionarioBean AS f JOIN f.listaTecnologia AS t WHERE f.id = :id",
				TecnologiaBean.class);
		query.setParameter("id", idFuncionario);
		return query.getResultList();
	}

	/**
	 * Lista todas as tecnologias de um projeto
	 * 
	 * @param projeto
	 * @return List<TecnologiaBean>
	 */
	public List<TecnologiaBean> listarPorProjeto(int idProjeto) {
		TypedQuery<TecnologiaBean> query = entityManager.createQuery(
				"SELECT t FROM ProjetoBean AS p JOIN p.listaTecnologia AS t WHERE p.id = :id", TecnologiaBean.class);
		query.setParameter("id", idProjeto);
		return query.getResultList();
	}

	/**
	 * Pesquisa uma tecnologia pelo seu id
	 * 
	 * @param id
	 * @return TecnologiaBean
	 */
	public TecnologiaBean obterPorId(int id) {
		TypedQuery<TecnologiaBean> query = entityManager.createQuery(
				"SELECT t FROM TecnologiaBean AS t WHERE t.id = :id AND t.ativo = 'S'", TecnologiaBean.class);
		query.setParameter("id", id);
		List<TecnologiaBean> listaTecnologia = query.getResultList();
		if (listaTecnologia.isEmpty()) {
			return null;
		} else {
			return listaTecnologia.get(0);
		}
	}

	/**
	 * Pesquisa uma tecnologia pelo seu nome
	 * 
	 * @param String
	 * @return TecnologiaBean
	 */
	public TecnologiaBean obterPorNome(String nome) {
		TypedQuery<TecnologiaBean> query = entityManager.createQuery(
				"SELECT t FROM TecnologiaBean AS t WHERE t.nome = :nome AND t.ativo = 'S'", TecnologiaBean.class);
		query.setParameter("nome", nome);
		List<TecnologiaBean> listaTecnologia = query.getResultList();
		if (listaTecnologia.isEmpty()) {
			return null;
		} else {
			return listaTecnologia.get(0);
		}
	}
}
