package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class TecnologiaDAO {

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

	public List<FuncionarioBean> obterPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM TecnologiaFuncionario WHERE idTecnologia = ?";

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

	public List<ProjetoBean> obterPorProjeto(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM ProjetoTecnologia WHERE idTecnologia = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		List<ProjetoBean> listaProjeto = new ArrayList<ProjetoBean>();
		while (rs.next()) {
			ProjetoBean projetoBean = new ProjetoBean();
			projetoBean.setId(rs.getInt("idProjeto"));

			listaProjeto.add(projetoBean);
		}
		
		ps.close();
		conexao.close();

		return listaProjeto;
	}
	
	
	public List<TecnologiaBean> obterNomePorProjeto(ProjetoBean projeto) throws ClassNotFoundException, SQLException{
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT t.nomeTecnologia"
				+"  FROM Projeto AS p INNER JOIN ProjetoTecnologia AS pt" 
				+"	ON p.idProjeto = pt.idProjeto"
				+"	INNER JOIN Tecnologia AS t ON t.idTecnologia = pt.idTecnologia"
				+"  WHERE p.idProjeto = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, projeto.getId());
		
		ResultSet rs = ps.executeQuery();
		
		List<TecnologiaBean>listaTecnologias = new ArrayList<>();
		TecnologiaBean tecnologiaBean = null;
		while(rs.next()){
			tecnologiaBean = new TecnologiaBean();
			tecnologiaBean.setNome(rs.getString("nomeTecnologia"));
			listaTecnologias.add(tecnologiaBean);
		}
		
		return listaTecnologias;
	}

	public TecnologiaBean obterNomeDesativado(TecnologiaBean tecnologiaBean)
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


}
