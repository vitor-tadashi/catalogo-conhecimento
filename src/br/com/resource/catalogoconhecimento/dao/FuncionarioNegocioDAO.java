package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class FuncionarioNegocioDAO {
	
	private String sqlInserir = "INSERT INTO FuncionarioNegocio (idFuncionario, idNegocio) VALUES (?, ?)";
	private String sqlConsultar = "SELECT * FROM TecnologiaFuncionario WHERE idFuncionario = ?";
	private final String sqlDeletar = "Delete FROM FuncionarioNegocio WHERE idFuncionario = ?";
	Connection conexao;
	
	public FuncionarioNegocioDAO() throws ClassNotFoundException, SQLException{
		conexao = ConnectionFactory.createConnection();
	}
	
	public int adicionar(FuncionarioBean funcionario, List<NegocioBean> negocios) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlInserir);
		int linhasAfetadas = 0;
		
		for(NegocioBean negocio : negocios){
			ps.setInt(1, funcionario.getId());
			ps.setInt(2, negocio.getId());
			linhasAfetadas = ps.executeUpdate();
		}
		
		ps.close();
		conexao.close();
		return linhasAfetadas;
	}
	
	public List<NegocioBean> listar(FuncionarioBean funcionario) throws SQLException, ClassNotFoundException{
		PreparedStatement ps = conexao.prepareStatement(sqlConsultar);
		ps.setInt(1, funcionario.getId());
		
		ResultSet rs = ps.executeQuery();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		List<NegocioBean> negocios = new ArrayList<>();
		
		while (rs.next()){
			NegocioBean negocioBean = new NegocioBean();
			int id = rs.getInt("idNegocio");
			negocioBean = negocioBusiness.obterPorId(id);
			negocios.add(negocioBean);
		}

		
		return negocios;
	}
	
	public List<NegocioBean> joinFuncionarioNegocio(int idFuncionario)
			throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT n.idNegocio, n.areaAtuacao " + "FROM Funcionario AS f "
				+ "INNER JOIN FuncionarioNegocio AS fn " + "ON fn.idFuncionario = f.idFuncionario "
				+ "INNER JOIN  Negocio AS n " + "ON n.idNegocio = fn.idNegocio " + "WHERE fn.idFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idFuncionario);

		ResultSet rs = ps.executeQuery();
		List<NegocioBean> listaNegocios = new ArrayList<NegocioBean>();
		NegocioBean negocio = null;
		while (rs.next()) {
			negocio = new NegocioBean();
			negocio.setId(rs.getInt("idNegocio"));
			negocio.setAreaAtuacao(rs.getString("areaAtuacao"));
			listaNegocios.add(negocio);
		}
		conexao.close();
		return listaNegocios;
	}
	
	private void remover(FuncionarioBean funcionario) throws SQLException{
		PreparedStatement ps = conexao.prepareStatement(sqlDeletar);
		ps.setInt(1, funcionario.getId());
		
		ps.executeUpdate();
	}
	
	public void atualizar(FuncionarioBean funcionarioBean, List<NegocioBean> listaNegocios) throws SQLException {
		this.remover(funcionarioBean);

		this.adicionar(funcionarioBean, listaNegocios);

	}

}
