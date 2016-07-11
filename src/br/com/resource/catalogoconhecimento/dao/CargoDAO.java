package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class CargoDAO extends GenericDAOImpl<CargoBean, Integer> {

	public List<CargoBean> listar() {
		TypedQuery<CargoBean> query = entityManager
				.createQuery("SELECT c FROM CargoBean AS c WHERE c.ativo = 'S' order by c.nome asc", CargoBean.class);
		List<CargoBean> listaCargo = query.getResultList();
		return listaCargo;
	}

	public CargoBean obterPorId(int id) throws BusinessException {
		try {
			TypedQuery<CargoBean> query = entityManager
					.createQuery("SELECT c FROM CargoBean AS c WHERE c.id = :id AND c.ativo = 'S'", CargoBean.class);
			CargoBean cargobean = query.setParameter("id", id).getSingleResult();
			return cargobean;
		} catch (Exception e) {
			return null;
		}
	}

	public CargoBean obterPorNome(String nome) {

		try {
			TypedQuery<CargoBean> query = entityManager.createQuery(
					"SELECT c FROM CargoBean AS c WHERE c.nome = :nome AND c.ativo = 'S'", CargoBean.class);
			CargoBean cargobean = query.setParameter("nome", nome).getSingleResult();
			return cargobean;
		} catch (Exception e) {
			return null;
		}
	}

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
}
