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
	private final String sqlCriar = "insert into CatalogoConhecimentos.dbo.ProjetoNegocio(idProjeto, idNegocio) values(?,?)";
	private final String sqlConsultar = "Select * from ProjetoNegocio where idProjetoNegocio where = ?";
	
	
	public ProjetoNegocioDAO() throws ClassNotFoundException, SQLException {
		conexao = ConnectionFactory.createConnection();
	}
	
	public int inserir(ProjetoBean projeto,List<NegocioBean>negocios) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlCriar);
		int linhasAfetadas = 0;
		
		for(NegocioBean negocio : negocios){
			ps.setInt(1, projeto.getIdProjeto());
			ps.setInt(2, negocio.getIdNegocio());
			linhasAfetadas = ps.executeUpdate();
		}
		
		ps.close();
		conexao.close();
		return linhasAfetadas;
		
	}
	
	public List<NegocioBean> listar(ProjetoBean projeto) throws SQLException, ClassNotFoundException{
		PreparedStatement ps = conexao.prepareStatement(sqlConsultar);
		ps.setInt(1, projeto.getIdProjeto());
		
		ResultSet rs = ps.executeQuery();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		List<NegocioBean>negocios = new ArrayList<>();
		while(rs.next()){
			NegocioBean negocio = new NegocioBean();
			int id = rs.getInt("idNegocio");
			negocio = negocioBusiness.listarPorId(id);
			negocios.add(negocio);
		}
		
		return negocios;
	}
	
}
