package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class CargoDAO extends GenericDAOImpl<CargoBean, Integer> {

	public List<CargoBean> listar() {
		TypedQuery<CargoBean> query = entityManager
				.createQuery("SELECT c from CargoBean AS c WHERE c.ativo = 'S'", CargoBean.class);
		List<CargoBean> listaCargo = query.getResultList();
		return listaCargo;
	}

	public CargoBean obterPorId(int id) throws SQLException, ClassNotFoundException {
		TypedQuery<CargoBean> query = entityManager
				.createQuery("SELECT c FROM CargoBean AS c WHERE c.id = :id AND c.ativo = 'S'", CargoBean.class);
		CargoBean cargobean = query.setParameter("id", id).getSingleResult();
		return cargobean;
	}

	public CargoBean obterPorNome(String nome) throws SQLException, ClassNotFoundException {
		TypedQuery<CargoBean> query = entityManager
				.createQuery("SELECT c FROM CargoBean AS c WHERE c.nome = :nome AND c.ativo = 'S'", CargoBean.class);
		CargoBean cargobean = query.setParameter("nome", nome).getSingleResult();
		return cargobean;
	}
	
	public CargoBean obterNomeDesativado(CargoBean cargoBean) throws SQLException, ClassNotFoundException {
		TypedQuery<CargoBean> query = entityManager
				.createQuery("SELECT c FROM CargoBean AS c WHERE c.nome = :nome AND c.ativo = 'N'", CargoBean.class);
		CargoBean cargoBeanDesativado = query.setParameter("nome", cargoBean.getNome()).getSingleResult();
		return cargoBeanDesativado;
	}

//	public List<FuncionarioBean> obterPorFuncionario(int id) throws ClassNotFoundException, SQLException {
//		@SuppressWarnings("unchecked")
//		List<FuncionarioBean> listaFuncionario = entityManager
//				.createQuery("SELECT f FROM FuncionarioBean as f WHERE f.id = ?").getResultList();
//		return listaFuncionario;
//	}

	public boolean verificarPorFuncionario(int id) throws ClassNotFoundException, SQLException {

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

//	public void reativar(CargoBean cargoBean) throws SQLException, ClassNotFoundException {
//		Connection conexao = ConnectionFactory.createConnection();
//
//		String sql = "UPDATE Cargo SET ativo = ? WHERE nomeCargo = ?";
//
//		PreparedStatement ps = conexao.prepareStatement(sql);
//		ps.setString(1, "s");
//		ps.setString(2, cargoBean.getNome());
//
//		ps.executeUpdate();
//
//		ps.close();
//		conexao.close();
//	}

}
