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

	public void adicionar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "INSERT INTO Cliente(nomeCliente, logradouro, CEP, numero, CNPJ, email, ativo) VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, clienteBean.getNome());
		ps.setString(2, clienteBean.getLogradouro());
		ps.setString(3, clienteBean.getCep());
		ps.setString(4, clienteBean.getNumero());
		ps.setString(5, clienteBean.getCnpj());
		ps.setString(6, clienteBean.getEmail());
		ps.setString(7, String.valueOf('S'));
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public List<ClienteBean> listar() throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cliente WHERE ativo = 'S'";

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

	public void alterar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "UPDATE Cliente SET nomeCliente = ?, logradouro = ?, CEP = ?, numero = ?, CNPJ = ?, email = ?, ativo = ? WHERE idCliente = ?";

		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, clienteBean.getNome());
		ps.setString(2, clienteBean.getLogradouro());
		ps.setString(3, clienteBean.getCep());
		ps.setString(4, clienteBean.getNumero());
		ps.setString(5, clienteBean.getCnpj());
		ps.setString(6, clienteBean.getEmail());
		ps.setString(7, String.valueOf('S'));
		ps.setInt(8, clienteBean.getId());

		ps.executeUpdate();
		conn.close();
	}

	public void remover(ClienteBean clienteBean) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "UPDATE Cliente SET ativo = 'N' WHERE idCliente = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, clienteBean.getId());
		stmt.executeUpdate();
		conn.close();
	}

	public ClienteBean obterPorId(int idCliente) throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Cliente WHERE idCliente = ? AND ativo = 'S'";
		PreparedStatement ps = conn.prepareStatement(sql);
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
	
	public ClienteBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT * FROM Cliente WHERE nomeCliente = ? AND ativo = 'S'";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nome);
		
		ResultSet rs = ps.executeQuery();
		
		ClienteBean clienteBean = null;
		if (rs.next()) {
			clienteBean = new ClienteBean();
			clienteBean.setId(rs.getInt("idCliente"));
			clienteBean.setNome(rs.getString("nomeCliente"));
			clienteBean.setLogradouro(rs.getString("logradouro"));
			clienteBean.setCep(rs.getString("cep"));
			clienteBean.setNumero(rs.getString("numero"));
			clienteBean.setCnpj(rs.getString("cnpj"));
			clienteBean.setEmail(rs.getString("email"));
			clienteBean.setAtivo(rs.getString("ativo").charAt(0));
		}
		
		ps.close();
		conexao.close();
		
		return clienteBean;
	}

	public boolean verificarPorCnpj(String cnpj) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Cliente WHERE cnpj = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cnpj);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return true;
		} else
			return false;
	}

}
