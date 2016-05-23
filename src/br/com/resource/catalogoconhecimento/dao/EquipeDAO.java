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

		String sql = "SELECT * FROM Equipe where ativo = ?";

		PreparedStatement ps = conec.prepareStatement(sql);
		ps.setString(1, "s");

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

		String sql = "INSERT INTO Equipe(observacao,nome, ativo) VALUES(?,?, ?)";

		PreparedStatement stmt = conec.prepareStatement(sql);

		stmt.setString(1, equipe.getObservacao());
		stmt.setString(2, equipe.getNome());
		stmt.setString(3, "s");
		
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
		

			String sql2 = "DELETE FROM EquipeFuncionario WHERE idEquipe= ? ";
			PreparedStatement stmt2 = conec.prepareStatement(sql2);
			stmt2.setInt(1, idEquipe.getIdEquipe());
			stmt2.executeUpdate();

			

			String sql = "Update Equipe set ativo = ? WHERE idEquipe= ?";
			PreparedStatement stmt = conec.prepareStatement(sql);
			
			stmt.setString(1, "n");
			stmt.setInt(2, idEquipe.getIdEquipe());
			stmt.executeUpdate();

			conec.commit();

	

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
