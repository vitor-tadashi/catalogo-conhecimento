package br.com.resource.catalogoconhecimento.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Repository
public class FuncionarioDAO extends GenericDAOImpl<FuncionarioBean, Integer> {

	/**
	 * Metodo para listar todos os funcionarios ativos
	 * 
	 * @return Lista de funcionarios
	 */
	public List<FuncionarioBean> listar() {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean AS f WHERE f.ativo = 'S' ORDER BY f.nome ASC", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = query.getResultList();
		return listaFuncionario;
	}

	/**
	 * Metodo para obter informacoes de um funcionario por Id
	 * 
	 * @param id
	 * @return Todas informacoes do funcionario
	 * @throws BusinessException
	 */
	public FuncionarioBean obterPorId(int id) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f WHERE f.id = :id AND f.ativo = 'S'", FuncionarioBean.class);
		query.setParameter("id", id);
		List<FuncionarioBean> listaFuncionario = query.getResultList();
		if (listaFuncionario.isEmpty()) {
			return null;
		} else {
			return listaFuncionario.get(0);
		}
	}

	/**
	 * Metodo para obter informacoes de um funcionario por nome
	 * 
	 * @param nome
	 * @return Todas informacoes do funcionario
	 * @throws BusinessException
	 */
	public List<FuncionarioBean> obterPorNome(String nome) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f WHERE f.nome = :nome and f.ativo = 'S'", FuncionarioBean.class);
		query.setParameter("nome", nome);
		List<FuncionarioBean> listaFuncionario = query.getResultList();
		return listaFuncionario;
	}

	/**
	 * M?todo para obter informa??es espec?ficas funcion?rios por idEquipe
	 * 
	 * @param idEquipe
	 * @return Lista de funcion?rios com informa??es espec?ficas
	 * @throws BusinessException
	 */
	public List<FuncionarioBean> listarPorEquipe(int idEquipe) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean AS f JOIN f.equipes AS e WHERE f.ativo = 'S' and e.id = :id ORDER BY f.nome ASC",
				FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = (List<FuncionarioBean>) query.setParameter("id", idEquipe)
				.getResultList();
		return listaFuncionario;

	}

	public FuncionarioBean obterPorEquipe(int idFuncionario, int idEquipe) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean AS f JOIN f.equipes AS e WHERE f.ativo = 'S' and e.id = :idEquipe and f.id = :idFuncionario ORDER BY f.nome ASC",
				FuncionarioBean.class);
		query.setParameter("idEquipe", idEquipe);
		query.setParameter("idFuncionario", idFuncionario);
		List<FuncionarioBean> listaFuncionario = query.getResultList();
		if (listaFuncionario.isEmpty()) {
			return null;
		} else {
			return listaFuncionario.get(0);
		}
	}

	/**
	 * Lista todos os funcion?rios de uma tecnologia espec?fica
	 * 
	 * @param nomeTecnologias
	 * @return List<FuncionarioBean> throws BusinessException
	 */
	public List<FuncionarioBean> listarPorTecnologias(String nomeTecnologias) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f JOIN f.listaTecnologia AS t WHERE t.nome = :nome and f.ativo = 'S' AND t.ativo = 'S' GROUP BY f.cpf, f.rg,f.dataNascimento, f.email, f.cargoBean, "
						+ "f.id, f.nome, f.nomeUser, f.telefone, f.ativo HAVING COUNT(f.id) > 0 ",
				FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = (List<FuncionarioBean>) query.setParameter("nome", nomeTecnologias)
				.getResultList();
		return listaFuncionario;

	}

	public List<FuncionarioBean> obterPorCpf(String cpf) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f WHERE f.cpf = :cpf and f.ativo = 'S'", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = (List<FuncionarioBean>) query.setParameter("cpf", cpf).getResultList();
		return listaFuncionario;
	}

	public List<FuncionarioBean> listarPorNegocio(String nomeNegocio) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f JOIN f.listaNegocio as n WHERE n.areaAtuacao = :areaAtuacao and f.ativo = 'S' GROUP BY f.cpf, f.rg,f.dataNascimento, f.email, f.cargoBean, "
						+ "f.id, f.nome, f.nomeUser, f.telefone, f.ativo HAVING COUNT(f.id) > 0 ",
				FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = (List<FuncionarioBean>) query.setParameter("areaAtuacao", nomeNegocio)
				.getResultList();
		return listaFuncionario;
	}

	public List<FuncionarioBean> obterPorEmail(String email) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f WHERE f.email = :email and f.ativo = 'S'", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = query.setParameter("email", email).getResultList();
		return listaFuncionario;

	}

	public List<FuncionarioBean> obterPorUser(String nomeUser) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f WHERE f.nomeUser = :nomeUser and f.ativo = 'S'",
				FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionarios = query.setParameter("nomeUser", nomeUser).getResultList();
		return listaFuncionarios;
	}

	public List<FuncionarioBean> obterPorRg(String rg) throws BusinessException {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean AS f WHERE f.rg = :rg AND f.ativo = 'S'", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionarios = query.setParameter("rg", rg).getResultList();
		return listaFuncionarios;
	}

}
