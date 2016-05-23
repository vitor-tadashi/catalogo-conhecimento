package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class EquipeDAO {

	Connection conec = null;

	// ********************************************************************************************************
	// M�TODO PARA SELECIONAR DADOS NA TABELA DE EQUIPE
	// ********************************************************************************************************
	public List<EquipeBean> listar() throws SQLException, ClassNotFoundException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM CatalogoConhecimentos.dbo.Equipe";

		PreparedStatement ps = conec.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<EquipeBean> equipes = new ArrayList<EquipeBean>();

		EquipeBean equipe;

		while (rs.next()) {

			int idEquipe = rs.getInt("idEquipe");
			String observacao = rs.getString("observacao");
			String nome = rs.getString("nome");

			equipe = new EquipeBean(idEquipe, observacao, nome);
			equipes.add(equipe);
		}

		conec.close();
		return equipes;
	}

	// ********************************************************************************************************
	// M�TODO PARA INSERIR DADOS NA TABELA DE EQUIPE
	// ********************************************************************************************************
	public void inserir(EquipeBean equipe) throws ClassNotFoundException, SQLException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "INSERT INTO CatalogoConhecimentos.dbo.Equipe(observacao,nome) VALUES(?,?)";

		PreparedStatement stmt = conec.prepareStatement(sql);

		stmt.setString(1, equipe.getObservacao());
		stmt.setString(2, equipe.getNome());

		stmt.executeUpdate();
		stmt.close();
		conec.close();

	}

	// ********************************************************************************************************
	// M�TODO PARA ATUALIZAR DADOS NA TABELA DE EQUIPE
	// ********************************************************************************************************
	public void atualizar(EquipeBean equipe) throws ClassNotFoundException, SQLException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "UPDATE Equipe SET nome = ?, observacao = ? WHERE idEquipe = ?";

		PreparedStatement stmt = conec.prepareStatement(sql);

		stmt.setString(1, equipe.getNome());
		stmt.setString(2, equipe.getObservacao());
		stmt.setInt(3, equipe.getIdEquipe());

		stmt.executeUpdate();
		conec.close();

	}

	// ********************************************************************************************************
	// M�TODO PARA DELETAR DADOS NA TABELA DE EQUIPE
	// ********************************************************************************************************
	public void deletar(EquipeBean idEquipe) throws SQLException, ClassNotFoundException {
		Connection conec = ConnectionFactory.createConnection();
		conec.setAutoCommit(false);
		try {

			String sql2 = "DELETE FROM CatalogoConhecimentos.dbo.EquipeFuncionario WHERE idEquipe= ? ";
			PreparedStatement stmt2 = conec.prepareStatement(sql2);
			stmt2.setInt(1, idEquipe.getIdEquipe());
			stmt2.executeUpdate();

			String sql3 = "DELETE FROM CatalogoConhecimentos.dbo.Projeto WHERE idEquipe= ? ";
			PreparedStatement stmt3 = conec.prepareStatement(sql3);
			stmt3.setInt(1, idEquipe.getIdEquipe());
			stmt3.executeUpdate();

			String sql = "DELETE FROM CatalogoConhecimentos.dbo.Equipe  WHERE idEquipe= ?";
			PreparedStatement stmt = conec.prepareStatement(sql);
			stmt.setInt(1, idEquipe.getIdEquipe());
			stmt.executeUpdate();

			conec.commit();

		} catch (Exception e) {
			conec.rollback();
			System.err.println("Problema ao deletar Equipe >>>" + e.getMessage());
		} finally {
			conec.close();
		}

	}

	// ********************************************************************************************************
	// M�TODO PARA SELECIONAR DADOS NA TABELA DE EQUIPE PELO ID
	// ********************************************************************************************************
	public EquipeBean listarPorId(int idEquipe) throws SQLException, ClassNotFoundException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM CatalogoConhecimentos.dbo.Equipe WHERE idEquipe = '" + idEquipe + "'";

		PreparedStatement stmt = conec.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		EquipeBean equipe = new EquipeBean();

		while (rs.next()) {

			equipe.setIdEquipe(rs.getInt("idEquipe"));
			equipe.setObservacao(rs.getString("observacao"));
			equipe.setNome(rs.getString("nome"));

		}

		conec.close();
		return equipe;

	}

}
