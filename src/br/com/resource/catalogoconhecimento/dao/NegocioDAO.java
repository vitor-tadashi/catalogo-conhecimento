package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class NegocioDAO {

	public void adicionar(NegocioBean negocioBean) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "INSERT INTO Negocio(areaAtuacao, ativo) VALUES(?, ?)";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, negocioBean.getAreaAtuacao());
		ps.setString(2, "s");

		ps.executeUpdate();

		ps.close();
		conexao.close();
	}

	public List<NegocioBean> listar() throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Negocio WHERE ativo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");

		ResultSet rs = ps.executeQuery();

		ArrayList<NegocioBean> listaNegocio = new ArrayList<NegocioBean>();
		NegocioBean negocioBean;
		while (rs.next()) {
			negocioBean = new NegocioBean();
			negocioBean.setId(rs.getInt("idNegocio"));
			negocioBean.setAreaAtuacao(rs.getString("areaAtuacao"));
			listaNegocio.add(negocioBean);
		}

		ps.close();
		conexao.close();

		return listaNegocio;
	}

	public NegocioBean obterPorId(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT * FROM Negocio WHERE idNegocio = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		NegocioBean negocioBean = null;
		while (rs.next()) {
			negocioBean = new NegocioBean();
			negocioBean.setId(rs.getInt("idNegocio"));
			negocioBean.setAreaAtuacao(rs.getString("areaAtuacao"));
		}
		
		ps.close();
		conexao.close();
		
		return negocioBean;
	}

	public NegocioBean obterPorNome(String areaAtuacao) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT * FROM Negocio WHERE areaAtuacao = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, areaAtuacao);
		
		ResultSet rs = ps.executeQuery();

		NegocioBean negocioBean = null;
		while (rs.next()) {
			negocioBean = new NegocioBean();
			negocioBean.setId(rs.getInt("idNegocio"));
			negocioBean.setAreaAtuacao(rs.getString("areaAtuacao"));
		}
		
		ps.close();
		conexao.close();
		
		return negocioBean;
	}

	public List<NegocioBean> obterPorProjeto(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT n.areaAtuacao FROM	"
				+ "Projeto AS p INNER JOIN ProjetoNegocio AS pn ON p.idProjeto = pn.idProjeto "
				+ "INNER JOIN Negocio AS n ON n.idNegocio = pn.idNegocio "
				+ "WHERE p.idProjeto = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, projeto.getId());

		ResultSet rs = ps.executeQuery();

		List<NegocioBean> listaNegocio = new ArrayList<>();
		NegocioBean negocioBean = null;
		while (rs.next()) {
			negocioBean = new NegocioBean();
			negocioBean.setAreaAtuacao(rs.getString("areaAtuacao"));
			listaNegocio.add(negocioBean);
		}
		
		ps.close();
		conexao.close();

		return listaNegocio;
	}

	public NegocioBean obterNomeDesativado(NegocioBean negocioBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT * FROM Negocio WHERE areaAtuacao = ? AND ativo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, negocioBean.getAreaAtuacao());
		ps.setString(2, "n");
		ResultSet rs = ps.executeQuery();

		NegocioBean negocioObtido = null;
		while (rs.next()) {
			negocioObtido = new NegocioBean();
			negocioObtido.setId(rs.getInt("idNegocio"));
			negocioObtido.setAreaAtuacao(rs.getString("areaAtuacao"));
		}
		
		ps.close();
		conexao.close();
		
		return negocioObtido;
	}
	
	public void alterar(NegocioBean negocioBean) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "UPDATE Negocio SET areaAtuacao = ? WHERE idNegocio = ? ";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, negocioBean.getAreaAtuacao());
		ps.setInt(2, negocioBean.getId());

		ps.executeUpdate();
		
		ps.close();
		conexao.close();
	}

	public void remover(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "UPDATE Negocio SET ativo = ? WHERE idNegocio = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, "n");
		ps.setInt(2, id);
		
		ps.executeUpdate();
		
		ps.close();
		conexao.close();
	}

	public void reativar(NegocioBean negocioBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Negocio SET ativo = ? WHERE areaAtuacao = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");
		ps.setString(2, negocioBean.getAreaAtuacao());

		ps.executeUpdate();
		
		ps.close();
		conexao.close();
	}

}
