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
		
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cargo WHERE idCargo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		CargoBean cargoBean = new CargoBean();
		while (rs.next()) {
			cargoBean.setId(rs.getInt("idCargo"));
			cargoBean.setNome(rs.getString("nomeCargo"));
		}
		
		ps.close();
		conexao.close();
		
		return cargoBean;
	}
	
	public CargoBean obterPorNome(String nome) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cargo WHERE nomeCargo = ? and ativo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nome);
		ps.setString(2,  "s");
		
		ResultSet rs = ps.executeQuery();

		CargoBean cargoBean = null;
		while (rs.next()) {
			cargoBean = new CargoBean();
			cargoBean.setId(rs.getInt("idCargo"));
			cargoBean.setNome(rs.getString("nomeCargo"));
		}
		
		ps.close();
		conexao.close();
		
		return cargoBean;
	}
	
	public List<FuncionarioBean> obterPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Funcionario WHERE idCargo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		List<FuncionarioBean> listaFuncionario = new ArrayList<FuncionarioBean>();
		while (rs.next()) {
			FuncionarioBean funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));

			listaFuncionario.add(funcionarioBean);
		}
		
		ps.close();
		conexao.close();

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
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cargo WHERE nomeCargo = ? AND ativo  = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, cargoBean.getNome());
		ps.setString(2, "n");

		ResultSet rs = ps.executeQuery();

		cargoBean = null;
		while (rs.next()) {
			cargoBean = new CargoBean();
			cargoBean.setId(rs.getInt("idCargo"));
			cargoBean.setNome(rs.getString("nomeCargo"));
		}

		ps.close();
		conexao.close();
		
		return cargoBean;
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
