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

	/* m�todo para listar o concorrente */
	public List<ConcorrenteClienteBean> listar() throws ClassNotFoundException {
		try {
			Connection conexao = ConnectionFactory.createConnection();
			String sql = "SELECT CO.*, CC.valorHora, CL.idCliente, CL.nomeCliente"
					+ "FROM CatalogoConhecimentos.dbo.Concorrente AS CO"
					+ "INNER JOIN CatalogoConhecimentos.dbo.ConcorrenteCliente AS CC ON CO.idConcorrente = CC.idConcorrente"
					+ "INNER JOIN CatalogoConhecimentos.dbo.Cliente AS CL ON CC.idCliente = CL.idCliente";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ArrayList<ConcorrenteClienteBean> concorrentesClientes = new ArrayList<ConcorrenteClienteBean>();

			while (rs.next()) {
				ConcorrenteClienteBean concorrenteCliente = new ConcorrenteClienteBean();
				concorrenteCliente.setValorhora(rs.getDouble("valorHora"));
				
				ConcorrenteBean concorrente = new ConcorrenteBean();
				concorrente.setIdConcorrente(rs.getInt("idConcorrente"));
				concorrente.setNomeConcorrente(rs.getString("nomeConcorrente"));
				concorrente.setdescricao(rs.getString("descricao"));
				concorrenteCliente.setConcorrente(concorrente);
				
				ClienteBean cliente = new ClienteBean();
				cliente.setId(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nomeCliente"));
				concorrenteCliente.setCliente(cliente);
				
				concorrentesClientes.add(concorrenteCliente);
			}
			ps.close();
			conexao.close();

			return concorrentesClientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* m�todo para realizar a inser��o de concorrente */
	public void inserir(ConcorrenteBean concorrente) throws ClassNotFoundException {

		String sql = "Insert into CatalogoConhecimentos.dbo.concorrente(nomeConcorrente,descricao)values(?,?)";
		try {
			Connection conexao = ConnectionFactory.createConnection();
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setString(1, concorrente.getNomeConcorrente());
			stmt.setString(2, concorrente.getDescricao());

			stmt.executeUpdate();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* m�todo para altera��o de concorrente */
	public void atualizar(ConcorrenteBean altconcorrente) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String SQL = ("Update CatalogoConhecimentos.dbo.Concorrente set nomeConcorrente = " + altconcorrente.getNomeConcorrente()
				+ "descricaoConcorrente = " + altconcorrente.getDescricao() + "where idConcorrente = "
				+ altconcorrente.getIdConcorrente());

		PreparedStatement stmt = conexao.prepareStatement(SQL);
		stmt.executeQuery();
		stmt.close();
		conexao.close();
	}

	/* m�todo para excluir concorrente */
	public void deletar(ConcorrenteBean concorrentes) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String deleteSQL = "Delete from CatalogoConhecimentos.dbo.Concorrente where idConcorrente = ? ";
		PreparedStatement stmt = conexao.prepareStatement(deleteSQL);

		stmt.setInt(1, concorrentes.getIdConcorrente());
		stmt.executeQuery();

		conexao.close();

	}

	/* m�todo para listar por id_concorrente */
	public ConcorrenteBean listarporID(int idConcorrente) throws SQLException, ClassNotFoundException {
		Connection conec = ConnectionFactory.createConnection();

		try {
			String sql = "Select * from CatalogoConhecimentos.dbo.Concorrente where idConcorrente = '" + idConcorrente + "'";

			PreparedStatement stmt = conec.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			ConcorrenteBean concorrenteBean = new ConcorrenteBean();

			while (rs.next()) {

				concorrenteBean.setIdConcorrente(rs.getInt("idConcorrente"));
				concorrenteBean.setNomeConcorrente(rs.getString("nomeConcorrente"));
				concorrenteBean.setdescricao(rs.getString("descricao"));

			}
			conec.close();
			return concorrenteBean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
