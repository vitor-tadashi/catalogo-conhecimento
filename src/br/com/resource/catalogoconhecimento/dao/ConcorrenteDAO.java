package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class ConcorrenteDAO extends GenericDAOImpl<ConcorrenteBean, Integer> {

	/**
	 * -JPQL- Retorna uma lista com todos os Concorrentes ATIVOS da tabela de
	 * Concorrentes
	 * 
	 * @param no
	 *            parameters
	 * @return listaConcorrente (retorna a lista de concorrentes)
	 */
	public List<ConcorrenteBean> listar() {
		TypedQuery<ConcorrenteBean> query = entityManager
				.createQuery("SELECT c FROM ConcorrenteBean AS c WHERE c.ativo = 'S'", ConcorrenteBean.class);
		List<ConcorrenteBean> listaConcorrente = query.getResultList();
		return listaConcorrente;
	}

	/**
	 * -SQL- Retorna uma lista com todos os Concorrentes relacionados ao Cliente
	 * (se estiver ativo) e o respectivo valor/hora
	 * 
	 * @param idCliente
	 * @return listaConcorrentesClientes - Retorna uma lista contendo objetos
	 *         ConcorrenteCliente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ConcorrenteClienteBean> listarPorCliente(int idCliente) throws ClassNotFoundException, SQLException {
		
		  TypedQuery<ConcorrenteClienteBean> query = entityManager.createQuery(
		  "SELECT cc FROM ConcorrenteClienteBean AS cc WHERE cc.cliente.id = :id AND cc.cliente.ativo = 'S'", ConcorrenteClienteBean.class); 
		  query.setParameter("id", idCliente);
		  
		  return query.getResultList();
		 
	}

	/**
	 * -SQL- Retorna uma lista com todos os Clientes relacionados ao Concorrente
	 * (se estiver ativo) e o respectivo valor/hora
	 * 
	 * @param idConcorrente
	 * @return listaConcorrentesClientes - Retorna uma lista contendo objetos
	 *         ConcorrenteCliente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<ConcorrenteClienteBean> listarPorConcorrente(int id) throws SQLException, ClassNotFoundException {

		  TypedQuery<ConcorrenteClienteBean> query = entityManager.createQuery(
		  "SELECT cc FROM ConcorrenteClienteBean AS cc WHERE cc.concorrente.id = :id AND cc.concorrente.ativo = 'S'", ConcorrenteClienteBean.class); 
		  query.setParameter("id", id);
		  
		  return query.getResultList();

	}

	/**
	 * -JPQL- uma lista de Concorrentes ATIVOS (buscado através do ID passado
	 * como parâmetro)
	 * 
	 * @param idConcorrente
	 * @return List<ConcorrenteBean>
	 */
	public ConcorrenteBean obterPorId(int idConcorrente) {
		TypedQuery<ConcorrenteBean> query = entityManager.createQuery(
				"SELECT c FROM ConcorrenteBean AS c WHERE c.id = :id AND c.ativo = 'S'", ConcorrenteBean.class);
		List<ConcorrenteBean> concorrentes = query.setParameter("id", idConcorrente).getResultList();
		if (concorrentes.isEmpty()) {
			return null;
		} else {
			return concorrentes.get(0);
		}
	}

	/**
	 * -JPQL- Retorna uma lista de Concorrentes ATIVOS (buscado através do NOME
	 * passado como parâmetro)
	 * 
	 * @param nomeConcorrente
	 * @return List<ConcorrenteBean>
	 */
	public ConcorrenteBean obterPorNome(String nomeConcorrente) {
		TypedQuery<ConcorrenteBean> query = entityManager.createQuery(
				"SELECT c FROM ConcorrenteBean AS c WHERE c.nome = :nome AND c.ativo = 'S'", ConcorrenteBean.class);
		List<ConcorrenteBean> concorrentes = query.setParameter("nome", nomeConcorrente).getResultList();
		if (concorrentes.isEmpty()) {
			return null;
		} else {
			return concorrentes.get(0);
		}
	}

	/**
	 * -JPQL- Faz a remoção FÍSICA de um Concorrente relacionado ao Cliente
	 * atual (Remoção de ConcorrenteCliente na tabela ConcorrenteCliente)
	 * 
	 * @param idCliente
	 * @param idConcorrente
	 * @return void
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void removerEntidadeDaLista(int idConcorrenteCliente) {
		Query query = entityManager
				.createQuery("DELETE FROM ConcorrenteClienteBean AS c WHERE c.id = :idConcorrenteCliente");
		query.setParameter("idConcorrenteCliente", idConcorrenteCliente);

		query.executeUpdate();
	}

	/**
	 * -JPQL- Faz a remoção FÍSICA de todos os Concorrentes relacionados ao
	 * Cliente atual na tabela ConcorrenteCliente
	 * 
	 * @param idCliente
	 * @param idConcorrente
	 * @return void
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void removerConcorrenteCliente(int idCliente) {
		Query query = entityManager
				.createQuery("DELETE FROM ConcorrenteClienteBean AS c WHERE c.idCliente = :idCliente");
		query.setParameter("idCliente", idCliente);

		query.executeUpdate();
	}

	/**
	 * -JPQL- Faz a remoção FÍSICA de todos os Clientes relacionados ao
	 * Concorrente atual na tabela ConcorrenteCliente
	 * 
	 * @param idCliente
	 * @param idConcorrente
	 * @return void
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void removerClienteConcorrente(int idConcorrente) {
		Query query = entityManager
				.createQuery("DELETE FROM ConcorrenteClienteBean AS c WHERE c.idConcorrente = :idConcorrente");
		query.setParameter("idConcorrente", idConcorrente);

		query.executeUpdate();
	}

	public ConcorrenteClienteBean obterPorConcorrente(int id) {
		TypedQuery<ConcorrenteClienteBean> query = entityManager.createQuery(
				"SELECT c FROM ConcorrenteClienteBean AS c WHERE c.idConcorrente = :id", ConcorrenteClienteBean.class);
		List<ConcorrenteClienteBean> concorrentes = query.setParameter("id", id).getResultList();
		if (concorrentes.isEmpty()) {
			return null;
		} else {
			return concorrentes.get(0);
		}

	}

}
