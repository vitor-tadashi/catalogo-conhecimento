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
	public void adicionar(CargoBean cargoBean) throws ClassNotFoundException, SQLException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "INSERT INTO Cargo( nomeCargo, ativo) VALUES(?, ?)";

		PreparedStatement st = conexao.prepareStatement(sql);

		st.setString(1, cargoBean.getNome());
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
		CargoBean cargoBean;

		while (rs.next()) {
			cargoBean = new CargoBean(rs.getInt("idCargo"), rs.getString("nomeCargo"));
			cargos.add(cargoBean);
		}

		conexao.close();
		return cargos;
	}

	// ATUALIZA
	public void alterar(CargoBean cargoBean) throws ClassNotFoundException, SQLException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Cargo SET nomeCargo = '" + cargoBean.getNome() + "' WHERE idCargo = " + cargoBean.getId();

		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.executeUpdate();
		conexao.close();
	}

	// DELETA
	public void remover(int id) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "update Cargo set ativo = ? WHERE idCargo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, "n");
		ps.setInt(2, id);
		ps.executeUpdate();
		conexao.close();
	}

	// LISTA POR ID
	public CargoBean obterPorId(int id) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cargo WHERE idCargo = '" + id + "'";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		CargoBean cargoBean = new CargoBean();

		while (rs.next()) {

			cargoBean.setId(rs.getInt("idCargo"));
			cargoBean.setNome(rs.getString("nomeCargo"));
		}
		conexao.close();
		return cargoBean;
	}

	// LISTA POR NOME
	public CargoBean obterPorNome(String nome) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Cargo WHERE nomeCargo = '" + nome + "'";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		CargoBean cargoBean = new CargoBean();

		while (rs.next()) {

			cargoBean.setId(rs.getInt("idCargo"));
			cargoBean.setNome(rs.getString("nomeCargo"));
		}
		conexao.close();
		return cargoBean;
	}

}
