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
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class ProjetoDAO {

	public List<ProjetoBean> listar() throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Projeto WHERE ativo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");
		
		ResultSet rs = ps.executeQuery();

		EquipeBusiness equipeBusiness = new EquipeBusiness();
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ProjetoBean projetoBean;
		EquipeBean equipeBean;
		ClienteBean clienteBean;
		
		List<ProjetoBean> listaProjeto = new ArrayList<ProjetoBean>();
		List<NegocioBean> listaNegocio = null;
		List<TecnologiaBean> listaTecnologia = null;
		
		while(rs.next()) {
			clienteBean = clienteBusiness.obterPorId(rs.getInt("idCliente"));
			equipeBean = equipeBusiness.listarPorId( rs.getInt("idEquipe"));
			
			projetoBean = new ProjetoBean(rs.getInt("idProjeto"), clienteBean, equipeBean,
					rs.getString("nomeProjeto"), rs.getString("observacao"));
			listaNegocio = new NegocioDAO().obterPorProjeto(projetoBean);
			listaTecnologia = new TecnologiaDAO().obterNomePorProjeto(projetoBean);
			
			projetoBean.setListaNegocio(listaNegocio);
			projetoBean.setListaTecnologia(listaTecnologia);
			
			listaProjeto.add(projetoBean);
		}

		ps.close();
		conexao.close();
		
		return listaProjeto;
	}

	public void adicionar(ProjetoBean projetoBean) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "INSERT INTO Projeto(idEquipe, idCliente, nomeProjeto,observacao, ativo) VALUES (?,?,?,?,?)";

		PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, projetoBean.getEquipe().getId());
		ps.setInt(2, projetoBean.getCliente().getId());
		ps.setString(3, projetoBean.getNome());
		ps.setString(4, projetoBean.getObservacao());
		ps.setString(5, "s");

		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		
		int newId = 0;
		
		if (rs.next()) {
		   newId = rs.getInt(1);
		   projetoBean.setId(newId);
		}
		
		ps.close();
		conexao.close();
	}

	public void atualizar(ProjetoBean projetoBean) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Projeto SET idEquipe = ?, idCliente = ?, nomeProjeto = ?, observacao = ? WHERE idProjeto = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, projetoBean.getEquipe().getId());
		ps.setInt(2, projetoBean.getCliente().getId());
		ps.setString(3, projetoBean.getNome());
		ps.setString(4, projetoBean.getObservacao());
		ps.setInt(5, projetoBean.getId());

		ps.executeUpdate();
		
		ps.close();
		conexao.close();
	}

	// DELETA NA TABELA PROJETO
	public void deletar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		
		Connection conn = ConnectionFactory.createConnection();
		ProjetoNegocioDAO projetoNegocio = new ProjetoNegocioDAO();
		
		
		String sql = "update projeto set ativo = ? where idProjeto = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, "n");
		stmt.setInt(2,projeto.getId());

		projetoNegocio.deletar(projeto);
		stmt.executeUpdate();
		conn.close();

	}

	// LISTAR PROJETO POR IDPROJETO
	public ProjetoBean obterPorId(int idProjeto) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Projeto WHERE idProjeto = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idProjeto);
		
		ResultSet rs = ps.executeQuery();
		ProjetoBean projeto = null;
		
		EquipeBusiness equipeBusiness = new EquipeBusiness();
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		EquipeBean equipe;
		ClienteBean cliente;
		
		if(rs.next()) {
			cliente = clienteBusiness.obterPorId(rs.getInt("idCliente"));
			equipe = equipeBusiness.listarPorId( rs.getInt("idEquipe"));
			
			projeto = new ProjetoBean(rs.getInt("idProjeto"), cliente, equipe,
					rs.getString("nomeProjeto"), rs.getString("observacao"));
		}
		conexao.close();
		return projeto;

	}
	public ProjetoBean obterPorNome(ProjetoBean projeto) throws SQLException, ClassNotFoundException {
		
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Projeto WHERE nomeProjeto = ? and idCliente = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, projeto.getNome());
		ps.setInt(2, projeto.getCliente().getId());
		
		ResultSet rs = ps.executeQuery();
		ProjetoBean projetoBean = null;
		
		EquipeBusiness equipeBusiness = new EquipeBusiness();
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		EquipeBean equipe = null;
		ClienteBean cliente = null;
		
		if(rs.next()) {
			cliente = clienteBusiness.obterPorId(rs.getInt("idCliente"));
			equipe = equipeBusiness.listarPorId( rs.getInt("idEquipe"));
			
			projetoBean = new ProjetoBean(rs.getInt("idProjeto"), cliente, equipe,
					rs.getString("nomeProjeto"), rs.getString("observacao"));
		}
		conexao.close();
		return projetoBean;
		
	}
	
	public void reativar(ProjetoBean projeto) throws SQLException, ClassNotFoundException{
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "UPDATE Tecnologia SET ativo = ? WHERE nomeTecnologia = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1,"s");
		ps.setString(2, projeto.getNome());

		ps.executeUpdate();
		conexao.close();
	}

}
