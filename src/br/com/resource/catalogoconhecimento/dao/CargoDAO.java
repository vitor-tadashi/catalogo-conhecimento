package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class CargoDAO  extends GenericDAOImpl<CargoBean, Integer>  {

	    public List<CargoBean> listar()  {
		
		@SuppressWarnings("unchecked")
		List<CargoBean> listaCargo = entityManager.createQuery("SELECT c from CargoBean AS c WHERE c.ativo = 'S'").getResultList();
		return listaCargo;
		
	}

	public CargoBean obterPorId(int id) throws SQLException, ClassNotFoundException {
		
		CargoBean cargobean = (CargoBean) entityManager.createQuery("SELECT C FROM CargoBean AS C WHERE C.id = ?").getSingleResult();	
		return cargobean;
	}
	
	public CargoBean obterPorNome(String nome) throws SQLException, ClassNotFoundException {
		
		CargoBean cargobean = (CargoBean) entityManager.createQuery("SELECT C FROM CargoBean AS C WHERE C.nome = ? and ativo = 's' ").getSingleResult();
		return cargobean;
	}
	
	public List<FuncionarioBean> obterPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		
		@SuppressWarnings("unchecked")
		List<FuncionarioBean> listaFuncionario = entityManager.createQuery("SELECT F FROM FuncionarioBean as F WHERE F.id = ?").getResultList();
		return listaFuncionario;
	}
	
	public boolean verificarPorFuncionario(int id) throws ClassNotFoundException, SQLException{
		
	
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE idCargo = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		boolean check = true;
		while (rs.next()) {
			check = false;
		}
		
		ps.close();
		conexao.close();

		return check;
		
		
	}
	
	public CargoBean obterNomeDesativado(CargoBean cargoBean)
			throws SQLException, ClassNotFoundException {
		
	CargoBean nomeDesativado = (CargoBean) entityManager.createQuery("SELECT C FROM Cargo as C WHERE nome = ? AND ativo = 'n' ").getSingleResult();	
	
//		Connection conexao = ConnectionFactory.createConnection();
//
//		String sql = "SELECT * FROM Cargo WHERE nomeCargo = ? AND ativo  = ?";
//
//		PreparedStatement ps = conexao.prepareStatement(sql);
//		ps.setString(1, cargoBean.getNome());
//		ps.setString(2, "n");
//
//		ResultSet rs = ps.executeQuery();
//
//		cargoBean = null;
//		while (rs.next()) {
//			cargoBean = new CargoBean();
//			cargoBean.setId(rs.getInt("idCargo"));
//			cargoBean.setNome(rs.getString("nomeCargo"));
//		}
//
//		ps.close();
//		conexao.close();
		
		return nomeDesativado;
	}
	


	public void remover(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "UPDATE Cargo SET ativo = 'n' WHERE idCargo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "n");
		ps.setInt(2, id);
		
		ps.executeUpdate();
		
		ps.close();
		conexao.close();
	}

	public void reativar(CargoBean cargoBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Cargo SET ativo = ? WHERE nomeCargo = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");
		ps.setString(2, cargoBean.getNome());

		ps.executeUpdate();
		
		ps.close();
		conexao.close();
	}
	
}
