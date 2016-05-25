package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class ConcorrenteDAO {

	public void adicionar(ConcorrenteBean concorrenteBean) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO CONCORRENTE(nomeConcorrente,descricao, ativo) VALUES(?, ?, ?)";
		Connection conn = ConnectionFactory.createConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, concorrenteBean.getNome());
		ps.setString(2, concorrenteBean.getDescricao());
		ps.setString(3, "s");
		ps.executeUpdate();
		ps.close();
		conn.close();
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

	public List<ConcorrenteClienteBean> obterPorCliente(int idCliente) throws ClassNotFoundException, SQLException {
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

	public void atualizar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();
		String SQL = "UPDATE CONCORRENTE SET nomeConcorrente = ?, descricao = ? WHERE idConcorrente = ?";
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.setString(1, concorrenteBean.getNome());
		ps.setString(2, concorrenteBean.getDescricao());
		ps.setInt(3, concorrenteBean.getId());
		ps.executeQuery();
		ps.close();
		conn.close();
	}

	public void remover(int idConcorrente) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();
		String deleteSQL = "UPDATE CONCORRENTE SET ATIVO = ? WHERE idConcorrente = ?";
		PreparedStatement ps = conn.prepareStatement(deleteSQL);
		ps.setString(1, "n");
		ps.setInt(2, idConcorrente);
		ps.executeUpdate();
		conn.close();
	}
}
