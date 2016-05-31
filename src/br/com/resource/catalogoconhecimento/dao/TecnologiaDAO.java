package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class TecnologiaDAO {

	/**
	 * Adicionar uma tecnologia
	 * 
	 * @param tecnologiaBean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void adicionar(TecnologiaBean tecnologiaBean) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "INSERT INTO Tecnologia(nomeTecnologia, ativo) VALUES(?, ?)";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, tecnologiaBean.getNome());
		ps.setString(2, "s");

		ps.executeUpdate();

		ps.close();
		conexao.close();
	}

	/**
	 * Listar todas as tecnologias disponíveis
	 * 
	 * @return List<TecnologiaBean>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<TecnologiaBean> listar() throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Tecnologia WHERE ativo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");

		ResultSet rs = ps.executeQuery();

		ArrayList<TecnologiaBean> listaTecnologia = new ArrayList<TecnologiaBean>();
		TecnologiaBean tecnologiaBean;
		while (rs.next()) {
			tecnologiaBean = new TecnologiaBean();
			tecnologiaBean.setId(rs.getInt("idTecnologia"));
			tecnologiaBean.setNome(rs.getString("nomeTecnologia"));
			listaTecnologia.add(tecnologiaBean);
		}

		ps.close();
		conexao.close();

		return listaTecnologia;
	}
	
	/**
	 * Lista todas as tecnologias de um funcionário
	 * 
	 * @param idFuncionario
	 * @return List<TecnologiaBean> 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TecnologiaBean> listarPorFuncionario(int idFuncionario) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT * FROM TecnologiaFuncionario AS tf "
				+ "INNER JOIN Tecnologia AS t ON tf.idTecnologia = t.idTecnologia "
				+ "WHERE tf.idTecnologia = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idFuncionario);
		
		ResultSet rs = ps.executeQuery();

		List<TecnologiaBean> listaTecnologia = new ArrayList<TecnologiaBean>();
		while (rs.next()) {
			TecnologiaBean tecnologiaBean = new TecnologiaBean();
			tecnologiaBean.setId(rs.getInt("idTecnologia"));
			tecnologiaBean.setNome(rs.getString("nomeTecnologia"));

			listaTecnologia.add(tecnologiaBean);
		}
		
		ps.close();
		conexao.close();

		return listaTecnologia;
	}
	
	/**
	 * Lista todas as tecnologias de um projeto
	 * 
	 * @param projeto
	 * @return List<TecnologiaBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<TecnologiaBean> listarPorProjeto(ProjetoBean projeto) throws ClassNotFoundException, SQLException{
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT t.nomeTecnologia"
				+"  FROM Projeto AS p INNER JOIN ProjetoTecnologia AS pt" 
				+"	ON p.idProjeto = pt.idProjeto"
				+"	INNER JOIN Tecnologia AS t ON t.idTecnologia = pt.idTecnologia"
				+"  WHERE p.idProjeto = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, projeto.getId());
		
		ResultSet rs = ps.executeQuery();
		
		List<TecnologiaBean>listaTecnologia = new ArrayList<>();
		TecnologiaBean tecnologiaBean = null;
		while(rs.next()){
			tecnologiaBean = new TecnologiaBean();
			tecnologiaBean.setId(rs.getInt("idTecnologia"));
			tecnologiaBean.setNome(rs.getString("nomeTecnologia"));
			listaTecnologia.add(tecnologiaBean);
		}
		
		return listaTecnologia;
	}

	/**
	 * Pesquisa uma tecnologia pelo seu id
	 * 
	 * @param id
	 * @return TecnologiaBean
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TecnologiaBean obterPorId(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Tecnologia WHERE idTecnologia = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		TecnologiaBean tecnologiaBean = null;
		while (rs.next()) {
			tecnologiaBean = new TecnologiaBean();
			tecnologiaBean.setId(rs.getInt("idTecnologia"));
			tecnologiaBean.setNome(rs.getString("nomeTecnologia"));
		}

		ps.close();
		conexao.close();

		return tecnologiaBean;
	}

	/**
	 * Pesquisa uma tecnologia pelo seu nome
	 * 
	 * @param nome
	 * @return TecnologiaBean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public TecnologiaBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Tecnologia WHERE nomeTecnologia = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nome);

		ResultSet rs = ps.executeQuery();

		TecnologiaBean tecnologiaBean = null;
		while (rs.next()) {
			tecnologiaBean = new TecnologiaBean();
			tecnologiaBean.setId(rs.getInt("idTecnologia"));
			tecnologiaBean.setNome(rs.getString("nomeTecnologia"));
		}

		ps.close();
		conexao.close();

		return tecnologiaBean;
	}

	/**
	 * Obtem uma tecnologia desativada
	 * 
	 * @param tecnologiaBean
	 * @return TecnologiaBean
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public TecnologiaBean obterDesativado(TecnologiaBean tecnologiaBean)
			throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Tecnologia WHERE nomeTecnologia = ? and ativo  = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, tecnologiaBean.getNome());
		ps.setString(2, "n");

		ResultSet rs = ps.executeQuery();

		tecnologiaBean = null;
		while (rs.next()) {
			tecnologiaBean = new TecnologiaBean();
			tecnologiaBean.setId(rs.getInt("idTecnologia"));
			tecnologiaBean.setNome(rs.getString("nomeTecnologia"));
		}

		ps.close();
		conexao.close();

		return tecnologiaBean;
	}

	/**
	 * Altera um tecnologia
	 * 
	 * @param tecnologiaBean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void alterar(TecnologiaBean tecnologiaBean) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Tecnologia SET nomeTecnologia = ? WHERE idTecnologia = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, tecnologiaBean.getNome());
		ps.setInt(2, tecnologiaBean.getId());

		ps.executeUpdate();

		ps.close();
		conexao.close();
	}

	/**
	 * Remove uma tecnologia
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void remover(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "update Tecnologia set ativo = ? WHERE idTecnologia = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "n");
		ps.setInt(2, id);

		ps.executeUpdate();

		ps.close();
		conexao.close();
	}

	public void reativar(TecnologiaBean tecnologiaBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Tecnologia SET ativo = ? WHERE nomeTecnologia = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");
		ps.setString(2, tecnologiaBean.getNome());

		ps.executeUpdate();

		ps.close();
		conexao.close();
	}

	public boolean verificarPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM TecnologiaFuncionario WHERE idTecnologia = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		boolean check = false;
		while (rs.next()) {
			check = true;
		}
		
		ps.close();
		conexao.close();

		return check;
	}
	

	public boolean verificarPorProjeto(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM ProjetoTecnologia WHERE idTecnologia = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		boolean check = false;
		while (rs.next()) {
			check = true;
		}
		
		ps.close();
		conexao.close();

		return check;
	}

}
