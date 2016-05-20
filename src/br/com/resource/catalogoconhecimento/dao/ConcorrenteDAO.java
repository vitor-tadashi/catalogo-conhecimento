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

	public List<ConcorrenteClienteBean> listar() throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT CO.*, CC.valorHora, CL.idCliente, CL.nomeCliente"
				+ "FROM Concorrente AS CO"
				+ "INNER JOIN ConcorrenteCliente AS CC ON CO.idConcorrente = CC.idConcorrente"
				+ "INNER JOIN Cliente AS CL ON CC.idCliente = CL.idCliente";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<ConcorrenteClienteBean> concorrentesClientes = new ArrayList<ConcorrenteClienteBean>();

		while (rs.next()) {
			ConcorrenteClienteBean concorrenteCliente = new ConcorrenteClienteBean();
			concorrenteCliente.setValorHora(rs.getDouble("valorHora"));

			ConcorrenteBean concorrente = new ConcorrenteBean();
			concorrente.setId(rs.getInt("idConcorrente"));
			concorrente.setNome(rs.getString("nomeConcorrente"));
			concorrente.setdescricao(rs.getString("descricao"));
			concorrenteCliente.setConcorrente(concorrente);

			ClienteBean cliente = new ClienteBean();
			cliente.setId(rs.getInt("idCliente"));
			cliente.setNome(rs.getString("nomeCliente"));
			concorrenteCliente.setCliente(cliente);

			concorrentesClientes.add(concorrenteCliente);
		}
		
		conexao.close();

		return concorrentesClientes;
	}

	public ConcorrenteBean obterPorId(int idConcorrente) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "Select * from CatalogoConhecimentos.dbo.Concorrente where idConcorrente = '" + idConcorrente
				+ "'";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		ConcorrenteBean concorrente = new ConcorrenteBean();

		while (rs.next()) {
			concorrente.setId(rs.getInt("idConcorrente"));
			concorrente.setNome(rs.getString("nomeConcorrente"));
			concorrente.setdescricao(rs.getString("descricao"));
		}

		conexao.close();
		return concorrente;
	}

	public List<ConcorrenteClienteBean> obterPorCliente(int idCliente) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT CO.*, CC.valorHora, CL.idCliente, CL.nomeCliente"
				+ " FROM Concorrente AS CO"
				+ " INNER JOIN ConcorrenteCliente AS CC ON CO.idConcorrente = CC.idConcorrente"
				+ " INNER JOIN Cliente AS CL ON CC.idCliente = CL.idCliente"
				+ " WHERE CL.idCliente = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idCliente);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<ConcorrenteClienteBean> concorrentesClientes = new ArrayList<ConcorrenteClienteBean>();
		
		while (rs.next()) {
			ConcorrenteClienteBean concorrenteCliente = new ConcorrenteClienteBean();
			concorrenteCliente.setValorHora(rs.getDouble("valorHora"));

			ConcorrenteBean concorrente = new ConcorrenteBean();
			concorrente.setId(rs.getInt("idConcorrente"));
			concorrente.setNome(rs.getString("nomeConcorrente"));
			concorrente.setdescricao(rs.getString("descricao"));
			concorrenteCliente.setConcorrente(concorrente);

			ClienteBean cliente = new ClienteBean();
			cliente.setId(rs.getInt("idCliente"));
			cliente.setNome(rs.getString("nomeCliente"));
			concorrenteCliente.setCliente(cliente);

			concorrentesClientes.add(concorrenteCliente);
		}
		
		conexao.close();
		return concorrentesClientes;
	}

	public void inserir(ConcorrenteBean concorrente) throws ClassNotFoundException {

		String sql = "Insert into CatalogoConhecimentos.dbo.concorrente(nomeConcorrente,descricao)values(?,?)";
		try {
			Connection conexao = ConnectionFactory.createConnection();
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, concorrente.getNome());
			stmt.setString(2, concorrente.getDescricao());

			stmt.executeUpdate();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizar(ConcorrenteBean altconcorrente) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String SQL = ("Update CatalogoConhecimentos.dbo.Concorrente set nomeConcorrente = "
				+ altconcorrente.getNome() + "descricaoConcorrente = " + altconcorrente.getDescricao()
				+ "where idConcorrente = " + altconcorrente.getId());

		PreparedStatement stmt = conexao.prepareStatement(SQL);
		stmt.executeQuery();
		stmt.close();
		conexao.close();
	}

	public void deletar(ConcorrenteBean concorrentes) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String deleteSQL = "Delete from CatalogoConhecimentos.dbo.Concorrente where idConcorrente = ? ";
		PreparedStatement stmt = conexao.prepareStatement(deleteSQL);

		stmt.setInt(1, concorrentes.getId());
		stmt.executeQuery();

		conexao.close();

	}

}
