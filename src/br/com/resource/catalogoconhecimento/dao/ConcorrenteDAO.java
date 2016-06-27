package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT CO.*, CC.valorHora, CL.idCliente, CL.nomeCliente FROM Concorrente AS CO"
				+ " INNER JOIN ConcorrenteCliente AS CC ON CO.idConcorrente = CC.idConcorrente"
				+ " INNER JOIN Cliente AS CL ON CC.idCliente = CL.idCliente"
				+ " WHERE CL.idCliente = ? AND CL.ativo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idCliente);
		ps.setString(2, "s");
		ResultSet rs = ps.executeQuery();
		ArrayList<ConcorrenteClienteBean> listaConcorrentesClientes = new ArrayList<ConcorrenteClienteBean>();
		while (rs.next()) {
			ConcorrenteClienteBean concorrenteCliente = new ConcorrenteClienteBean();
			concorrenteCliente.setValorHora(rs.getDouble("valorHora"));
			ConcorrenteBean concorrenteBean = new ConcorrenteBean();
			concorrenteBean.setId(rs.getInt("idConcorrente"));
			concorrenteBean.setNome(rs.getString("nomeConcorrente"));
			concorrenteBean.setDescricao(rs.getString("descricao"));
			concorrenteCliente.setConcorrente(concorrenteBean);
			ClienteBean clienteBean = new ClienteBean();
			clienteBean.setId(rs.getInt("idCliente"));
			clienteBean.setNome(rs.getString("nomeCliente"));
			concorrenteCliente.setCliente(clienteBean);
			listaConcorrentesClientes.add(concorrenteCliente);
		}
		conn.close();
		return listaConcorrentesClientes;
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

		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT CO.*, CC.valorHora, CL.idCliente, CL.nomeCliente FROM Concorrente AS CO"
				+ " INNER JOIN ConcorrenteCliente AS CC ON CO.idConcorrente = CC.idConcorrente"
				+ " INNER JOIN Cliente AS CL ON CC.idCliente = CL.idCliente"
				+ " WHERE CC.idConcorrente = ? AND CO.ativo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, "s");
		ResultSet rs = ps.executeQuery();
		ArrayList<ConcorrenteClienteBean> listaConcorrentesClientes = new ArrayList<ConcorrenteClienteBean>();
		while (rs.next()) {
			ConcorrenteClienteBean concorrenteClienteBean = new ConcorrenteClienteBean();
			concorrenteClienteBean.setValorHora(rs.getDouble("valorHora"));
			ConcorrenteBean concorrenteBean = new ConcorrenteBean();
			concorrenteBean.setId(rs.getInt("idConcorrente"));
			concorrenteBean.setNome(rs.getString("nomeConcorrente"));
			concorrenteBean.setDescricao(rs.getString("descricao"));
			// concorrenteClienteBean.setConcorrente(concorrenteBean);
			ClienteBean clienteBean = new ClienteBean();
			clienteBean.setId(rs.getInt("idCliente"));
			clienteBean.setNome(rs.getString("nomeCliente"));
			// concorrenteClienteBean.setCliente(clienteBean);
			listaConcorrentesClientes.add(concorrenteClienteBean);
		}
		conn.close();
		return listaConcorrentesClientes;
	}

	/**
	 * -JPQL- uma lista de Concorrentes ATIVOS (buscado através do ID passado
	 * como parâmetro)
	 * 
	 * @param idConcorrente
	 * @return List<ConcorrenteBean>
	 */
	public List<ConcorrenteBean> obterPorId(int idConcorrente) {
		TypedQuery<ConcorrenteBean> query = entityManager.createQuery(
				"SELECT c FROM ConcorrenteBean AS c WHERE c.id = :id AND c.ativo = 'S'", ConcorrenteBean.class);
		return query.setParameter("id", idConcorrente).getResultList();
	}

	/**
	 * -JPQL- Retorna uma lista de Concorrentes ATIVOS (buscado através do NOME
	 * passado como parâmetro)
	 * 
	 * @param nomeConcorrente
	 * @return List<ConcorrenteBean>
	 */
	public List<ConcorrenteBean> obterPorNome(String nomeConcorrente) {
		try {
			TypedQuery<ConcorrenteBean> query = entityManager.createQuery(
					"SELECT c FROM ConcorrenteBean AS c WHERE c.nome = :nome AND c.ativo = 'S'", ConcorrenteBean.class);
			return query.setParameter("nome", nomeConcorrente).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * -SQL- Faz a remoção FÍSICA de ConcorrenteCliente na tabela
	 * ConcorrenteCliente
	 * 
	 * @param idCliente
	 * @param idConcorrente
	 * @return void
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void removerConcorrenteCliente(int idCliente, int idConcorrente)
			throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "DELETE FROM ConcorrenteCliente WHERE idCliente = ? AND idConcorrente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idCliente);
		ps.setInt(2, idConcorrente);
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	/**
	 * -SQL- Verifica se o Concorrente está vinculado à algum cliente na tabela
	 * ConcorrenteCliente
	 * 
	 * @param idConcorrente
	 * @return TRUE = Concorrente está vinculado à algum cliente; / FALSE =
	 *         Concorrente não tem vínculos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean verificarPorCliente(int idConcorrente) throws ClassNotFoundException, SQLException {
		Connection conec = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM ConcorrenteCliente WHERE idConcorrente=?";
		PreparedStatement ps = conec.prepareStatement(sql);
		ps.setInt(1, idConcorrente);
		ResultSet rs = ps.executeQuery();
		boolean check = false;
		while (rs.next()) {
			check = true;
		}
		conec.close();
		ps.close();
		return check;
		// Onde está @@@ é o atributo da idConcorrente que está no "Concorrente"
		// dentro de ConcorrenteCliente. Vou ver com o Rodrigo
		/*
		 * TypedQuery<ConcorrenteBean> query = entityManager.createQuery(
		 * "SELECT cc FROM ConcorrenteCliente AS cc WHERE cc.idConcorrente = :@@@@@@@@ AND c.ativo = 'S'"
		 * , ConcorrenteBean.class); ConcorrenteBean concorrenteBean =
		 * query.setParameter("nome", idConcorrente).getSingleResult(); return
		 * concorrenteBean;
		 */
	}
}
