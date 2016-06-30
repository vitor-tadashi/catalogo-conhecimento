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
import br.com.resource.catalogoconhecimento.business.EquipeBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class ProjetoEquipeDAO {
	Connection conexao;
	private final String sqlCriar = "insert into ProjetoEquipe(idProjeto, idEquipe) values(?,?)";
	private final String sqlConsultar = "Select * from ProjetoEquipe where idProjeto = ?";
	private final String sqlDeletar  ="Delete from ProjetoEquipe where idProjeto = ?";

	public ProjetoEquipeDAO() throws ClassNotFoundException, SQLException {
		conexao = ConnectionFactory.createConnection();
	}
	
	public int inserir(ProjetoBean projeto,List<EquipeBean>equipes) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlCriar);
		int linhasAfetadas = 0;
		
		for(EquipeBean equipe : equipes){
			if(equipe != null){
				ps.setInt(1, projeto.getId());
				ps.setInt(2, equipe.getId());
				linhasAfetadas = ps.executeUpdate();
			}
			
		}
		
		ps.close();
		conexao.close();
		return linhasAfetadas;
		
	}
	
	public List<EquipeBean> listar(ProjetoBean projeto) throws SQLException, ClassNotFoundException, BusinessException{
		PreparedStatement ps = conexao.prepareStatement(sqlConsultar);
		ps.setInt(1, projeto.getId());
		
		ResultSet rs = ps.executeQuery();
		EquipeBusiness equipeBusiness = new EquipeBusiness();
		List<EquipeBean>equipes = new ArrayList<>();
		while(rs.next()){
			EquipeBean equipe = new EquipeBean();
			int id = rs.getInt("idEquipe");
			equipe = equipeBusiness.obterPorId(id);
			equipes.add(equipe);
		}
		
		return equipes;
	}
	
	public void atualizar(ProjetoBean projeto,List<EquipeBean>equipes) throws SQLException{
		
		this.deletar(projeto);
		
		this.inserir(projeto, equipes);
		
		conexao.close();
		
	}
	
	public void deletar(ProjetoBean projeto) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlDeletar);
		ps.setInt(1, projeto.getId());
		
		ps.executeUpdate();
	}
	
	
}
