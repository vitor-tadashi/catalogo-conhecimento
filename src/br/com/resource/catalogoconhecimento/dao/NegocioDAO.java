package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class NegocioDAO extends GenericDAOImpl<NegocioBean, Integer> {
 
	// LISTA
	public List<NegocioBean> listar(){

		TypedQuery<NegocioBean> query = entityManager.createQuery("SELECT n FROM NegocioBean AS n WHERE n.ativo = 'S' order by n.areaAtuacao asc",
				NegocioBean.class);
		
		List<NegocioBean> listaNegocio = query.getResultList();
		return listaNegocio;
	}
	 

	public void reativar(NegocioBean negocioBean) throws SQLException, ClassNotFoundException {
		try {
			TypedQuery<NegocioBean> query = entityManager.createQuery(
	 			"UPDATE NegocioBean AS n SET n.ativo = 'S' WHERE n.areaAtuacao = :areaAtuacao", NegocioBean.class);  
	 		query.setParameter("areaAtuacao", negocioBean.getAreaAtuacao()).getSingleResult();
	 		  
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	public NegocioBean obterNomeDesativado(NegocioBean negocioBean) throws SQLException, ClassNotFoundException {
	 	try {
	 		
	 		TypedQuery<NegocioBean> query = entityManager.createQuery(
	 				"SELECT n FROM NegocioBean AS n WHERE n.areaAtuacao = :areaAtuacao AND ativo = 'n'", NegocioBean.class);  
	 			NegocioBean negocioBeanDesativado = query.setParameter("areaAtuacao", negocioBean.getAreaAtuacao()).getSingleResult();
				return negocioBeanDesativado;
			} catch (Exception e) {
				return null;
			}
		}

 

	// LISTA POR NOME
	public NegocioBean obterPorNome(String nome) throws SQLException, ClassNotFoundException {
		
		try {
			TypedQuery<NegocioBean> query = entityManager.createQuery(
					"SELECT n FROM NegocioBean AS n WHERE n.areaAtuacao = :areaAtuacao AND n.ativo = 'S'", NegocioBean.class);
			NegocioBean negocioBean = query.setParameter("areaAtuacao", nome).getSingleResult();
			return negocioBean;
		} catch (Exception e) {
			return null;
		}
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

	public List<NegocioBean> listarPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.createConnection();
		 
		String sql = "SELECT n.areaAtuacao, n.idNegocio FROM Funcionario AS f "
				+ "INNER JOIN FuncionarioNegocio AS fn "
				+ "ON f.idFuncionario = fn.idFuncionario "
				+ "INNER JOIN Negocio AS n "
				+ "ON n.idNegocio = fn.idNegocio "
				+ "WHERE f.idFuncionario = ? AND f.ativo = ? ";
		
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

