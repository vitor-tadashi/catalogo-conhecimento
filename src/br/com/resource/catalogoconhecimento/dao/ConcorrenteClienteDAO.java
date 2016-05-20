package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class ConcorrenteClienteDAO {

	/* m�todo para realizar a inser��o de valor do concorrente */
	public void inserir(ConcorrenteClienteBean concClienteBean) throws ClassNotFoundException {

		String sql = "Insert into CatalogoConhecimentos.dbo.ConcorrenteCliente" + "(idConcorrenteCliente, valorHora)"
				+ "values(?,?)";

		try {
			Connection conexao = ConnectionFactory.createConnection();
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, concClienteBean.getIdConcorrenteCliente());
			stmt.setDouble(2, concClienteBean.getValorHora());

			stmt.executeQuery(sql);
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* m�todo para altera��o de concorrente */
	public void atualizar(ConcorrenteClienteBean concClienteBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String SQL = ("Update CatalogoConhecimentos.dbo.ConcorrenteCliente AS cc Inner Join Concorrente AS conc on cc.IdConcorrenteCliente = conc.IdConcorrente  And Inner join Cliente As cl	on cc.IdConcorrenteCliente = cl.IdCliente order by cc.IdConcorrenteCliente, conc.IdConcorrente,cl.IdCliente");

		PreparedStatement stmt = conexao.prepareStatement(SQL);
		stmt.executeQuery();
		stmt.close();
		conexao.close();
	}

	/* m�todo para excluir concorrente_cliente */
	public void deletar(ConcorrenteClienteBean concClienteBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String deleteSQL = "Delete from CatalogoConhecimentos.dbo.ConcorrenteCliente where idConcorrenteCliente = ? ";
		PreparedStatement stmt = conexao.prepareStatement(deleteSQL);

		stmt.setInt(1, concClienteBean.getIdConcorrenteCliente());
		stmt.executeQuery();

		conexao.close();

	}

	/* m�todo para selecionar concorrente_cliente */
	public List<ConcorrenteClienteBean> listar() throws ClassNotFoundException {

		try {

			Connection conexao = ConnectionFactory.createConnection();
			//String sql = "Select * from CatalogoConhecimentos.dbo.ConcorrenteCliente AS cc Inner Join CatalogoConhecimentos.dbo.Concorrente AS conc on cc.IdConcorrenteCliente = conc.IdConcorrente Inner join CatalogoConhecimentos.dbo.Cliente As cl on cc.IdConcorrenteCliente = cl.IdCliente order by cc.IdConcorrenteCliente, conc.IdConcorrente,cl.IdCliente";

			String sql = "Select * from CatalogoConhecimentos.dbo.ConcorrenteCliente";
			
			
			ArrayList<ConcorrenteClienteBean> concorrentesclientes = new ArrayList<ConcorrenteClienteBean>();
			ConcorrenteClienteBean concorrentescliente;

			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int IdConcorrenteCliente = rs.getInt("idConcorrenteCliente");
				int IdCliente = rs.getInt("idCliente");
				int IdConcorrente = rs.getInt("idConcorrente");
				int Valorhora = rs.getInt("valorhora");

				concorrentescliente = new ConcorrenteClienteBean();
				concorrentescliente.setIdConcorrenteCliente(IdConcorrenteCliente);
				//concorrentescliente.setIdCliente(IdCliente);
				//concorrentescliente.setIdConcorrente(IdConcorrente);
				concorrentescliente.setValorHora(Valorhora);

				concorrentesclientes.add(concorrentescliente);
			}
			conexao.close();

			return concorrentesclientes;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/* m�todo para selecionar por idConcorrenteCliente concorrente_cliente */

	public ConcorrenteClienteBean listarPorId(int id) throws SQLException, ClassNotFoundException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM CatalogoConhecimentos.dbo.ConcorrenteCliente WHERE idConcorrenteCliente = '" + id + "'";

		PreparedStatement stmt = conec.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		ConcorrenteClienteBean concorrentecliente = new ConcorrenteClienteBean();

		while (rs.next()) {

			concorrentecliente.setIdConcorrenteCliente(rs.getInt("idConcorrenteCliente"));
			concorrentecliente.setIdConcorrenteCliente(rs.getInt("idCliente"));
			concorrentecliente.setIdConcorrenteCliente(rs.getInt("idConcorrente"));
			concorrentecliente.setIdConcorrenteCliente(rs.getInt("valorhora"));

		}

		conec.close();
		return concorrentecliente;

	}

}
