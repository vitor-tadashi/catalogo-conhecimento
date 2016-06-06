package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

public class ProjetoDAO {

	Connection conn = null;

	// SELECIONAR NA TABELA PROJETO
	public List<ProjetoBean> listar() throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "Select * from Projeto where ativo = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "s");

		ResultSet rs = stmt.executeQuery();

		List<ProjetoBean> projetos = new ArrayList<ProjetoBean>();
		ProjetoBean projeto;
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ClienteBean cliente;

		List<NegocioBean> listaNegocio = null;
		List<TecnologiaBean> listaTecnologia = null;
		List<EquipeBean> listaEquipe = null;
		while (rs.next()) {

			cliente = clienteBusiness.obterPorId(rs.getInt("idCliente"));

			projeto = new ProjetoBean(rs.getInt("idProjeto"), cliente, rs.getString("nomeProjeto"),
					rs.getString("observacao"));

			listaNegocio = new NegocioDAO().obterPorProjeto(projeto);
			listaTecnologia = new TecnologiaDAO().listarPorProjeto(projeto);
			listaEquipe = new EquipeDAO().obterPorProjeto(projeto);

			projeto.setListaNegocio(listaNegocio);
			projeto.setListaTecnologia(listaTecnologia);
			projeto.setListaEquipe(listaEquipe);
			projetos.add(projeto);
		}

