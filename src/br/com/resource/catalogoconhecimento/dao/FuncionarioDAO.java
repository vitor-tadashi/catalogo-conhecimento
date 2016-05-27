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

	// CRIA
	public int inserir(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "INSERT INTO Funcionario(idCargo,nomeFuncionario,telefone,nomeUser,email, ativo, CPF, RG, dataNascimetno) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement st = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		
		st.setInt(1, funcionario.getCargo().getId());
		st.setString(2, funcionario.getNome());
		st.setString(3, funcionario.getTelefone());
		st.setString(4, funcionario.getNomeUser());
		st.setString(5, funcionario.getEmail());
		st.setString(6, "s");
		st.setString(7, funcionario.getCpf());
		st.setString(8, funcionario.getRg());
		st.setDate(9, new java.sql.Date(funcionario.getDataNascimento().getTime()));
		
		st.executeUpdate();
		ResultSet rs = st.getGeneratedKeys();
		int id = 0;
		if(rs.next()){
			id = rs.getInt(1);
		}
		
		funcionario.setId(id);
		st.close();
		conexao.close();
		return id;
	}

	// LISTA
	public List<FuncionarioBean> listar() throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Funcionario where ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");
		
		ResultSet rs = ps.executeQuery();

		ArrayList<FuncionarioBean> funcionarios = new ArrayList<FuncionarioBean>();
		FuncionarioBean funcionario;
		CargoBusiness cargoBusiness = new CargoBusiness();
		TecnologiaFuncionarioBusiness tecnologiaFuncionarioBusiness = new TecnologiaFuncionarioBusiness();
		while (rs.next()) {
			CargoBean cargo = cargoBusiness.obterPorId(rs.getInt("idCargo"));
			List<TecnologiaBean> tecnologias = tecnologiaFuncionarioBusiness.joinTecnologiaFuncionario(rs.getInt("idFuncionario"));
			
			funcionario = new FuncionarioBean();
			
			funcionario.setId(rs.getInt("idFuncionario"));
			funcionario.setCargo(cargo);
			funcionario.setNome(rs.getString("nomeFuncionario"));
			funcionario.setNomeUser(rs.getString("nomeUser"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setCpf(rs.getString("CPF"));
			funcionario.setRg(rs.getString("RG"));
			funcionario.setDataNascimento(rs.getDate("dataNascimetno"));
			funcionario.setTecnologias(tecnologias);
			
			funcionarios.add(funcionario);
		}

		conexao.close();
		return funcionarios;
	}

	// ATUALIZA
	public void atualizar(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "UPDATE Funcionario SET nomeFuncionario =?, telefone =?, nomeUser=?, email =?, CPF = ?, RG = ?, dataNascimetno = ? WHERE idFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, funcionario.getNome());
		ps.setString(2, funcionario.getTelefone());
		ps.setString(3, funcionario.getNomeUser());
		ps.setString(4, funcionario.getEmail());
		ps.setString(5, funcionario.getCpf());
		ps.setString(6, funcionario.getRg());
		ps.setDate(7, new java.sql.Date(funcionario.getDataNascimento().getTime()));
		ps.setInt(8, funcionario.getId());
		
		
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
	public FuncionarioBean obterPorId(int idFuncionario) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idFuncionario);
		ResultSet rs = ps.executeQuery();

		FuncionarioBean funcionario = null;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));
			funcionario = new FuncionarioBean();
			funcionario.setId(rs.getInt("idFuncionario"));
			funcionario.setCargo(cargoBean);
			funcionario.setNome(rs.getString("nomeFuncionario"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setNomeUser(rs.getString("nomeUser"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setCpf(rs.getString("CPF"));
			funcionario.setRg(rs.getString("RG"));
			funcionario.setDataNascimento(rs.getDate("dataNascimetno"));	
		}
		
		conexao.close();
		return funcionario;
	}
	
	//OBTER POR NOME
	public FuncionarioBean obterPorNome(String nome) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE nomeFuncionario = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nome);
		ResultSet rs = ps.executeQuery();

		FuncionarioBean funcionario = null;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			funcionario = new FuncionarioBean();
			funcionario.setId(rs.getInt("idFuncionario"));
			funcionario.setCargo(cargoBean);
			funcionario.setNome(rs.getString("nomeFuncionario"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setNomeUser(rs.getString("nomeUser"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setCpf(rs.getString("CPF"));
			funcionario.setRg(rs.getString("RG"));
			funcionario.setDataNascimento(rs.getDate("dataNascimetno"));
		}
		
		conexao.close();
		return funcionario;
	}
	
	

}
