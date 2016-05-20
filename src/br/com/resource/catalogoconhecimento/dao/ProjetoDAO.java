package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class ProjetoDAO {

	Connection conn = null;

	// SELECIONAR NA TABELA PROJETO
	public List<ProjetoBean> listar() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "Select * from CatalogoConhecimentos.dbo.Projeto";

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		List<ProjetoBean> projetos = new ArrayList<ProjetoBean>();
		ProjetoBean projeto;
		EquipeBusiness equipeBusiness = new EquipeBusiness();
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		EquipeBean equipe;
		ClienteBean cliente;
		
		while(rs.next()) {
			
			cliente = clienteBusiness.listarPorId(rs.getInt("idCliente"));
			equipe = equipeBusiness.listarPorId( rs.getInt("idEquipe"));
			
			projeto = new ProjetoBean(rs.getInt("idProjeto"), cliente, equipe,
					rs.getString("nomeProjeto"), rs.getString("observacao"));
			projetos.add(projeto);
		}

		conn.close();
		return projetos;

	}

	// ADICIONAR NA TABELA PROJETO
	public void inserir(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "Insert into CatalogoConhecimentos.dbo.Projeto(idEquipe, idCliente, nomeProjeto,observacao) values(?,?, ?, ?)";

		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		stmt.setInt(1, projeto.getEquipe().getIdEquipe());
		stmt.setInt(2, projeto.getCliente().getId());
		stmt.setString(3, projeto.getNomeProjeto());
		stmt.setString(4, projeto.getObservacao());

		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		int newId = 0;
		
		if (rs.next()) {
		   newId = rs.getInt(1);
		   projeto.setIdProjeto(newId);
		}
		
		stmt.close();
		conn.close();

		
		
	}

	// ATUALIZAR NA TABELA PROJETO
	public void atualizar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "Update CatalogoConhecimentos.dbo.Projeto set idEquipe = ?, idCliente = ?, nomeProjeto = ?, observacao = ? where idProjeto = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, projeto.getEquipe().getIdEquipe());
		stmt.setInt(2, projeto.getCliente().getId());
		stmt.setString(3, projeto.getNomeProjeto());
		stmt.setString(4, projeto.getObservacao());
		stmt.setInt(5, projeto.getIdProjeto());

		stmt.executeUpdate();
		conn.close();

	}

	// DELETA NA TABELA PROJETO
	public void deletar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		
		Connection conn = ConnectionFactory.createConnection();

		String sql = "Delete from CatalogoConhecimentos.dbo.projeto where idProjeto = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1,projeto.getIdProjeto());

		stmt.executeUpdate();
		conn.close();

	}

	// LISTAR PROJETO POR IDPROJETO
	public ProjetoBean obterPorId(int idProjeto) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM CatalogoConhecimentos.dbo.Projeto WHERE idProjeto = '" + idProjeto + "'";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ProjetoBean projeto = null;
		
		EquipeBusiness equipeBusiness = new EquipeBusiness();
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		EquipeBean equipe;
		ClienteBean cliente;
		
		if(rs.next()) {
			cliente = clienteBusiness.listarPorId(rs.getInt("idCliente"));
			equipe = equipeBusiness.listarPorId( rs.getInt("idEquipe"));
			
			projeto = new ProjetoBean(rs.getInt("idProjeto"), cliente, equipe,
					rs.getString("nomeProjeto"), rs.getString("observacao"));
		}
		conexao.close();
		return projeto;

	}

}
