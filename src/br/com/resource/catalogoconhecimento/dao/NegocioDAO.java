package br.com.resource.catalogoconhecimento.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;

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
		TypedQuery<NegocioBean> query = entityManager.createQuery("SELECT n FROM ProjetoBean p JOIN p.listaNegocio n WHERE p.id = :id", NegocioBean.class);
		query.setParameter("id",projeto.getId());
		return query.getResultList();
		
	}

	public boolean verificarPorProjeto(int id) throws ClassNotFoundException, SQLException {
		TypedQuery<ProjetoBean> query = entityManager.createQuery("SELECT n FROM ProjetoBean AS p join p.listaNegocio AS n WHERE p.id = :id", ProjetoBean.class);
		query.setParameter("id", id);
		List<ProjetoBean> equipes = query.getResultList();
		
		if(equipes.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	public List<NegocioBean> listarPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		
		TypedQuery<NegocioBean> query = entityManager.createQuery("SELECT n FROM FuncionarioBean AS f"
				+ "JOIN f.listaNegocio AS n WHERE f.id = :id AND f.ativo = 'S'", NegocioBean.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}	
}

