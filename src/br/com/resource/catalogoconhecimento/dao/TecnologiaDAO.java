package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class TecnologiaDAO extends GenericDAOImpl<TecnologiaBean, Integer> {

	/**
	 * Listar todas as tecnologias disponíveis
	 * 
	 * @return List<TecnologiaBean>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<TecnologiaBean> listar() {
		TypedQuery<TecnologiaBean> query = entityManager
				.createQuery("SELECT t FROM TecnologiaBean AS t WHERE t.ativo = 'S'", TecnologiaBean.class);
		List<TecnologiaBean> listaTecnologia = query.getResultList();
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
		
		TypedQuery<TecnologiaBean> query = entityManager.createQuery("SELECT t FROM FuncionarioBean as f JOIN f.listaTecnologia AS t WHERE f.id = :id AND f.ativo = 'S'  ",TecnologiaBean.class);
		query.setParameter("id", idFuncionario);
		return query.getResultList();
		
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
		
		String sql = "SELECT t.idTecnologia, t.nomeTecnologia"
				+"  FROM Projeto AS p INNER JOIN ProjetoTecnologia AS pt" 
				+"	ON p.idProjeto = pt.idProjeto"
				+"	INNER JOIN Tecnologia AS t ON t.idTecnologia = pt.idTecnologia"
				+"  WHERE p.idProjeto = ? and t.ativo= ?";
		
		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, projeto.getId());
		ps.setString(2, "s");
		
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
	public TecnologiaBean obterPorId(int id) {
		try {
			TypedQuery<TecnologiaBean> query = entityManager
					.createQuery("SELECT t FROM TecnologiaBean AS t WHERE t.id = :id AND t.ativo = 'S'", TecnologiaBean.class);
			TecnologiaBean tecnologiaBean = query.setParameter("id", id).getSingleResult();
			return tecnologiaBean;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Pesquisa uma tecnologia pelo seu nome
	 * 
	 * @param String
	 * @return TecnologiaBean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public TecnologiaBean obterPorNome(String nome) {
		try {
			TypedQuery<TecnologiaBean> query = entityManager
					.createQuery("SELECT t FROM TecnologiaBean AS t WHERE t.nome = :nome AND t.ativo = 'S'", TecnologiaBean.class);
			TecnologiaBean tecnologiaBean = query.setParameter("nome", nome).getSingleResult();
			return tecnologiaBean;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean verificarPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();

		String sql = "SELECT * FROM TecnologiaFuncionario WHERE idTecnologia = ?";

		PreparedStatement ps = conexao.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		boolean check = true;
		while (rs.next()) {
			check = false;
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

		boolean check = true;
		while (rs.next()) {
			check = false;
		}
		
		ps.close();
		conexao.close();

		return check;
	}


}
