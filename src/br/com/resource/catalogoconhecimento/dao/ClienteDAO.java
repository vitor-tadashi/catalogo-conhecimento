package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class ClienteDAO {

	Connection conn = null;

	public void inserir(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "INSERT INTO Cliente(nomeCliente, logradouro, CEP, numero, CNPJ, email, ativo) VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, clienteBean.getNome());
		ps.setString(2, clienteBean.getLogradouro());
		ps.setString(3, clienteBean.getCep());
		ps.setString(4, clienteBean.getNumero());
		ps.setString(5, clienteBean.getCnpj());
		ps.setString(6, clienteBean.getEmail());
		ps.setString(7, String.valueOf(clienteBean.getAtivo()));
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public List<ClienteBean> listar() throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cliente";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<ClienteBean> listaClientes = new ArrayList<ClienteBean>();
		ClienteBean clienteBean;

		while (rs.next()) {
			clienteBean = new ClienteBean();
			clienteBean.setId(rs.getInt("idCliente"));
			clienteBean.setNome(rs.getString("nomeCliente"));
			clienteBean.setLogradouro(rs.getString("logradouro"));
			clienteBean.setCep(rs.getString("cep"));
			clienteBean.setNumero(rs.getString("numero"));
			clienteBean.setCnpj(rs.getString("cnpj"));
			clienteBean.setEmail(rs.getString("email"));
			clienteBean.setAtivo(rs.getString("ativo").charAt(0));
			listaClientes.add(clienteBean);
		}
		conn.close();
		return listaClientes;
	}

	public void atualizar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "UPDATE Cliente SET nomeCliente = ?, logradouro = ?, CEP = ?, numero = ?, CNPJ = ?, email = ?, ativo = ? WHERE idCliente = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, clienteBean.getNome());
		ps.setString(2, clienteBean.getLogradouro());
		ps.setString(3, clienteBean.getCep());
		ps.setString(4, clienteBean.getNumero());
		ps.setString(5, clienteBean.getCnpj());
		ps.setString(6, clienteBean.getEmail());
		ps.setString(7, String.valueOf(clienteBean.getAtivo()));
		ps.setInt(8, clienteBean.getId());
		ps.executeUpdate();
		conn.close();
	}

	public void deletar(ClienteBean clienteBean) throws SQLException, ClassNotFoundException {

		Connection conn = ConnectionFactory.createConnection();
		conn.setAutoCommit(false);

		try {

			String sql1 = "DELETE FROM CatalogoConhecimentos.dbo.ConcorrenteCliente WHERE idCliente= ? ";
			PreparedStatement stmt1 = conn.prepareStatement(sql1);
			stmt1.setInt(1, clienteBean.getId());
			stmt1.executeUpdate();

			String sql2 = "DELETE FROM CatalogoConhecimentos.dbo.Projeto WHERE idCliente= ? ";
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, clienteBean.getId());
			stmt2.executeUpdate();

			String sql = "DELETE FROM CatalogoConhecimentos.dbo.Cliente  WHERE idCliente= ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, clienteBean.getId());
			stmt.executeUpdate();

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			System.out.println("--- Erro ao deletar cliente ---" + e.getMessage());
		} finally {
			conn.close();
		}
	}

	public ClienteBean obterPorId(int idCliente) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();
		String sqlSelecionar = "SELECT * FROM Cliente WHERE idCliente = ?";
		PreparedStatement ps = conn.prepareStatement(sqlSelecionar);
		ps.setInt(1, idCliente);

		ResultSet rs = ps.executeQuery();
		ClienteBean clienteBean = new ClienteBean();

		if (rs.next()) {

			clienteBean.setId(rs.getInt("idCliente"));
			clienteBean.setNome(rs.getString("nomeCliente"));
			clienteBean.setLogradouro(rs.getString("logradouro"));
			clienteBean.setCep(rs.getString("cep"));
			clienteBean.setNumero(rs.getString("numero"));
			clienteBean.setCnpj(rs.getString("cnpj"));
			clienteBean.setEmail(rs.getString("email"));
			clienteBean.setAtivo(rs.getString("ativo").charAt(0));
			conn.close();
		}

		return clienteBean;
	}

}
