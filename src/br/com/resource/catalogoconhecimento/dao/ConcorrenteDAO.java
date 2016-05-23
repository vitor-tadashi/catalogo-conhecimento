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

	public List<ConcorrenteBean> listar() throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Concorrente where ativo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");

		ResultSet rs = ps.executeQuery();

		List<ConcorrenteBean> concorrentes = new ArrayList<ConcorrenteBean>();

		while (rs.next()) {
			ConcorrenteBean concorrenteBean = new ConcorrenteBean();
			concorrenteBean.setId(rs.getInt("idConcorrente"));
			concorrenteBean.setNome(rs.getString("nomeConcorrente"));
			concorrenteBean.setDescricao(rs.getString("descricao"));

			concorrentes.add(concorrenteBean);
		}

		conexao.close();
		return concorrentes;
	}

	public List<ConcorrenteClienteBean> obterPorId(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT CO.*, CC.valorHora, CL.idCliente, CL.nomeCliente" + " FROM Concorrente AS CO"
				+ " INNER JOIN ConcorrenteCliente AS CC ON CO.idConcorrente = CC.idConcorrente"
				+ " INNER JOIN Cliente AS CL ON CC.idCliente = CL.idCliente" + " WHERE CC.idConcorrente = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		ArrayList<ConcorrenteClienteBean> concorrentesClientes = new ArrayList<ConcorrenteClienteBean>();

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

			concorrentesClientes.add(concorrenteClienteBean);
		}

		conexao.close();
		return concorrentesClientes;
	}

	public List<ConcorrenteClienteBean> obterPorCliente(int id) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT CO.*, CC.valorHora, CL.idCliente, CL.nomeCliente" + " FROM Concorrente AS CO"
				+ " INNER JOIN ConcorrenteCliente AS CC ON CO.idConcorrente = CC.idConcorrente"
				+ " INNER JOIN Cliente AS CL ON CC.idCliente = CL.idCliente" + " WHERE CC.idCliente = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		ArrayList<ConcorrenteClienteBean> concorrentesClientes = new ArrayList<ConcorrenteClienteBean>();

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

			concorrentesClientes.add(concorrenteClienteBean);
		}

		conexao.close();
		return concorrentesClientes;
	}

	public void adicionar(ConcorrenteBean concorrenteBean) throws ClassNotFoundException {

		String sql = "Insert into concorrente(nomeConcorrente,descricao, ativo)values(?,?,?)";
		try {
			Connection conexao = ConnectionFactory.createConnection();
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, concorrenteBean.getNome());
			stmt.setString(2, concorrenteBean.getDescricao());
			stmt.setString(3, "s");

			stmt.executeUpdate();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String SQL = ("Update Concorrente set nomeConcorrente = " + concorrenteBean.getNome()
				+ "descricaoConcorrente = " + concorrenteBean.getDescricao() + "where idConcorrente = "
				+ concorrenteBean.getId());

		PreparedStatement stmt = conexao.prepareStatement(SQL);
		stmt.executeQuery();
		stmt.close();
		conexao.close();
	}

	public void remover(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String deleteSQL = "Update Concorrente set ativo = ? where idConcorrente = ? ";
		PreparedStatement stmt = conexao.prepareStatement(deleteSQL);

		stmt.setString(1, "n");
		stmt.setInt(2, concorrenteBean.getId());
		stmt.executeQuery();

		conexao.close();
	}
}