		conn.close();
		return projetos;

	}

	// ADICIONAR NA TABELA PROJETO
	public void inserir(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "Insert into Projeto(idCliente, nomeProjeto,observacao, ativo) values(?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		stmt.setInt(1, projeto.getCliente().getId());
		stmt.setString(2, projeto.getNome());
		stmt.setString(3, projeto.getObservacao());
		stmt.setString(4, "s");

		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		int newId = 0;

		if (rs.next()) {
			newId = rs.getInt(1);
			projeto.setId(newId);
		}

		stmt.close();
		conn.close();

	}

	// ATUALIZAR NA TABELA PROJETO
	public void atualizar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();

		String sql = "Update Projeto set idCliente = ?, nomeProjeto = ?, observacao = ? where idProjeto = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, projeto.getCliente().getId());
		stmt.setString(2, projeto.getNome());
		stmt.setString(3, projeto.getObservacao());
		stmt.setInt(4, projeto.getId());

		stmt.executeUpdate();
		conn.close();

	}

	// DELETA NA TABELA PROJETO
	public void deletar(int id) throws ClassNotFoundException, SQLException {

		Connection conn = ConnectionFactory.createConnection();

		String sql = "update projeto set ativo = ? where idProjeto = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, "n");
		stmt.setInt(2, id);

		stmt.executeUpdate();
		conn.close();

	}

	// LISTAR PROJETO POR IDPROJETO
	public ProjetoBean obterPorId(int idProjeto) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Projeto WHERE idProjeto = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, idProjeto);

		ResultSet rs = ps.executeQuery();
		ProjetoBean projeto = null;

		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ClienteBean cliente;

		if (rs.next()) {
			cliente = clienteBusiness.obterPorId(rs.getInt("idCliente"));

			projeto = new ProjetoBean(rs.getInt("idProjeto"), cliente, rs.getString("nomeProjeto"),
					rs.getString("observacao"));
		}
		conexao.close();
		return projeto;

	}

	public ProjetoBean obterPorNome(ProjetoBean projeto) throws SQLException, ClassNotFoundException {

		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Projeto WHERE nomeProjeto = ? and idCliente = ? and ativo = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, projeto.getNome());
		ps.setInt(2, projeto.getCliente().getId());
		ps.setString(3, "s");

		ResultSet rs = ps.executeQuery();
		ProjetoBean projetoBean = null;

		ClienteBusiness clienteBusiness = new ClienteBusiness();

		ClienteBean cliente = null;

		if (rs.next()) {
			cliente = clienteBusiness.obterPorId(rs.getInt("idCliente"));
			projetoBean = new ProjetoBean(rs.getInt("idProjeto"), cliente, rs.getString("nomeProjeto"),
					rs.getString("observacao"));
		}
		conexao.close();
		return projetoBean;

	}

	public void reativar(String nome) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "UPDATE Projeto SET ativo = ? WHERE nomeProjeto = ?";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, "s");
		ps.setString(2, nome);

		ps.executeUpdate();
		conexao.close();
	}

	public List<ProjetoBean> obterPorTecnologias(String nomeTecnologias) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT p.idProjeto, p.idCliente, p.nomeProjeto, p.observacao" + " FROM Projeto p"
				+ " INNER JOIN ProjetoTecnologia pt ON p.idProjeto = pt.idProjeto"
				+ " INNER JOIN Tecnologia t on pt.idTecnologia = t.idTecnologia" + " WHERE t.nomeTecnologia IN ("
				+ nomeTecnologias + ") " + " GROUP BY p.idProjeto, p.idCliente, p.nomeProjeto, p.observacao"
				+ " HAVING COUNT(p.idProjeto) > 0";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<ProjetoBean> projetos = new ArrayList<ProjetoBean>();
		ProjetoBean projeto;
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ClienteBean cliente;

		List<NegocioBean> listaNegocio = null;
		List<TecnologiaBean> listaTecnologia = null;
		List<EquipeBean> listaEquipes = null;
		while (rs.next()) {
			cliente = clienteBusiness.obterPorId(rs.getInt("idCliente"));

			projeto = new ProjetoBean(rs.getInt("idProjeto"), cliente, rs.getString("nomeProjeto"),
					rs.getString("observacao"));

			listaNegocio = new NegocioDAO().obterPorProjeto(projeto);
			listaTecnologia = new TecnologiaDAO().listarPorProjeto(projeto);
			listaEquipes = new EquipeDAO().obterPorProjeto(projeto);

			projeto.setListaEquipe(listaEquipes);
			projeto.setListaNegocio(listaNegocio);
			projeto.setListaTecnologia(listaTecnologia);
			projetos.add(projeto);
		}

		return projetos;

	}

	public ProjetoBean obterPorNomeDesativado(String nome) throws ClassNotFoundException, SQLException {
		Connection conec = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Projeto WHERE nomeProjeto= ? AND ativo = ?";
		PreparedStatement ps = conec.prepareStatement(sql);

		ps.setString(1, nome);
		ps.setString(2, "n");

		ResultSet rs = ps.executeQuery();

		ProjetoBean projetoBean = null;
		while (rs.next()) {
			projetoBean = new ProjetoBean();
			projetoBean.setId(rs.getInt("idProjeto"));
			projetoBean.setNome(rs.getString("nomeProjeto"));
			projetoBean.setObservacao(rs.getString("observacao"));

		}
		conec.close();
		ps.close();
		return projetoBean;
	}

	public List<ProjetoBean> obterPorNegocio(String nomeNegocio) throws SQLException, ClassNotFoundException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT p.idProjeto, p.idCliente, p.nomeProjeto, p.observacao" + " FROM Projeto p"
				+ " INNER JOIN ProjetoNegocio pn ON p.idProjeto = pn.idProjeto"
				+ " INNER JOIN Negocio n on pn.idNegocio = n.idNegocio" + " WHERE n.areaAtuacao IN (" + nomeNegocio
				+ ") " + " GROUP BY p.idProjeto, p.idCliente, p.nomeProjeto, p.observacao"
				+ " HAVING COUNT(p.idProjeto) > 0";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<ProjetoBean> projetos = new ArrayList<ProjetoBean>();
		ProjetoBean projeto;
		ClienteBusiness clienteBusiness = new ClienteBusiness();
		ClienteBean cliente;

		List<NegocioBean> listaNegocio = null;
		List<TecnologiaBean> listaTecnologia = null;
		List<EquipeBean> listaEquipes = null;
		while (rs.next()) {
			cliente = clienteBusiness.obterPorId(rs.getInt("idCliente"));

			projeto = new ProjetoBean(rs.getInt("idProjeto"), cliente, rs.getString("nomeProjeto"),
					rs.getString("observacao"));

			listaNegocio = new NegocioDAO().obterPorProjeto(projeto);
			listaTecnologia = new TecnologiaDAO().listarPorProjeto(projeto);
			listaEquipes = new EquipeDAO().obterPorProjeto(projeto);

			projeto.setListaEquipe(listaEquipes);
			projeto.setListaNegocio(listaNegocio);
			projeto.setListaTecnologia(listaTecnologia);
			projetos.add(projeto);
		}

		return projetos;

	}

	public List<ProjetoBean> listarPorNomeCliente(String nomeCliente) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT p.nomeProjeto, p.observacao "
				+ "FROM Projeto AS p INNER JOIN Cliente AS c ON p.idCliente = c.idCliente "
				+ "WHERE c.nomeCliente = ? AND p.ativo = 's' AND c.ativo = 's'";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setString(1, nomeCliente);

		ResultSet rs = ps.executeQuery();

		ArrayList<ProjetoBean> listaProjeto = new ArrayList<ProjetoBean>();
		while (rs.next()) {
			ProjetoBean projetoBean = new ProjetoBean();
			projetoBean.setId(rs.getInt("idProjeto"));
			projetoBean.setNome(rs.getString("nomeProjeto"));
			projetoBean.setObservacao(rs.getString("observacao"));

			listaProjeto.add(projetoBean);
		}

		ps.close();
		conexao.close();

		return listaProjeto;
	}

	public boolean verificarPorEquipe(int id) throws ClassNotFoundException, SQLException {
		Connection conec = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM ProjetoEquipe WHERE idProjeto=?";
		PreparedStatement ps = conec.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		boolean check = true;
		while (rs.next()) {
			check = false;

		}
		conec.close();
		ps.close();

		return check;
	}

	public boolean verificarPorNegocio(int id) throws ClassNotFoundException, SQLException {
		Connection conec = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM ProjetoNegocio WHERE idProjeto=?";
		PreparedStatement ps = conec.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		boolean check = true;
		while (rs.next()) {
			check = false;
		}
		conec.close();
		ps.close();

		return check;
	}

	public boolean verificarPorTecnologia(int id) throws ClassNotFoundException, SQLException {
		Connection conec = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM ProjetoTecnologia WHERE idProjeto=?";
		PreparedStatement ps = conec.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		boolean check = true;
		while (rs.next()) {
			check = false;
		}
		conec.close();
		ps.close();

		return check;
	}

}
