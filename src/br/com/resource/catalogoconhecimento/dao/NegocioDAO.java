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

	// CRIA
	public void adicionar(NegocioBean negocio) throws ClassNotFoundException, SQLException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "INSERT INTO Negocio(areaAtuacao, ativo) VALUES(?,?)";
		PreparedStatement st = conexao.prepareStatement(sql);

		st.setString(1, negocio.getAreaAtuacao());
		st.setString(2, "s");

		st.executeUpdate();
		st.close();
		conexao.close();
	}

	// LISTA
	public List<NegocioBean> listar() throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM Negocio where ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");

		ResultSet rs = ps.executeQuery();

		ArrayList<NegocioBean> negocios = new ArrayList<NegocioBean>();
		NegocioBean negocio = null;

		while (rs.next()) {

			int idNegocio = rs.getInt("idNegocio");

			String areaAtuacao = rs.getString("areaAtuacao");

			negocio = new NegocioBean(idNegocio, areaAtuacao);
			negocios.add(negocio);
		}

		conexao.close();
		return negocios;
	}

	// ATUALIZA
	public void alterar(NegocioBean negocio) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "UPDATE Negocio SET areaAtuacao = ? WHERE idNegocio = ? ";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, negocio.getAreaAtuacao());
		ps.setInt(2, negocio.getId());

		ps.executeUpdate();
		conexao.close();
	}

	public NegocioBean obterNomeDesativado(NegocioBean negocioBean) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Negocio WHERE areaAtuacao = ? AND ativo = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, negocioBean.getAreaAtuacao());
		ps.setString(2, "n");
		ResultSet rs = ps.executeQuery();

		NegocioBean negocio = null;

		while (rs.next()) {
			int id = rs.getInt("idNegocio");
			String nomeNeg = rs.getString("areaAtuacao");
			negocio = new NegocioBean();
			negocio.setId(id);
			negocio.setAreaAtuacao(nomeNeg);
		}
		conexao.close();
		return negocio;
	}

	// DELETA
	public void remover(int id) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "update Negocio set ativo = ? WHERE idNegocio = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, "n");
		ps.setInt(2, id);
		ps.executeUpdate();
		conexao.close();
	}

	public void reativar(NegocioBean negocio) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Negocio SET ativo = ? WHERE areaAtuacao = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");
		ps.setString(2, negocio.getAreaAtuacao());

		ps.executeUpdate();
		conexao.close();
	}

	// LISTA POR ID
	public NegocioBean obterPorId(int idNegocio) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Negocio WHERE idNegocio = '" + idNegocio + "'";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		NegocioBean negocio = null;
		while (rs.next()) {

			negocio = new NegocioBean();
			negocio.setId(rs.getInt("idNegocio"));
			negocio.setAreaAtuacao(rs.getString("areaAtuacao"));
		}
		conexao.close();
		return negocio;
	}

	// LISTA POR NOME
	public NegocioBean obterPorNome(String nome) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Negocio WHERE areaAtuacao = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);

		ps.setString(1, nome);
		ResultSet rs = ps.executeQuery();

		NegocioBean negocio = null;

		while (rs.next()) {
			int id = rs.getInt("idNegocio");
			String nomeNeg = rs.getString("areaAtuacao");

			negocio = new NegocioBean();
			negocio.setId(id);
			negocio.setAreaAtuacao(nomeNeg);
		}
		conexao.close();
		return negocio;
	}
	
	public List<NegocioBean> obterPorProjeto(ProjetoBean projeto) throws ClassNotFoundException, SQLException{
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "  select" 
					+"	n.areaAtuacao"
					+"  from"
					+"	Projeto as p inner join ProjetoNegocio as pn" 
					+"	on p.idProjeto = pn.idProjeto"
					+"	inner join Negocio as n"
					+"	on n.idNegocio = pn.idNegocio"
					+"  where p.idProjeto = ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, projeto.getId());
		
		ResultSet rs = ps.executeQuery();
		
		List<NegocioBean> listaNegocio =  new ArrayList<>();
		NegocioBean negocio = null;
		while(rs.next()){
			negocio = new NegocioBean();
			negocio.setAreaAtuacao(rs.getString("areaAtuacao"));
			listaNegocio.add(negocio);
		}
		
		return listaNegocio;
		
	}

	public boolean verificarPorProjeto(int id) throws ClassNotFoundException, SQLException {
		Connection conec = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM ProjetoNegocio WHERE idNegocio=?";
		
		PreparedStatement ps = conec.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		boolean check = true;
		while (rs.next()) {
			check = false;
		}
		
		ps.close();
		conec.close();

		return check;
	}

	public List<NegocioBean> obterPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		
		String sql = "SELECT n.areaAtuacao, n.idNegocio FROM Funcionario AS f INNER JOIN FuncionarioNegocio AS fn ON f.idFuncionario = fn.idFuncionario INNER JOIN Negocio AS n ON n.idNegocio = fn.idNegocio WHERE f.idFuncionario = ? AND f.ativo = ? ";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, "s");
		
		ResultSet rs = ps.executeQuery();

		List<NegocioBean> listaNegocio = new ArrayList<NegocioBean>();
		while (rs.next()) {
			NegocioBean negocioBean = new NegocioBean();
			negocioBean.setId(rs.getInt("idNegocio"));
			negocioBean.setAreaAtuacao(rs.getString("areaAtuacao"));

			listaNegocio.add(negocioBean);
		}
		
		ps.close();
		conexao.close();

		return listaNegocio;
	}	
}

