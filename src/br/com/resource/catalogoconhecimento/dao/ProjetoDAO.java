package br.com.resource.catalogoconhecimento.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Repository
public class ProjetoDAO extends GenericDAOImpl<ProjetoBean, Integer> {

	public List<ProjetoBean> listar() {
		TypedQuery<ProjetoBean> query = entityManager.createQuery(
				"SELECT f FROM ProjetoBean AS f WHERE f.ativo = 'S' ORDER BY f.nome ASC", ProjetoBean.class);
		List<ProjetoBean> listaProjetos = query.getResultList();
		return listaProjetos;
	}

	public void remover(int id) throws BusinessException {
		ProjetoBean projetoBean = obterPorId(id);
		projetoBean.setAtivo('N');
		alterar(projetoBean);
	}

	public ProjetoBean obterPorId(int id) throws BusinessException {
		TypedQuery<ProjetoBean> query = entityManager
				.createQuery("SELECT f FROM ProjetoBean f " + " WHERE f.id = :id AND f.ativo = 'S'", ProjetoBean.class);
		query.setParameter("id", id);
		List<ProjetoBean> projetos = query.getResultList();
		if (projetos.isEmpty()) {
			return null;
		} else {
			return projetos.get(0);
		}
	}

	public ProjetoBean obterPorNome(String nome) throws BusinessException {
		TypedQuery<ProjetoBean> query = entityManager.createQuery(
				"SELECT p FROM ProjetoBean as p WHERE p.nome = :nome AND p.ativo = 'S'", ProjetoBean.class);
		query.setParameter("nome", nome);
		List<ProjetoBean> projetos = query.getResultList();
		if (projetos.isEmpty()) {
			return null;
		} else {
			return projetos.get(0);
		}
	}

	public void reativar(String nome) throws BusinessException {
		ProjetoBean projetoBean = obterPorNome(nome);
		if (projetoBean != null) {
			projetoBean.setAtivo('S');
			alterar(projetoBean);
		}
	}

	public List<ProjetoBean> obterPorTecnologias(String nomeTecnologias) throws BusinessException {
		TypedQuery<ProjetoBean> query = entityManager.createQuery(
				"SELECT p FROM ProjetoBean p JOIN p.listaTecnologia t " + "WHERE t.nome IN (" + nomeTecnologias
						+ ") and p.ativo = 'S' and t.ativo = 'S'"
						+ "GROUP BY p.id,p.cliente, p.nome, p.observacao, p.ativo HAVING COUNT(p.id) > 0",
				ProjetoBean.class);
		return query.getResultList();
	}

	public ProjetoBean obterPorNomeDesativado(String nome) throws BusinessException {
		TypedQuery<ProjetoBean> query = entityManager
				.createQuery("SELECT p FROM ProjetoBean WHERE p.nome = :nome AND p.ativo = 'S'", ProjetoBean.class);
		query.setParameter("nome", nome);
		return query.getResultList().get(0);
	}

	public List<ProjetoBean> obterPorNegocio(String nomeNegocio) throws BusinessException {
		TypedQuery<ProjetoBean> query = entityManager.createQuery(
				"SELECT p FROM ProjetoBean p JOIN p.listaNegocio n " + "WHERE n.areaAtuacao IN (" + nomeNegocio
						+ ") and p.ativo = 'S' and n.ativo = 'S'"
						+ "GROUP BY p.id,p.cliente, p.nome, p.observacao, p.ativo HAVING COUNT(p.id) > 0",
				ProjetoBean.class);
		return query.getResultList();
	}

	public List<ProjetoBean> listarPorNomeCliente(String nomeCliente) throws BusinessException {
		TypedQuery<ProjetoBean> query = entityManager
				.createQuery("SELECT p FROM ProjetoBean as p join fetch p.cliente as c"
						+ "WHERE c.nome = :nome AND p.ativo = 'S' AND c.ativo = 'S'", ProjetoBean.class);
		query.setParameter("nome", nomeCliente);
		return query.getResultList();
	}

	public boolean verificarPorEquipe(int id) throws BusinessException {
		TypedQuery<EquipeBean> query = entityManager.createQuery(
				"SELECT e FROM ProjetoBean AS p join p.listaEquipe AS e WHERE p.id = :id", EquipeBean.class);
		query.setParameter("id", id);
		List<EquipeBean> equipes = query.getResultList();
		if (equipes.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean verificarPorNegocio(int id) throws BusinessException {
		TypedQuery<NegocioBean> query = entityManager.createQuery(
				"SELECT n FROM ProjetoBean AS p join p.listaNegocio AS n WHERE p.id = :id", NegocioBean.class);
		query.setParameter("id", id);
		List<NegocioBean> equipes = query.getResultList();
		if (equipes.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean verificarPorTecnologia(int id) throws BusinessException {
		TypedQuery<TecnologiaBean> query = entityManager.createQuery(
				"SELECT n FROM ProjetoBean AS p join p.listaTecnologia AS n WHERE p.id = :id", TecnologiaBean.class);
		query.setParameter("id", id);
		List<TecnologiaBean> equipes = query.getResultList();
		if (equipes.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
