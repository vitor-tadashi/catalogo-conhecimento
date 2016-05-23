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
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class FuncionarioDAO {

	// CRIA
	public void inserir(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "INSERT INTO Funcionario(idCargo,nomeFuncionario,telefone,nomeUser,email, ativo) VALUES(?,?,?,?,?,?)";
		PreparedStatement st = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		
		st.setInt(1, funcionario.getCargo().getIdCargo());
		st.setString(2, funcionario.getNomeFuncionario());
		st.setString(3, funcionario.getTelefone());
		st.setString(4, funcionario.getNomeUser());
		st.setString(5, funcionario.getEmail());
		st.setString(6, "s");
		
		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		
		int id = 0;
		if(rs.next()){
			id = rs.getInt("idFuncionario");
		}
		funcionario.setIdFuncionario(id);
		st.close();
		conexao.close();
	}

	// LISTA
	public List<FuncionarioBean> listar() throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Funcionario where ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<FuncionarioBean> funcionarios = new ArrayList<FuncionarioBean>();
		FuncionarioBean funcionario;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargo = cargoBusiness.obterPorId(rs.getInt("idCargo"));
			funcionario = new FuncionarioBean();
			
			funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
			funcionario.setCargo(cargo);
			funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
			funcionario.setNomeUser(rs.getString("nomeUser"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setEmail(rs.getString("email"));
		
			
			funcionarios.add(funcionario);
		}

		conexao.close();
		return funcionarios;
	}

	// ATUALIZA
	public void atualizar(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "UPDATE Funcionario SET nomeFuncionario = '"
				+ funcionario.getNomeFuncionario() + "' telefone = '" + funcionario.getTelefone() + "' nomeUser = '"
				+ funcionario.getNomeUser() + "' email = '" + funcionario.getEmail() + "' WHERE idTecnologia = ? "
				+ funcionario.getIdFuncionario();
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.executeUpdate();
		conexao.close();
	}

	// DELETA
	public void deletar(int idFuncionario) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();
	
			
			String sql2 = "DELETE FROM TecnologiaFuncionario WHERE idFuncionario= ? ";
			PreparedStatement stmt2 = conexao.prepareStatement(sql2);
			stmt2.setInt(1, idFuncionario);
			stmt2.executeUpdate();	
			
			String sql3 = "DELETE FROM EquipeFuncionario WHERE idFuncionario= ? ";
			PreparedStatement stmt3 = conexao.prepareStatement(sql3);
			stmt3.setInt(1, idFuncionario);
			stmt3.executeUpdate();
			
			String sql1 = "update Funcionario set ativo = ? WHERE idFuncionario= ? ";
			PreparedStatement stmt1 = conexao.prepareStatement(sql1);
			
			stmt1.setString(1, "n");
			stmt1.setInt(2, idFuncionario);
			stmt1.executeUpdate();
			
			
			conexao.commit();
			
	
	}

	// LISTA POR ID
	public FuncionarioBean listarPorId(int idFuncionario) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE idFuncionario = '" + idFuncionario
				+ "'";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		FuncionarioBean funcionario = null;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			funcionario = new FuncionarioBean(rs.getInt("idFuncionario"), cargoBean,
					rs.getString("nomeFuncionario"), rs.getString("nomeUser"), rs.getString("telefone"),
					rs.getString("email"));
		}
		conexao.close();
		return funcionario;
	}

}
