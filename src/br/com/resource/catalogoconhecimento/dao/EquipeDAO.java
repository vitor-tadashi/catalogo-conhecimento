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

	// SELECIONAR DADOS NA TABELA DE EQUIPE

	public List<EquipeBean> listar() throws SQLException, ClassNotFoundException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Equipe where ativo = ?";

		PreparedStatement ps = conec.prepareStatement(sql);
		ps.setString(1, "s");

		ResultSet rs = ps.executeQuery();

		ArrayList<EquipeBean> equipes = new ArrayList<EquipeBean>();

		EquipeBean equipe;

		while (rs.next()) {

			int id = rs.getInt("id");
			String observacao = rs.getString("observacao");
			String nome = rs.getString("nome");

			equipe = new EquipeBean(id, observacao, nome);
			equipes.add(equipe);
		}

		conec.close();
		return equipes;
	}

	// INSERIR DADOS NA TABELA DE EQUIPE
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

	// ATUALIZAR DADOS NA TABELA DE EQUIPE

	public void atualizar(EquipeBean equipe) throws ClassNotFoundException, SQLException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "UPDATE Equipe SET nome = ?, observacao = ? WHERE id = ?";

		PreparedStatement stmt = conec.prepareStatement(sql);

		stmt.setString(1, equipe.getNome());
		stmt.setString(2, equipe.getObservacao());
		stmt.setInt(3, equipe.getId());

		stmt.executeUpdate();
		conec.close();

	}

	// DELETAR DADOS NA TABELA DE EQUIPE

	public void deletar(EquipeBean id) throws SQLException, ClassNotFoundException {
		Connection conec = ConnectionFactory.createConnection();
		conec.setAutoCommit(false);

		String sql2 = "DELETE FROM EquipeFuncionario WHERE id= ? ";
		PreparedStatement stmt2 = conec.prepareStatement(sql2);
		stmt2.setInt(1, id.getId());
		stmt2.executeUpdate();

		String sql = "Update Equipe set ativo = ? WHERE id= ?";
		PreparedStatement stmt = conec.prepareStatement(sql);

		stmt.setString(1, "n");
		stmt.setInt(2, id.getId());
		stmt.executeUpdate();

		conec.commit();

	}

	// SELECIONAR DADOS NA TABELA DE EQUIPE PELO ID

	public EquipeBean listarPorId(int id) throws SQLException, ClassNotFoundException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Equipe WHERE id = '" + id + "'";

		PreparedStatement stmt = conec.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		EquipeBean equipe = new EquipeBean();

		while (rs.next()) {

			equipe.setId(rs.getInt("id"));
			equipe.setObservacao(rs.getString("observacao"));
			equipe.setNome(rs.getString("nome"));

		}

		conec.close();
		return equipe;

	}

	public EquipeBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {

		Connection conec = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Equipe WHERE nome = ?";
		PreparedStatement stmt = conec.prepareStatement(sql);

		stmt.setString(1, nome);
		ResultSet rs = stmt.executeQuery();
		
		EquipeBean  equipe = null;

		while (rs.next()) {

			int id = rs.getInt("id");
			String nomeEq = rs.getString("nome");

			EquipeBean oEquipe = new EquipeBean();

			oEquipe.setId(id);
			oEquipe.setNome(nomeEq);

		}
		
		conec.close();
		return equipe;
		

	}

}
