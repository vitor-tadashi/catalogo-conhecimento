package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.business.ConcorrenteBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class ConcorrenteDAO {

	public void adicionar(ConcorrenteBean concorrenteBean) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO CONCORRENTE(nomeConcorrente, descricao, ativo) VALUES(?, ?, ?)";
		Connection conn = ConnectionFactory.createConnection();
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, concorrenteBean.getNome());
		ps.setString(2, concorrenteBean.getDescricao());
		ps.setString(3, "S");
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();
		int newId = 0;

		if (rs.next()) {
			newId = rs.getInt(1);
			concorrenteBean.setId(newId);
		}
		ps.close();
		conn.close();
	}

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

	public List<ConcorrenteBean> listar() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Concorrente WHERE ativo = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "s");
		ResultSet rs = ps.executeQuery();
		List<ConcorrenteBean> listaConcorrentes = new ArrayList<ConcorrenteBean>();
		while (rs.next()) {
			ConcorrenteBean concorrenteBean = new ConcorrenteBean();
			concorrenteBean.setId(rs.getInt("idConcorrente"));
			concorrenteBean.setNome(rs.getString("nomeConcorrente"));
			concorrenteBean.setDescricao(rs.getString("descricao"));
			listaConcorrentes.add(concorrenteBean);
		}
		conn.close();
		return listaConcorrentes;
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

	public boolean existe(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Concorrente WHERE nomeConcorrente = ? and ativo  = ?";

		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, concorrenteBean.getNome());
		preparedStatement.setString(2, "n");

		ResultSet resultSet = preparedStatement.executeQuery();

		if (!resultSet.isBeforeFirst()) {
			return false;
		} else {
			return true;
		}
	}

	public ConcorrenteBean obterPorId(int idConcorrente) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM CONCORRENTE WHERE idConcorrente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idConcorrente);
		ResultSet rs = ps.executeQuery();
		ConcorrenteBean concorrenteBean = new ConcorrenteBean();
		while (rs.next()) {
			concorrenteBean.setId(rs.getInt("idConcorrente"));
			concorrenteBean.setNome(rs.getString("nomeConcorrente"));
			concorrenteBean.setDescricao(rs.getString("descricao"));
		}
		conn.close();
		return concorrenteBean;
	}

	public ConcorrenteBean obterPorNome(String nomeConcorrente) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Concorrente WHERE nomeConcorrente = ? AND ativo = 'S'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, nomeConcorrente);
		ResultSet rs = ps.executeQuery();
		ConcorrenteBean concorrenteBean = null;
		while (rs.next()) {
			concorrenteBean = new ConcorrenteBean();
			concorrenteBean.setId(rs.getInt("idConcorrente"));
			concorrenteBean.setNome(rs.getString("nomeConcorrente"));
		}
		ps.close();
		conn.close();
		return concorrenteBean;
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

	public void alterar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();
		String SQL = "UPDATE CONCORRENTE SET nomeConcorrente = ?, descricao = ? WHERE idConcorrente = ?";
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.setString(1, concorrenteBean.getNome());
		ps.setString(2, concorrenteBean.getDescricao());
		ps.setInt(3, concorrenteBean.getId());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public void remover(int idConcorrente) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();
		String deleteSQL = "UPDATE CONCORRENTE SET ATIVO = ? WHERE idConcorrente = ?";
		PreparedStatement ps = conn.prepareStatement(deleteSQL);
		ps.setString(1, "N");
		ps.setInt(2, idConcorrente);
		ps.executeUpdate();
		conn.close();
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

	public void reativar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "UPDATE Concorrente SET descricao = ?, ativo = ? WHERE nomeConcorrente = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, concorrenteBean.getDescricao());
		ps.setString(2, "s");
		ps.setString(3, concorrenteBean.getNome());

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
