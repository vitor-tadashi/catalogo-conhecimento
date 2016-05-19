package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class NegocioDAO {
	
	// CRIA
		public void inserir(NegocioBean negocio) throws ClassNotFoundException, SQLException {
			
			Connection conexao = ConnectionFactory.createConnection();
			
			String sql = "INSERT INTO CatalogoConhecimentos.dbo.Negocio(areaAtuacao) VALUES(?)";
			PreparedStatement st = conexao.prepareStatement(sql);
			
			st.setString(1, negocio.getAreaAtuacao());

			st.executeUpdate();
			st.close();
			conexao.close();
		}

		// LISTA
		public List<NegocioBean> listar() throws SQLException, ClassNotFoundException {
		
			Connection conexao = ConnectionFactory.createConnection();

			String sql = "SELECT * FROM CatalogoConhecimentos.dbo.Negocio";
			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			ArrayList<NegocioBean> negocios = new ArrayList<NegocioBean>();
			NegocioBean negocio;

			while (rs.next()) {
				
				int idNegocio = rs.getInt("idNegocio");
				
				String areaAtuacao = rs.getString("areaAtuacao");
				
				negocio = new NegocioBean(idNegocio, areaAtuacao);
				negocios.add(negocio);
			}

			conexao.close();
			return negocios;
		}

		// ATUALIZA
		public void atualizar(NegocioBean negocio) throws ClassNotFoundException, SQLException {
			Connection conexao = ConnectionFactory.createConnection();
			String sql = "UPDATE CatalogoConhecimentos.dbo.Negocio SET areaAtuacao = '" + negocio.getAreaAtuacao() + "' WHERE idNegocio = ? "
					+ negocio.getIdNegocio();
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.executeUpdate();
			conexao.close();
		}

		// DELETA
		public void deletar(int id) throws SQLException, ClassNotFoundException {
			Connection conexao = ConnectionFactory.createConnection();
			String sql = "DELETE FROM CatalogoConhecimentos.dbo.negocio WHERE idNegocio = ?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			conexao.close();
		}

		// LISTA POR ID
		public NegocioBean listarPorId(int idNegocio) throws SQLException, ClassNotFoundException {
			Connection conexao = ConnectionFactory.createConnection();
			String sql = "SELECT * FROM CatalogoConhecimentos.dbo.Negocio WHERE idNegocio = '" + idNegocio + "'";
			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			NegocioBean negocio = null;
			while (rs.next()) {

				negocio = new NegocioBean();
				negocio.setIdNegocio(rs.getInt("idNegocio"));
				negocio.setAreaAtuacao(rs.getString("areaAtuacao"));
			}
			conexao.close();
			return negocio;
		}
	
		// LISTA POR NOME


public NegocioBean listarPorNome(String nome) throws SQLException, ClassNotFoundException {
	Connection conexao = ConnectionFactory.createConnection();
	String sql = "SELECT * FROM CatalogoConhecimentos.dbo.Negocio WHERE areaAtuacao = '" + nome + "'";
	PreparedStatement ps = conexao.prepareStatement(sql);

	ResultSet rs = ps.executeQuery();

	NegocioBean negocio = null;
	while (rs.next()) {

		negocio = new NegocioBean (rs.getInt("idNegocio"), rs.getString("areaAtuacao"));
	}
	conexao.close();
	return negocio;
}
}
