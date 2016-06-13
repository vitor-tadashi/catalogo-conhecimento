
package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.EquipeFuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class EquipeDAO {

	Connection conec = null;

	// SELECIONAR DADOS NA TABELA DE EQUIPE

	public List<EquipeBean> listar() throws SQLException, ClassNotFoundException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Equipe where ativo = ?";

		PreparedStatement ps = conec.prepareStatement(sql);
		ps.setString(1, "s");

		ResultSet rs = ps.executeQuery();

		List<EquipeBean> listaEquipe = new ArrayList<EquipeBean>();
		while (rs.next()) {
			EquipeBean equipeBean = new EquipeBean();
			equipeBean.setId(rs.getInt("idEquipe"));
			equipeBean.setNome(rs.getString("nome"));
			equipeBean.setObservacao(rs.getString("observacao"));

			listaEquipe.add(equipeBean);
		}

		conec.close();
		return listaEquipe;
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

		String sql = "UPDATE Equipe SET nome = ?, observacao = ? WHERE idEquipe = ?";

		PreparedStatement stmt = conec.prepareStatement(sql);
		if(equipe.getObservacao().trim().equals("")){
			equipe.setObservacao("-");
		}

		stmt.setString(1, equipe.getNome());
		stmt.setString(2, equipe.getObservacao());
		stmt.setInt(3, equipe.getId());

		stmt.executeUpdate();
		conec.close();

	}

	// DELETAR DADOS NA TABELA DE EQUIPE

	public void deletar(int id) throws SQLException, ClassNotFoundException {
		Connection conec = ConnectionFactory.createConnection();
		conec.setAutoCommit(false);

		String sql2 = "DELETE FROM EquipeFuncionario WHERE idEquipe= ? ";
		PreparedStatement stmt2 = conec.prepareStatement(sql2);
		stmt2.setInt(1, id);
		stmt2.executeUpdate();

		String sql = "Update Equipe set ativo = ? WHERE idEquipe= ?";
		PreparedStatement stmt = conec.prepareStatement(sql);

		stmt.setString(1, "n");
		stmt.setInt(2, id);
		stmt.executeUpdate();

		conec.commit();

	}

	// SELECIONAR DADOS NA TABELA DE EQUIPE PELO ID

	public EquipeBean obterPorId(int id) throws SQLException, ClassNotFoundException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Equipe WHERE idEquipe = '" + id + "'";

		PreparedStatement stmt = conec.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		EquipeBean equipe = new EquipeBean();

		while (rs.next()) {

			equipe.setId(rs.getInt("idEquipe"));
			equipe.setObservacao(rs.getString("observacao"));
			equipe.setNome(rs.getString("nome"));

		}

		conec.close();
		return equipe;

	}
	
	//SELECIONAR DADOS NA TABELA DE EQUIPE PELO NOME
	
	public EquipeBean obterPorNome(String nome) throws SQLException, ClassNotFoundException {
		
		Connection conec = ConnectionFactory.createConnection();
		
		String sql = "SELECT * FROM Equipe WHERE nome = ?";
		
		PreparedStatement stmt = conec.prepareStatement(sql);
		stmt.setString(1, nome);
		
		ResultSet rs = stmt.executeQuery();
		
		EquipeBean equipe = new EquipeBean();
		
		while (rs.next()) {
			
			equipe.setId(rs.getInt("idEquipe"));
			equipe.setObservacao(rs.getString("observacao"));
			equipe.setNome(rs.getString("nome"));
			
		}
		
		conec.close();
		return equipe;
		
	}

	// SELECIONAR DADOS NA TABELA DE EQUIPE PELO NOME

	public EquipeBean listarPorNome(String nome) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Equipe WHERE nome = ? and ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		
		ps.setString(1, nome);
		ps.setString(2, "s");
		
		ResultSet rs = ps.executeQuery();

		EquipeBean equipeBean = null;
		while (rs.next()) {
			equipeBean = new EquipeBean();
			equipeBean.setId(rs.getInt("idEquipe"));
			equipeBean.setNome(rs.getString("nome"));
			equipeBean.setObservacao(rs.getString("observacao"));
		}
		conexao.close();
		
		return equipeBean;
	}

	//INSERIR DADOS NA TABELA POR EQUIPE
	
	public void inserirPorEquipe(int equipe, int funcionario) throws ClassNotFoundException, SQLException {

		Connection conexao = ConnectionFactory.createConnection();
		String sql = "INSERT INTO EquipeFuncionario (idEquipe, idFuncionario) VALUES (?,?)";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setInt(1, equipe);
		ps.setInt(2, funcionario);

		ps.executeUpdate();
		ps.close();
		conexao.close();
	}

	//DELETAR DADOS NA TABELA POR EQUIPE
	
	public void deletarPorEquipe(int idEquipe, int idFuncionario) throws ClassNotFoundException, SQLException {

		Connection conec = ConnectionFactory.createConnection();

		String sql = "DELETE FROM EquipeFuncionario WHERE idEquipe=? and idFuncionario =?";
		PreparedStatement stmt = conec.prepareStatement(sql);
		stmt.setInt(1, idEquipe);
		stmt.setInt(2, idFuncionario);

		stmt.executeUpdate();
		stmt.close();
		conec.close();
	}

	//SELECIONAR DADOS NA TABELA POR EQUIPE
	
	public EquipeFuncionarioBean listarPorEquipe(int idEquipe, int idFuncionario)
			throws ClassNotFoundException, SQLException, BusinessException {
		Connection conec = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM EquipeFuncionario WHERE idEquipe=" + idEquipe + " and idFuncionario="
				+ idFuncionario;
		PreparedStatement stmt = conec.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		EquipeFuncionarioBean equipeFuncionario = null;

		while (rs.next()) {
			equipeFuncionario = new EquipeFuncionarioBean();
			EquipeBusiness equipeBusiness = new EquipeBusiness();
			EquipeBean equipeBean = equipeBusiness.obterPorId(rs.getInt("idEquipe"));
			FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
			FuncionarioBean funcionarioBean = funcionarioBusiness.obterPorId(rs.getInt("idFuncionario"));

			equipeFuncionario.setId(rs.getInt("idEquipeFuncionario"));
			equipeFuncionario.setEquipe(equipeBean);
			equipeFuncionario.setFuncionario(funcionarioBean);

		}
		
		stmt.close();
		conec.close();
		
		return equipeFuncionario;

	}
	
	//SELECIONAR DADOS NA TABELA POR PROJETO
	
	public List<EquipeBean> obterPorProjeto(ProjetoBean projeto) throws ClassNotFoundException, SQLException{
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "  select" 
					+"	e.idEquipe, e.nome, e.observacao"
					+"  from"
					+"	Projeto as p inner join ProjetoEquipe as pe" 
					+"	on p.idProjeto = pe.idProjeto"
					+"	inner join Equipe as e"
					+"	on e.idEquipe = pe.idEquipe"
					+"  where p.idProjeto = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, projeto.getId());
		
		ResultSet rs = ps.executeQuery();
		
		List<EquipeBean> listaEquipe =  new ArrayList<>();
		EquipeBean equipe = null;
		while(rs.next()){
			equipe = new EquipeBean();
			equipe.setId(rs.getInt("idEquipe"));
			equipe.setNome(rs.getString("nome"));
			equipe.setObservacao(rs.getString("observacao"));
			listaEquipe.add(equipe);
		}
		
		return listaEquipe;
		
	}
	
	public EquipeBean obterNomeDesativado(EquipeBean equipeBean)
			throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Equipe WHERE nome = ? and ativo  = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, equipeBean.getNome());
		ps.setString(2, "n");

		ResultSet rs = ps.executeQuery();

		equipeBean = null;
		while (rs.next()) {
			equipeBean = new EquipeBean();
			equipeBean.setId(rs.getInt("idEquipe"));
			equipeBean.setNome(rs.getString("nome"));
			equipeBean.setObservacao(rs.getString("obrservacao"));
		}

		ps.close();
		conexao.close();

		return equipeBean;
	}

	public List<EquipeBean> obterPorFuncionario (int idFuncionario) throws ClassNotFoundException, SQLException {
		Connection conec = ConnectionFactory.createConnection();
		
		String sql ="SELECT f.idFuncionario, f.nomeFuncionario, e.nome FROM Funcionario AS f "
			+"INNER JOIN EquipeFuncionario AS ef  ON f.idFuncionario = ef.idFuncionario "
			+"INNER JOIN Equipe AS e ON e.idEquipe = ef.idEquipe WHERE f.idFuncionario=? and f.ativo = ? ";
		
				PreparedStatement ps = conec.prepareStatement(sql);
				
				ps.setInt(1, idFuncionario);
				ps.setString(2,  "s");
				ResultSet rs = ps.executeQuery();
				List<EquipeBean> listaEquipes = new ArrayList<EquipeBean>();

				EquipeBean equipeBean = null;
				while (rs.next()) {

					equipeBean = new EquipeBean();
					equipeBean.setNome(rs.getString("nome"));

					listaEquipes.add(equipeBean);
				}
				
				return listaEquipes;
			 		
		
	}
	
	public List<EquipeBean> obterPorProjeto (int idProjeto) throws ClassNotFoundException, SQLException {
		Connection conec = ConnectionFactory.createConnection();
		
		String sql ="SELECT e.*, e.nome FROM Equipe AS e "
				+"INNER JOIN ProjetoEquipe AS pe ON e.idEquipe = pe.idEquipe "
				+"INNER JOIN Projeto AS p ON p.idProjeto = pe.idProjeto WHERE p.idProjeto = ? and e.ativo = ?";
		
		PreparedStatement ps = conec.prepareStatement(sql);
		
		ps.setInt(1, idProjeto);
		ps.setString(2,  "s");
		ResultSet rs = ps.executeQuery();
		List<EquipeBean> listaEquipes = new ArrayList<EquipeBean>();
		
		EquipeBean equipeBean = null;
		while (rs.next()) {
			
			equipeBean = new EquipeBean();
			equipeBean.setId(rs.getInt("idEquipe"));
			equipeBean.setNome(rs.getString("nome"));
			
			listaEquipes.add(equipeBean);
		}
		
		return listaEquipes;
		
		
	}

	public boolean verificarPorFuncionarios(int id) throws ClassNotFoundException, SQLException {
		Connection conec = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM EquipeFuncionario WHERE idEquipe = ?";
		PreparedStatement ps = conec.prepareStatement(sql);
		ps.setInt(1,id);
		
		ResultSet rs = ps.executeQuery();
		boolean check = true;
		while(rs.next()){
			check = false;
		}
		conec.close();
		ps.close();
		
		return check;
	}

}
