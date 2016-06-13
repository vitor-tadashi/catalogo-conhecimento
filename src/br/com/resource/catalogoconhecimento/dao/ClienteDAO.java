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
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class ClienteDAO {

	Connection conn = null;

	public void adicionar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "INSERT INTO Cliente(nomeCliente, logradouro, CEP, numero, CNPJ, email, ativo) VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, clienteBean.getNome());
		ps.setString(2, clienteBean.getLogradouro());
		ps.setString(3, clienteBean.getCep());
		ps.setString(4, clienteBean.getNumero());
		ps.setString(5, clienteBean.getCnpj());
		ps.setString(6, clienteBean.getEmail());
		ps.setString(7, "S");
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		int newId = 0;

		if (rs.next()) {
			newId = rs.getInt(1);
			clienteBean.setId(newId);
		}

		ps.close();
		conn.close();
	}

	public List<ClienteBean> listar() throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cliente WHERE ativo = 'S'";

		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<ClienteBean> listaClientes = new ArrayList<ClienteBean>();
		ClienteBean clienteBean = null;

		while (rs.next()) {
			clienteBean = new ClienteBean();
			clienteBean.setId(rs.getInt("idCliente"));
			clienteBean.setNome(rs.getString("nomeCliente"));
			clienteBean.setLogradouro(rs.getString("logradouro"));
			clienteBean.setCep(rs.getString("cep"));
			clienteBean.setNumero(rs.getString("numero"));
			clienteBean.setCnpj(rs.getString("cnpj"));
			clienteBean.setEmail(rs.getString("email"));
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
		}

		ps.close();
		conexao.close();

		return clienteBean;
	}

	public ClienteBean obterNomeDesativado(ClienteBean clienteBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Cliente WHERE nomeCliente = ? AND ativo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, clienteBean.getNome());
		ps.setString(2, "n");
		ResultSet rs = ps.executeQuery();

		ClienteBean cliente = null;

		while (rs.next()) {
			int id = rs.getInt("idCliente");
			String nomeCliente = rs.getString("nomeCliente");
			cliente = new ClienteBean();
			cliente.setId(id);
			cliente.setNome(nomeCliente);
		}
		conexao.close();
		return cliente;
	}

	public boolean verificarPorCnpj(String cnpj) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Cliente WHERE cnpj = ? AND ativo = 'S'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cnpj);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return true;
		} else
			return false;
	}

	public void reativar(ClienteBean clienteBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Cliente SET logradouro = ?, CEP = ?, numero = ?, CNPJ = ?, email = ?, ativo = ? WHERE nomeCliente = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ps.setString(1, clienteBean.getLogradouro());
		ps.setString(2, clienteBean.getCep());
		ps.setString(3, clienteBean.getNumero());
		ps.setString(4, clienteBean.getCnpj());
		ps.setString(5, clienteBean.getEmail());
		ps.setString(6, "S");
		ps.setString(7, clienteBean.getNome());
		ps.executeUpdate();
		conexao.close();
	}

}
