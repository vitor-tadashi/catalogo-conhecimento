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

public class TecnologiaFuncionarioDAO {
	
	private String sqlInserir = "INSERT INTO TecnologiaFuncionario (idFuncionario, idTecnologia) VALUES (?, ?)";
	private String sqlConsultar = "SELECT * FROM TecnologiaFuncionario WHERE idTecnologiaFuncionario = ?";
	private final String sqlDeletar  ="Delete from TecnologiaFuncionario where idFuncionario = ?";
	Connection conexao;
	
	public TecnologiaFuncionarioDAO() throws ClassNotFoundException, SQLException {
		 conexao = ConnectionFactory.createConnection();
	}
	
	public int inserir(FuncionarioBean funcionario, List<TecnologiaBean>tecnologias) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlInserir);
		int linhasAfetadas =0;
		
		for(TecnologiaBean tecnologia: tecnologias){
			ps.setInt(1, funcionario.getId());
			ps.setInt(2, tecnologia.getId());
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
	
	
	
	public List<TecnologiaBean> joinTecnologiaFuncionario(int idFuncionario) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT t.idTecnologia, t.nomeTecnologia "
				+ "FROM Funcionario as f "
				+	"inner join TecnologiaFuncionario as tf "
				+	"on tf.idFuncionario = f.idFuncionario "
				+		"inner join Tecnologia as t "
				+		"on t.idTecnologia = tf.idTecnologia "
				+	"WHERE tf.idFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idFuncionario);
		
		ResultSet rs = ps.executeQuery();
		List<TecnologiaBean> listaTecnologias = new ArrayList<TecnologiaBean>();
		TecnologiaBean tecnologia = null;
		while (rs.next()) {
			tecnologia = new TecnologiaBean();
			tecnologia.setId(rs.getInt("idTecnologia"));
			tecnologia.setNome(rs.getString("nomeTecnologia"));
			listaTecnologias.add(tecnologia);
		}
		conexao.close();
		return listaTecnologias;
	}

	public void atualizar(FuncionarioBean funcionario, List<TecnologiaBean> listaTecnologia) throws SQLException {
		this.deletar(funcionario);
		
		this.inserir(funcionario, listaTecnologia);
		
	}

	private void deletar(FuncionarioBean funcionario) throws SQLException {
		PreparedStatement ps = conexao.prepareStatement(sqlDeletar);
		ps.setInt(1, funcionario.getId());
		
		ps.executeUpdate();
		
	}

}
