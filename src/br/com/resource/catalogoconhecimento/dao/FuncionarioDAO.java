package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaFuncionarioBusiness;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class FuncionarioDAO {

	/**
	 * Método para adicionar um novo funcionário
	 * 
	 * @param funcionarioBean
	 * @return id, criado no bd, do novo funcionário adicionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int adicionar(FuncionarioBean funcionarioBean) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "INSERT INTO Funcionario(idCargo,nomeFuncionario,telefone,nomeUser,email, ativo, CPF, RG, dataNascimento) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement st = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		st.setInt(1, funcionarioBean.getCargo().getId());
		st.setString(2, funcionarioBean.getNome());
		st.setString(3, funcionarioBean.getTelefone());
		st.setString(4, funcionarioBean.getNomeUser());
		st.setString(5, funcionarioBean.getEmail());
		st.setString(6, "s");
		st.setString(7, funcionarioBean.getCpf());
		st.setString(8, funcionarioBean.getRg());
		st.setDate(9, new java.sql.Date(funcionarioBean.getDataNascimento().getTime()));

		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		int id = 0;
		if (rs.next()) {
			id = rs.getInt(1);
		}

		funcionarioBean.setId(id);
		st.close();
		conexao.close();
		return id;
	}

	/**
	 * Método para listar todos os funcionários ativos
	 * 
	 * @return Lista de funcionários
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioBean> listar() throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT *, CONVERT(VARCHAR,CONVERT(date, dataNascimento, 100), 103) AS Data_Nascimento FROM Funcionario where ativo = ? ORDER BY nomeFuncionario ASC";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");

		ResultSet rs = ps.executeQuery();

		ArrayList<FuncionarioBean> listaFuncionarios = new ArrayList<FuncionarioBean>();
		FuncionarioBean funcionarioBean;
		CargoBusiness cargoBusiness = new CargoBusiness();
		TecnologiaFuncionarioBusiness tecnologiaFuncionarioBusiness = new TecnologiaFuncionarioBusiness();
		while (rs.next()) {
			CargoBean cargo = cargoBusiness.obterPorId(rs.getInt("idCargo"));
			List<TecnologiaBean> tecnologias = tecnologiaFuncionarioBusiness
					.joinTecnologiaFuncionario(rs.getInt("idFuncionario"));

			funcionarioBean = new FuncionarioBean();

			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setCargo(cargo);
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));
			funcionarioBean.setTecnologias(tecnologias);

			listaFuncionarios.add(funcionarioBean);
		}

		conexao.close();
		return listaFuncionarios;
	}

	/**
	 * método para alterar informações de um funcionário
	 * 
	 * @param funcionarioBean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(FuncionarioBean funcionarioBean) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "UPDATE Funcionario SET nomeFuncionario =?, telefone =?, nomeUser=?, email =? WHERE idFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, funcionarioBean.getNome());
		ps.setString(2, funcionarioBean.getTelefone());
		ps.setString(3, funcionarioBean.getNomeUser());
		ps.setString(4, funcionarioBean.getEmail());
		ps.setInt(5, funcionarioBean.getId());

		ps.executeUpdate();
		conexao.close();
	}

	/**
	 * Método para remover logicamente um funcionário
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void remover(int id) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql2 = "DELETE FROM TecnologiaFuncionario WHERE idFuncionario= ? ";
		PreparedStatement stmt2 = conexao.prepareStatement(sql2);
		stmt2.setInt(1, id);
		stmt2.executeUpdate();

		String sql3 = "DELETE FROM EquipeFuncionario WHERE idFuncionario= ? ";
		PreparedStatement stmt3 = conexao.prepareStatement(sql3);
		stmt3.setInt(1, id);
		stmt3.executeUpdate();

		String sql1 = "update Funcionario set ativo = ? WHERE idFuncionario= ? ";
		PreparedStatement stmt1 = conexao.prepareStatement(sql1);

		stmt1.setString(1, "n");
		stmt1.setInt(2, id);
		stmt1.executeUpdate();

		conexao.commit();

	}

	/**
	 * Método para obter informações de um funcionário por Id
	 * 
	 * @param id
	 * @return Todas informações do funcionário
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FuncionarioBean obterPorId(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);
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

	/**
	 * Método para obter informações de um funcionário por nome
	 * 
	 * @param nome
	 * @return Todas informações do funcionário
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FuncionarioBean obterPorNome(String nome) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE nomeFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nome);
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

	/**
	 * Método para obter informações específicas funcionários por idEquipe
	 * 
	 * @param idEquipe
	 * @return Lista de funcionários com informações específicas
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FuncionarioBean> obterPorEquipe(int idEquipe) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT f.idFuncionario, 	f.nomeFuncionario, f.email" + " FROM Funcionario AS f"
				+ " INNER JOIN EquipeFuncionario AS ef" + " ON f.idFuncionario = ef.idFuncionario"
				+ " INNER JOIN Equipe AS e" + " ON e.idEquipe = ef.idEquipe " + "WHERE e.idEquipe = ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, idEquipe);
		ResultSet rs = stmt.executeQuery();
		List<FuncionarioBean> listaFuncionarios = new ArrayList<FuncionarioBean>();

		TecnologiaFuncionarioBusiness tecnologiaFuncionarioBusiness = new TecnologiaFuncionarioBusiness();

		FuncionarioBean funcionarioBean = null;
		while (rs.next()) {

			funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setEmail(rs.getString("email"));

			List<TecnologiaBean> tecnologias = tecnologiaFuncionarioBusiness
					.joinTecnologiaFuncionario(funcionarioBean.getId());
			funcionarioBean.setTecnologias(tecnologias);

			listaFuncionarios.add(funcionarioBean);
		}
		conexao.close();
		return listaFuncionarios;

	}

	/**
	 * Método para obter informações de um funcionário caso ele já tenha sido
	 * removido
	 * 
	 * @param nome
	 * @return informações do funcionário removido
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FuncionarioBean obterFuncionarioDesativado(String nome) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE nomeFuncionario = ? AND ativo =?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nome);
		ps.setString(2, "n");
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
	
	/**
	 * Lista todos os funcionários de uma tecnologia específica
	 * 
	 * @param nomeTecnologias
	 * @return List<FuncionarioBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FuncionarioBean> listarPorTecnologias(String nomeTecnologias) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT f.* FROM Funcionario f "
				+ "INNER JOIN TecnologiaFuncionario tf ON tf.idFuncionario = f.idFuncionario "
				+ "INNER JOIN Tecnologia t ON tf.idTecnologia = t.idTecnologia "
				+ "WHERE t.nomeTecnologia IN (" + nomeTecnologias + ") AND f.ativo = 's' AND t.ativo = 's' "
				+ "GROUP BY	f.CPF, f.RG, f.ativo, f.dataNascimento, f.email, f.idCargo, "
				+ "f.idFuncionario, f.nomeFuncionario, f.nomeUser, f.telefone "
				+ "HAVING COUNT(f.idFuncionario) > 1";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nomeTecnologias);

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

	public void reativar(FuncionarioBean funcionarioBean) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Funcionario SET ativo = ? WHERE CPF = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");
		ps.setString(2, funcionarioBean.getCpf());

		ps.executeUpdate();
		conexao.close();
	}

}
