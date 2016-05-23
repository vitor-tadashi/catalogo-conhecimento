package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class CargoDAO {

	Connection conexao = null;

	// CRIA
	public void inserir(CargoBean cargo) throws ClassNotFoundException, SQLException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "INSERT INTO Cargo( nomeCargo, ativo) VALUES(?, ?)";

		PreparedStatement st = conexao.prepareStatement(sql);

		st.setString(1, cargo.getNomeCargo());
		st.setString(2, "s");
		
		st.executeUpdate();
		st.close();
		conexao.close();
	}

	// LISTA
	public List<CargoBean> listar() throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cargo where ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, "s");
		ResultSet rs = ps.executeQuery();

		ArrayList<CargoBean> cargos = new ArrayList<CargoBean>();
		CargoBean cargo;

		while (rs.next()) {
			cargo = new CargoBean(rs.getInt("idCargo"), rs.getString("nomeCargo"));
			cargos.add(cargo);
		}

		conexao.close();
		return cargos;
	}

	// ATUALIZA
	public void atualizar(CargoBean cargo) throws ClassNotFoundException, SQLException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Cargo SET nomeCargo = '" + cargo.getNomeCargo() + "' WHERE idCargo = "
				+ cargo.getIdCargo();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.executeUpdate();
		conexao.close();
	}

	// DELETA
	public void deletar(int idCargo) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "update Cargo set ativo = ? WHERE idCargo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, "n");
		ps.setInt(2, idCargo);
		ps.executeUpdate();
		conexao.close();
		
	}

	// LISTA POR ID
	public CargoBean obterPorId(int idCargo) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cargo WHERE idCargo = '" + idCargo + "'";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		CargoBean cargo = new CargoBean();

		while (rs.next()) {

			cargo.setIdCargo(rs.getInt("idCargo"));
			cargo.setNomeCargo(rs.getString("nomeCargo"));
		}
		conexao.close();
		return cargo;
	}
	


	// LISTA POR  NOME
	public CargoBean obterPorNome(String nomeCargo) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cargo WHERE nomeCargo = '" + nomeCargo + "'";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		CargoBean cargo = new CargoBean();

		while (rs.next()) {

			cargo.setIdCargo(rs.getInt("idCargo"));
			cargo.setNomeCargo(rs.getString("nomeCargo"));
		}
		conexao.close();
		return cargo;
	}

}
