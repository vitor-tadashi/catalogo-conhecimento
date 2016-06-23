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
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class ConcorrenteDAO extends GenericDAOImpl<ConcorrenteBean, Integer> {

	public void adicionarConcorrenteCliente(ConcorrenteClienteBean concorrenteClienteBean)
			throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "INSERT INTO ConcorrenteCliente (idCliente, idConcorrente, valorHora) VALUES (?, ?, ?)";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, concorrenteClienteBean.getCliente().getId());
		ps.setInt(2, concorrenteClienteBean.getConcorrente().getId());
		ps.setDouble(3, concorrenteClienteBean.getValorHora());
		ps.executeUpdate();
		ps.close();
		conexao.close();
	}

	public List<ConcorrenteBean> listar() {
		TypedQuery<ConcorrenteBean> query = entityManager
				.createQuery("SELECT c FROM ConcorrenteBean AS c WHERE c.ativo = 'S'", ConcorrenteBean.class);
		List<ConcorrenteBean> listaConcorrentes = query.getResultList();
		return listaConcorrentes;
	}

	public List<ConcorrenteClienteBean> listarPorCliente(int idCliente) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT CO.*, CC.valorHora, CL.idCliente, CL.nomeCliente" + " FROM Concorrente AS CO"
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
			concorrenteClienteBean.setConcorrente(concorrenteBean);
			ClienteBean clienteBean = new ClienteBean();
			clienteBean.setId(rs.getInt("idCliente"));
			clienteBean.setNome(rs.getString("nomeCliente"));
			concorrenteClienteBean.setCliente(clienteBean);
			listaConcorrentesClientes.add(concorrenteClienteBean);
		}
		conn.close();
		return listaConcorrentesClientes;
	}

	public List<ConcorrenteClienteBean> listarPorNomeCliente(String nomeCliente)
			throws ClassNotFoundException, SQLException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT co.idConcorrente, cl.idCliente, cc.valorHora " + "FROM Concorrente AS CO "
				+ "INNER JOIN ConcorrenteCliente AS CC ON CO.idConcorrente = CC.idConcorrente "
				+ "INNER JOIN Cliente AS CL ON CC.idCliente = CL.idCliente "
				+ "WHERE CL.nomeCliente = ? AND co.ativo = 's' AND cl.ativo = 's'";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nomeCliente);
		ResultSet rs = ps.executeQuery();
		ArrayList<ConcorrenteClienteBean> listaConcorrenteCliente = new ArrayList<ConcorrenteClienteBean>();
		while (rs.next()) {
			ConcorrenteClienteBean concorrenteClienteBean = new ConcorrenteClienteBean();
			concorrenteClienteBean.setValorHora(rs.getDouble("valorHora"));
			ConcorrenteBean concorrenteBean = new ConcorrenteBusiness().obterPorId(rs.getInt("idConcorrente"));
			concorrenteClienteBean.setConcorrente(concorrenteBean);
			// ClienteBean clienteBean = new
			// ClienteBusiness().obterPorId(rs.getInt("idCliente"));
			// concorrenteClienteBean.setCliente(clienteBean);
			listaConcorrenteCliente.add(concorrenteClienteBean);
		}

		ps.close();
		conexao.close();

		return listaConcorrenteCliente;
	}

	public ConcorrenteBean obterPorId(int idConcorrente) {
		TypedQuery<ConcorrenteBean> query = entityManager.createQuery(
				"SELECT c FROM ConcorrenteBean AS c WHERE c.id = :id AND c.ativo = 'S'", ConcorrenteBean.class);
		ConcorrenteBean concorrenteBean = query.setParameter("id", idConcorrente).getSingleResult();
		return concorrenteBean;
	}

	public ConcorrenteBean obterPorNome(String nomeConcorrente) {
		try {
			TypedQuery<ConcorrenteBean> query = entityManager.createQuery(
					"SELECT c FROM ConcorrenteBean AS c WHERE c.nome = :nome AND c.ativo = 'S'", ConcorrenteBean.class);
			ConcorrenteBean concorrenteBean = query.setParameter("nome", nomeConcorrente).getSingleResult();
			return concorrenteBean;
		} catch (Exception e) {
			return null;
		}
	}

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
	}
}
