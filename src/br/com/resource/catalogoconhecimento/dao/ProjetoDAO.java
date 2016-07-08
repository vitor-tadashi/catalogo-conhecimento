package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.ClienteBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class ProjetoDAO extends GenericDAOImpl<ProjetoBean, Integer> {
	
	Connection conn = null;

	@Autowired
	private NegocioDAO negocioDAO;
	
	@Autowired
	private TecnologiaDAO tecnologiaDAO;

	
	// SELECIONAR NA TABELA PROJETO
	public List<ProjetoBean> listar() {
		TypedQuery<ProjetoBean> query = entityManager.createQuery(
				"SELECT f FROM ProjetoBean AS f WHERE f.ativo = 'S' ORDER BY f.nome ASC", ProjetoBean.class);
		List<ProjetoBean> listaProjetos = query.getResultList();
		return listaProjetos;
	}

	// DELETA NA TABELA PROJETO
	public void remover(int id) throws ClassNotFoundException, SQLException {
		ProjetoBean projetoBean = obterPorId(id);
		projetoBean.setAtivo('N');
		alterar(projetoBean);
	}

	/**
	 * Método para obter informações de um projeto por Id
	 * 
	 * @param id
	 * @return Todas informações do projeto
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Override
	public ProjetoBean obterPorId(Integer id)  {

		try {
			TypedQuery<ProjetoBean> query = entityManager.createQuery(
					"SELECT f FROM ProjetoBean f "
					+ " WHERE f.id = :id AND f.ativo = 'S'", ProjetoBean.class);

			ProjetoBean projetoBean = query.setParameter("id", id).getSingleResult();			
			
			return projetoBean;
		
		} catch (Exception e) {
			return null;
		}
	}

	public ProjetoBean obterPorNome(String nome)
			throws SQLException, ClassNotFoundException {

		try {
			TypedQuery<ProjetoBean> query = entityManager.createQuery(
					"SELECT f FROM ProjetoBean as f WHERE f.nome = :nome", ProjetoBean.class);
			List<ProjetoBean> projetos = query.setParameter("nome", nome).getResultList();
			
			if (projetos.size() > 0)
				return projetos.get(0);
			else
				return null;
		} catch (Exception e) {
			return null;
		}
	}

	public void reativar(String nome) throws SQLException, ClassNotFoundException {
		ProjetoBean projetoBean = obterPorNome(nome);
		
		if (projetoBean != null) {
			projetoBean.setAtivo('S');
			alterar(projetoBean);
		}
	}

	public List<ProjetoBean> obterPorTecnologias(String nomeTecnologias)
			throws SQLException, ClassNotFoundException, BusinessException {
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

			projeto = new ProjetoBean();
			projeto.setId(rs.getInt("idProjeto"));
			projeto.setNome(rs.getString("nomeProjeto"));
			projeto.setObservacao(rs.getString("observacao"));
			projeto.setCliente(cliente);

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

	public List<ProjetoBean> obterPorNegocio(String nomeNegocio)
			throws SQLException, ClassNotFoundException, BusinessException {
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

			projeto = new ProjetoBean();
			projeto.setId(rs.getInt("idProjeto"));
			projeto.setNome(rs.getString("nomeProjeto"));
			projeto.setObservacao(rs.getString("observacao"));
			projeto.setCliente(cliente);

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
