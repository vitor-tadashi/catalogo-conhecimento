package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class ProjetoTecnologiaDAO {
	Connection conexao;
	private final String sqlCriar = "insert into ProjetoTecnologia(idProjeto, idTecnologia) values(?,?)";
	private final String sqlConsultar = "Select * from ProjetoNegocio where idProjeto = ?";
	private final String sqlDeletar  ="Delete from ProjetoNegocio where idProjeto = ?";
	
	
	public ProjetoTecnologiaDAO() throws ClassNotFoundException, SQLException {
		conexao = ConnectionFactory.createConnection();
	}
	
	public int inserir(ProjetoBean projeto,List<TecnologiaBean>listaTecnologia) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlCriar);
		int linhasAfetadas = 0;
		
		for(TecnologiaBean tecnologia : listaTecnologia){
			ps.setInt(1, projeto.getId());
			ps.setInt(2, tecnologia.getId());
			linhasAfetadas = ps.executeUpdate();
		}
		
		ps.close();
		conexao.close();
		return linhasAfetadas;
		
	}
	
	public List<TecnologiaBean> listar(ProjetoBean projeto) throws SQLException, ClassNotFoundException{
		PreparedStatement ps = conexao.prepareStatement(sqlConsultar);
		ps.setInt(1, projeto.getId());
		
		ResultSet rs = ps.executeQuery();
		TecnologiaBusiness TecnologiaBusiness = new TecnologiaBusiness();
		List<TecnologiaBean>listaTecnologia = new ArrayList<>();
		TecnologiaBean tecnologia = null;
		while(rs.next()){
			tecnologia = new TecnologiaBean();
			int id = rs.getInt("idTecnologia");
			tecnologia = TecnologiaBusiness.obterPorId(id);
			listaTecnologia.add(tecnologia);
		}
		
		return listaTecnologia;
	}
	
	public void atualizar(ProjetoBean projeto,List<TecnologiaBean>listaTecnologia) throws SQLException{
		
		this.deletar(projeto);
		
		this.inserir(projeto, listaTecnologia);
		
		conexao.close();
		
	}
	
	public void deletar(ProjetoBean projeto) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlDeletar);
		ps.setInt(1, projeto.getId());
		
		ps.executeUpdate();
	}
	
	
	
	
}
