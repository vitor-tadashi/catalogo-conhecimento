package br.com.resource.catalogoconhecimento.dao;

import java.sql.SQLException;
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

	
	// SELECIONAR NA TABELA PROJETO
	public List<ProjetoBean> listar() {
		TypedQuery<ProjetoBean> query = entityManager.createQuery(
				"SELECT f FROM ProjetoBean AS f WHERE f.ativo = 'S' ORDER BY f.nome ASC", ProjetoBean.class);
		List<ProjetoBean> listaProjetos = query.getResultList();
		return listaProjetos;
	}

	// DELETA NA TABELA PROJETO
	public void remover(int id) throws ClassNotFoundException, SQLException {
		ProjetoBean projetoBean = obterPorId(id);
		projetoBean.setAtivo('N');
		alterar(projetoBean);
	}

	/**
	 * Método para obter informações de um projeto por Id
	 * 
	 * @param id
	 * @return Todas informações do projeto
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ProjetoBean obterPorId(Integer id)  {

		try {
			TypedQuery<ProjetoBean> query = entityManager.createQuery(
					"SELECT f FROM ProjetoBean f "
					+ " WHERE f.id = :id AND f.ativo = 'S'", ProjetoBean.class);

			ProjetoBean projetoBean = query.setParameter("id", id).getSingleResult();			
			
			return projetoBean;
		
		} catch (Exception e) {
			return null;
		}
	}

	public ProjetoBean obterPorNome(String nome)
			throws SQLException, ClassNotFoundException {

		try {
			TypedQuery<ProjetoBean> query = entityManager.createQuery(
					"SELECT p FROM ProjetoBean as p WHERE p.nome = :nome AND p.ativo = 'S'", ProjetoBean.class);
			List<ProjetoBean> projetos = query.setParameter("nome", nome).getResultList();
			
			if (projetos.size() > 0)
				return projetos.get(0);
			else
				return null;
		} catch (Exception e) {
			return null;
		}
	}

	public void reativar(String nome) throws SQLException, ClassNotFoundException {
		ProjetoBean projetoBean = obterPorNome(nome);
		
		if (projetoBean != null) {
			projetoBean.setAtivo('S');
			alterar(projetoBean);
		}
	}

	public List<ProjetoBean> obterPorTecnologias(String nomeTecnologias)
			throws SQLException, ClassNotFoundException, BusinessException {
		TypedQuery<ProjetoBean> query = entityManager.createQuery("SELECT p FROM ProjetoBean p JOIN p.listaTecnologia t "
								+ "WHERE t.nome IN ("+nomeTecnologias+") and p.ativo = 'S' and t.ativo = 'S'"
								+ "GROUP BY p.id,p.cliente, p.nome, p.observacao, p.ativo HAVING COUNT(p.id) > 0", ProjetoBean.class);
		return query.getResultList();
	}

	public ProjetoBean obterPorNomeDesativado(String nome) throws ClassNotFoundException, SQLException {
		TypedQuery<ProjetoBean>query = entityManager.createQuery("SELECT p FROM ProjetoBean WHERE p.nome = :nome AND p.ativo = 'S'", ProjetoBean.class);
		query.setParameter("nome", nome);
		
		return query.getResultList().get(0);
	}

	public List<ProjetoBean> obterPorNegocio(String nomeNegocio)
			throws SQLException, ClassNotFoundException, BusinessException {
		TypedQuery<ProjetoBean> query = entityManager.createQuery("SELECT p FROM ProjetoBean p JOIN p.listaNegocio n "
				+ "WHERE n.areaAtuacao IN ("+nomeNegocio+") and p.ativo = 'S' and n.ativo = 'S'"
				+ "GROUP BY p.id,p.cliente, p.nome, p.observacao, p.ativo HAVING COUNT(p.id) > 0", ProjetoBean.class);
			return query.getResultList();

	}

	public List<ProjetoBean> listarPorNomeCliente(String nomeCliente) throws ClassNotFoundException, SQLException {
		
		TypedQuery<ProjetoBean> query = entityManager.createQuery("SELECT p FROM ProjetoBean as p join fetch p.cliente as c"
										+ "WHERE c.nome = :nome AND p.ativo = 'S' AND c.ativo = 'S'", ProjetoBean.class);
		query.setParameter("nome", nomeCliente);
		
		return query.getResultList();
	}

	public boolean verificarPorEquipe(int id) throws ClassNotFoundException, SQLException {
		
		TypedQuery<EquipeBean> query = entityManager.createQuery("SELECT e FROM ProjetoBean AS p join p.listaEquipe AS e WHERE p.id = :id", EquipeBean.class);
		query.setParameter("id", id);
		List<EquipeBean> equipes = query.getResultList();
		
		if(equipes.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	public boolean verificarPorNegocio(int id) throws ClassNotFoundException, SQLException {
		TypedQuery<NegocioBean> query = entityManager.createQuery("SELECT n FROM ProjetoBean AS p join p.listaNegocio AS n WHERE p.id = :id", NegocioBean.class);
		query.setParameter("id", id);
		List<NegocioBean> equipes = query.getResultList();
		
		if(equipes.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	public boolean verificarPorTecnologia(int id) throws ClassNotFoundException, SQLException {
		TypedQuery<TecnologiaBean> query = entityManager.createQuery("SELECT n FROM ProjetoBean AS p join p.listaTecnologia AS n WHERE p.id = :id", TecnologiaBean.class);
		query.setParameter("id", id);
		List<TecnologiaBean> equipes = query.getResultList();
		
		if(equipes.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

}
