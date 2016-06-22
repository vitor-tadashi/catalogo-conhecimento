package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class FuncionarioDAO extends GenericDAOImpl<FuncionarioBean, Integer> {

	/**
	 * M�todo para listar todos os funcion�rios ativos
	 * 
	 * @return Lista de funcion�rios
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioBean> listar() {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean AS f WHERE f.ativo = 'S' ORDER BY f.nome ASC", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = query.getResultList();
		return listaFuncionario;
	}

	// /**
	// * M�todo para adicionar um novo funcion�rio
	// *
	// * @param funcionarioBean
	// * @return id, criado no bd, do novo funcion�rio adicionado
	// * @throws ClassNotFoundException
	// * @throws SQLException
	// */
	// public int adicionar(FuncionarioBean funcionarioBean) throws
	// ClassNotFoundException, SQLException {
	// Connection conexao = ConnectionFactory.createConnection();
	// String sql = "INSERT INTO
	// Funcionario(idCargo,nomeFuncionario,telefone,nomeUser,email, ativo, CPF,
	// RG, dataNascimento) VALUES(?,?,?,?,?,?,?,?,?)";
	// PreparedStatement st = conexao.prepareStatement(sql,
	// Statement.RETURN_GENERATED_KEYS);
	//
	// st.setInt(1, funcionarioBean.getCargo().getId());
	// st.setString(2, funcionarioBean.getNome());
	// st.setString(3, funcionarioBean.getTelefone());
	// st.setString(4, funcionarioBean.getNomeUser());
	// st.setString(5, funcionarioBean.getEmail());
	// st.setString(6, "s");
	// st.setString(7, funcionarioBean.getCpf());
	// st.setString(8, funcionarioBean.getRg());
	// st.setDate(9, new Date(funcionarioBean.getDataNascimento().getTime()));
	//
	// st.executeUpdate();
	// ResultSet rs = st.getGeneratedKeys();
	// int id = 0;
	// if (rs.next()) {
	// id = rs.getInt(1);
	// }
	//
	// funcionarioBean.setId(id);
	// st.close();
	// conexao.close();
	// return id;
	// }

	// /**
	// * m�todo para alterar informa��es de um funcion�rio
	// *
	// * @param funcionarioBean
	// * @throws ClassNotFoundException
	// * @throws SQLException
	// */
	// public void alterar(FuncionarioBean funcionarioBean) throws
	// ClassNotFoundException, SQLException,BusinessException {
	// Connection conexao = ConnectionFactory.createConnection();
	// String sql = "UPDATE Funcionario SET nomeFuncionario =?, telefone =?
	// WHERE idFuncionario = ?";
	// PreparedStatement ps = conexao.prepareStatement(sql);
	// ps.setString(1, funcionarioBean.getNome());
	// ps.setString(2, funcionarioBean.getTelefone());
	// ps.setInt(3, funcionarioBean.getId());
	//
	// ps.executeUpdate();
	// conexao.close();
	// }

	// /**
	// * M�todo para remover logicamente um funcion�rio
	// *
	// * @param id
	// * @throws SQLException
	// * @throws ClassNotFoundException
	// */
	// public void remover(int id) throws SQLException, ClassNotFoundException,
	// BusinessException {
	//
	// Connection conexao = ConnectionFactory.createConnection();
	//
	// String sql1 = "DELETE FROM TecnologiaFuncionario WHERE idFuncionario= ?
	// ";
	// PreparedStatement stmt2 = conexao.prepareStatement(sql1);
	// stmt2.setInt(1, id);
	// stmt2.executeUpdate();
	//
	// String sql2 = "DELETE FROM FuncionarioNegocio WHERE idFuncionario= ? ";
	// PreparedStatement stmt4 = conexao.prepareStatement(sql2);
	// stmt4.setInt(1, id);
	// stmt4.executeUpdate();
	//
	// String sql3 = "DELETE FROM EquipeFuncionario WHERE idFuncionario= ? ";
	// PreparedStatement stmt3 = conexao.prepareStatement(sql3);
	// stmt3.setInt(1, id);
	// stmt3.executeUpdate();
	//
	// String sql4 = "update Funcionario set ativo = ? WHERE idFuncionario= ? ";
	// PreparedStatement stmt1 = conexao.prepareStatement(sql4 );
	//
	// stmt1.setString(1, "n");
	// stmt1.setInt(2, id);
	// stmt1.executeUpdate();
	//
	// conexao.commit();
	//
	// }

	/**
	 * M�todo para obter informa��es de um funcion�rio por Id
	 * 
	 * @param id
	 * @return Todas informa��es do funcion�rio
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FuncionarioBean obterPorId(int id) throws SQLException, ClassNotFoundException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		FuncionarioBean funcionarioBean = null;
		while (rs.next()) {

			funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));
		}

		conexao.close();
		return funcionarioBean;
	}

	/**
	 * M�todo para obter informa��es de um funcion�rio por nome
	 * 
	 * @param nome
	 * @return Todas informa��es do funcion�rio
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FuncionarioBean obterPorNome(String nome) throws SQLException, ClassNotFoundException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE nomeFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nome);
		ResultSet rs = ps.executeQuery();

		FuncionarioBean funcionarioBean = null;
		while (rs.next()) {

			funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));
		}

		conexao.close();
		return funcionarioBean;
	}

	/**
	 * M�todo para obter informa��es espec�ficas funcion�rios por idEquipe
	 * 
	 * @param idEquipe
	 * @return Lista de funcion�rios com informa��es espec�ficas
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public List<FuncionarioBean> listarPorEquipe(int idEquipe)
			throws BusinessException {
		
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean AS f JOIN f.equipes e WHERE f.ativo = 'S' and e.id = :id",
				FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = (List<FuncionarioBean>) query.setParameter("id", idEquipe).getResultList();
		return listaFuncionario ;


	}

	// /**
	// * M�todo para obter informa��es de um funcion�rio caso ele j� tenha sido
	// * removido
	// *
	// * @param nome
	// * @return informa��es do funcion�rio removido
	// * @throws SQLException
	// * @throws ClassNotFoundException
	// */
	// public FuncionarioBean obterDesativado(String nome) throws SQLException,
	// ClassNotFoundException, BusinessException {
	// Connection conexao = ConnectionFactory.createConnection();
	// String sql = "SELECT * FROM Funcionario WHERE nomeFuncionario = ? AND
	// ativo =?";
	// PreparedStatement ps = conexao.prepareStatement(sql);
	// ps.setString(1, nome);
	// ps.setString(2, "n");
	// ResultSet rs = ps.executeQuery();
	//
	// FuncionarioBean funcionarioBean = null;
	// CargoBusiness cargoBusiness = new CargoBusiness();
	// while (rs.next()) {
	// CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));
	//
	// funcionarioBean = new FuncionarioBean();
	// funcionarioBean.setId(rs.getInt("idFuncionario"));
	// funcionarioBean.setCargo(cargoBean);
	// funcionarioBean.setNome(rs.getString("nomeFuncionario"));
	// funcionarioBean.setTelefone(rs.getString("telefone"));
	// funcionarioBean.setNomeUser(rs.getString("nomeUser"));
	// funcionarioBean.setEmail(rs.getString("email"));
	// funcionarioBean.setCpf(rs.getString("CPF"));
	// funcionarioBean.setRg(rs.getString("RG"));
	// funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));
	// }
	//
	// conexao.close();
	// return funcionarioBean;
	// }

	/**
	 * Lista todos os funcion�rios de uma tecnologia espec�fica
	 * 
	 * @param nomeTecnologias
	 * @return List<FuncionarioBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FuncionarioBean> listarPorTecnologias(String nomeTecnologias)
			throws ClassNotFoundException, SQLException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT f.* FROM Funcionario f "
				+ "INNER JOIN TecnologiaFuncionario tf ON tf.idFuncionario = f.idFuncionario "
				+ "INNER JOIN Tecnologia t ON tf.idTecnologia = t.idTecnologia " + "WHERE t.nomeTecnologia IN ("
				+ nomeTecnologias + ") AND f.ativo = 's' AND t.ativo = 's' "
				+ "GROUP BY	f.CPF, f.RG, f.ativo, f.dataNascimento, f.email, f.idCargo, "
				+ "f.idFuncionario, f.nomeFuncionario, f.nomeUser, f.telefone " + "HAVING COUNT(f.idFuncionario) > 0";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<FuncionarioBean> listaFuncionario = new ArrayList<FuncionarioBean>();
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			FuncionarioBean funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setCargo(cargoBean);
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));

			listaFuncionario.add(funcionarioBean);
		}

		ps.close();
		conexao.close();

		return listaFuncionario;
	}

	// public void reativar(FuncionarioBean funcionarioBean) throws
	// ClassNotFoundException, SQLException,BusinessException {
	// Connection conexao = ConnectionFactory.createConnection();
	//
	// String sql = "UPDATE Funcionario SET ativo = ? WHERE CPF = ?";
	// PreparedStatement ps = conexao.prepareStatement(sql);
	// ps.setString(1, "s");
	// ps.setString(2, funcionarioBean.getCpf());
	//
	// ps.executeUpdate();
	// conexao.close();
	// }

	public FuncionarioBean obterPorCpf(String cpf) throws SQLException, ClassNotFoundException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE CPF = ? AND ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, cpf);
		ps.setString(2, "s");
		ResultSet rs = ps.executeQuery();

		FuncionarioBean funcionarioBean = null;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setCargo(cargoBean);
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));
		}

		conexao.close();
		return funcionarioBean;

	}

	public List<FuncionarioBean> listarPorNegocio(String nomeNegocio)
			throws ClassNotFoundException, SQLException, BusinessException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT f.CPF, f.RG,f.dataNascimento, f.email, f.idCargo, "
				+ "f.idFuncionario, f.nomeFuncionario, f.nomeUser, f.telefone" + " FROM Funcionario f "
				+ "INNER JOIN FuncionarioNegocio fn ON fn.idFuncionario = f.idFuncionario "
				+ "INNER JOIN Negocio as n ON fn.idNegocio = n.idNegocio " + "WHERE n.areaAtuacao IN (" + nomeNegocio
				+ ") " + "GROUP BY	f.CPF, f.RG,f.dataNascimento, f.email, f.idCargo, "
				+ "f.idFuncionario, f.nomeFuncionario, f.nomeUser, f.telefone " + "HAVING COUNT(f.idFuncionario) > 0";

		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<FuncionarioBean> listaFuncionario = new ArrayList<FuncionarioBean>();
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			FuncionarioBean funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setCargo(cargoBean);
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));

			listaFuncionario.add(funcionarioBean);
		}

		ps.close();
		conexao.close();

		return listaFuncionario;
	}

	public FuncionarioBean obterPorEmail(String email) throws SQLException, ClassNotFoundException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE email = ? AND ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, "s");
		ResultSet rs = ps.executeQuery();
		FuncionarioBean funcionarioBean = null;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setCargo(cargoBean);
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));

		}

		conexao.close();
		return funcionarioBean;

	}

	public FuncionarioBean obterPorUser(String user) throws SQLException, ClassNotFoundException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE nomeUser = ? AND ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, user);
		ps.setString(2, "s");
		ResultSet rs = ps.executeQuery();

		FuncionarioBean funcionarioBean = null;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setCargo(cargoBean);
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));
		}

		conexao.close();
		return funcionarioBean;

	}

	public FuncionarioBean obterPorRg(String rg) throws SQLException, ClassNotFoundException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE RG = ? AND ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, rg);
		ps.setString(2, "s");
		ResultSet rs = ps.executeQuery();

		FuncionarioBean funcionarioBean = null;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setCargo(cargoBean);
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));
		}

		conexao.close();
		return funcionarioBean;

	}

	public List<FuncionarioBean> listarPorNome(String nome)
			throws SQLException, ClassNotFoundException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE nomeFuncionario LIKE '%" + nome + "%' and ativo = 's'";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<FuncionarioBean> listaFuncionario = new ArrayList<>();
		FuncionarioBean funcionarioBean = null;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setCargo(cargoBean);
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));

			listaFuncionario.add(funcionarioBean);
		}

		conexao.close();
		return listaFuncionario;
	}

}
