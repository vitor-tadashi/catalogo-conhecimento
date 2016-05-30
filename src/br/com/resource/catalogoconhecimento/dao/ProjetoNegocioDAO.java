package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class ProjetoNegocioDAO {

	Connection conexao;
	private final String sqlCriar = "insert into ProjetoNegocio(idProjeto, idNegocio) values(?,?)";
	private final String sqlConsultar = "Select * from ProjetoNegocio where idProjeto = ?";
	private final String sqlDeletar  ="Delete from ProjetoNegocio where idProjeto = ?";

	
	public ProjetoNegocioDAO() throws ClassNotFoundException, SQLException {
		conexao = ConnectionFactory.createConnection();
	}
	
	public int inserir(ProjetoBean projeto,List<NegocioBean>negocios) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlCriar);
		int linhasAfetadas = 0;
		
		for(NegocioBean negocio : negocios){
			ps.setInt(1, projeto.getId());
			ps.setInt(2, negocio.getId());
			linhasAfetadas = ps.executeUpdate();
		}
		
		ps.close();
		conexao.close();
		return linhasAfetadas;
		
	}
	
	public List<NegocioBean> listar(ProjetoBean projeto) throws SQLException, ClassNotFoundException{
		PreparedStatement ps = conexao.prepareStatement(sqlConsultar);
		ps.setInt(1, projeto.getId());
		
		ResultSet rs = ps.executeQuery();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		List<NegocioBean>negocios = new ArrayList<>();
		while(rs.next()){
			NegocioBean negocio = new NegocioBean();
			int id = rs.getInt("idNegocio");
			negocio = negocioBusiness.obterPorId(id);
			negocios.add(negocio);
		}
		
		return negocios;
	}
	
	public void atualizar(ProjetoBean projeto,List<NegocioBean>negocios) throws SQLException{
		
		this.deletar(projeto);
		
		this.inserir(projeto, negocios);
		
		conexao.close();
		
	}
	
	public void deletar(ProjetoBean projeto) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlDeletar);
		ps.setInt(1, projeto.getId());
		
		ps.executeUpdate();
	}
	
}
