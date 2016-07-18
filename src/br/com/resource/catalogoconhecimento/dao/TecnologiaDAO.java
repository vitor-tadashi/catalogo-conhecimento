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
	 */
	public List<TecnologiaBean> listarPorFuncionario(int idFuncionario) {
//		TypedQuery<TecnologiaBean> query = entityManager.createQuery("SELECT p FROM ProjetoBean p JOIN p.listaNegocio n "
//				+ "WHERE n.areaAtuacao IN ("+nomeNegocio+") and p.ativo = 'S' and n.ativo = 'S'"
//				+ "GROUP BY p.id,p.cliente, p.nome, p.observacao, p.ativo HAVING COUNT(p.id) > 0", ProjetoBean.class);
//			return query.getResultList();
			
		String sql = "SELECT t.idTecnologia, t.nomeTecnologia FROM Tecnologia AS t "
				+ "INNER JOIN TecnologiaFuncionario AS tf ON tf.idTecnologia = t.idTecnologia "
				+ "INNER JOIN Funcionario AS f on tf.idFuncionario = f.idFuncionario "
				+ "WHERE f.idFuncionario = ? and t.ativo=?";
		return null;
	}

	/**
	 * Lista todas as tecnologias de um projeto
	 * 
	 * @param projeto
	 * @return List<TecnologiaBean>
	 */
	public List<TecnologiaBean> listarPorProjeto(ProjetoBean projeto) {
//		TypedQuery<TecnologiaBean> query = entityManager.createQuery("SELECT p FROM ProjetoBean p JOIN p.listaNegocio n "
//				+ "WHERE n.areaAtuacao IN ("+nomeNegocio+") and p.ativo = 'S' and n.ativo = 'S'"
//				+ "GROUP BY p.id,p.cliente, p.nome, p.observacao, p.ativo HAVING COUNT(p.id) > 0", ProjetoBean.class);
//			return query.getResultList();
			
		String sql = "SELECT t.idTecnologia, t.nomeTecnologia"
				+ "  FROM Projeto AS p INNER JOIN ProjetoTecnologia AS pt" + "	ON p.idProjeto = pt.idProjeto"
				+ "	INNER JOIN Tecnologia AS t ON t.idTecnologia = pt.idTecnologia"
				+ "  WHERE p.idProjeto = ? and t.ativo= ?";
		return null;
	}

	/**
	 * Pesquisa uma tecnologia pelo seu id
	 * 
	 * @param id
	 * @return TecnologiaBean
	 */
	public TecnologiaBean obterPorId(int id) {
		TypedQuery<TecnologiaBean> query = entityManager.createQuery(
				"SELECT t FROM TecnologiaBean AS t WHERE t.id = :id AND t.ativo = 'S'", TecnologiaBean.class);
		query.setParameter("id", id);
		List<TecnologiaBean> listaTecnologia = query.getResultList();
		if (listaTecnologia.isEmpty()) {
			return null;
		} else {
			return listaTecnologia.get(0);
		}
	}

	/**
	 * Pesquisa uma tecnologia pelo seu nome
	 * 
	 * @param String
	 * @return TecnologiaBean
	 */
	public TecnologiaBean obterPorNome(String nome) {
		TypedQuery<TecnologiaBean> query = entityManager.createQuery(
				"SELECT t FROM TecnologiaBean AS t WHERE t.nome = :nome AND t.ativo = 'S'", TecnologiaBean.class);
		query.setParameter("nome", nome);
		List<TecnologiaBean> listaTecnologia = query.getResultList();
		if (listaTecnologia.isEmpty()) {
			return null;
		} else {
			return listaTecnologia.get(0);
		}
	}
}
