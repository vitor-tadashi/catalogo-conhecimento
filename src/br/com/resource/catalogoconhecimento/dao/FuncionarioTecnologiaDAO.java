package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class FuncionarioTecnologiaDAO {
	
	private String sql = "INSERT INTO CatalogoConhecimentos.dbo.TecnologiaFuncionario (idTecnologia, idFuncionario) VALUES (?, ?)";
	private String sqlConsultar = "SELECT * FROM CatalogoConhecimentos.dbo.TecnologiaFuncionario WHERE idTecnologiaFuncionario = ?";
	Connection conexao;
	
	public FuncionarioTecnologiaDAO() throws ClassNotFoundException, SQLException {
		 conexao = ConnectionFactory.createConnection();
	}
	
	public int inserir(FuncionarioBean funcionario, List<TecnologiaBean>tecnologias) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sql);
		int linhasAfetadas =0;
		
		for(TecnologiaBean tecnologia: tecnologias){
			ps.setInt(1, funcionario.getId());
			ps.setInt(2, tecnologia.getIdTecnologia());
			linhasAfetadas = ps.executeUpdate();
		}
		
		
		
		
		
		ps.close();
		conexao.close();
		return linhasAfetadas;
		
	}
	
	public List<TecnologiaBean> listar(FuncionarioBean funcionario) throws SQLException, ClassNotFoundException{
		PreparedStatement ps = conexao.prepareStatement(sqlConsultar);
		ps.setInt(1, funcionario.getId());
		
		ResultSet rs = ps.executeQuery();
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		List<TecnologiaBean> tecnologias = new ArrayList<>();
		while(rs.next()){
			TecnologiaBean tecnologia = new TecnologiaBean();
			int id = rs.getInt("idTecnologia");
			tecnologia = tecnologiaBusiness.obterPorId(id);
			tecnologias.add(tecnologia);
		}
		
		return tecnologias;
	}

}
