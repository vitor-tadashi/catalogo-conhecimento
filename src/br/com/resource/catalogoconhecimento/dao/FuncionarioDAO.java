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
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class FuncionarioDAO extends GenericDAOImpl<FuncionarioBean, Integer> {

	/**
	 * M�todo para listar todos os funcion�rios ativos
	 * 
	 * @return Lista de funcion�rios
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioBean> listar() {
		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean AS f WHERE f.ativo = 'S' ORDER BY f.nome ASC", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = query.getResultList();
		return listaFuncionario;
	}

	/**
	 * M�todo para obter informa��es de um funcion�rio por Id
	 * 
	 * @param id
	 * @return Todas informa��es do funcion�rio
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public FuncionarioBean obterPorId(int id) throws SQLException, ClassNotFoundException, BusinessException {

		try {
			TypedQuery<FuncionarioBean> query = entityManager.createQuery(
					"SELECT f FROM FuncionarioBean as f WHERE f.id = :id AND f.ativo = 'S'", FuncionarioBean.class);
			FuncionarioBean funcionarioBean = query.setParameter("id", id).getSingleResult();
			return funcionarioBean;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * M�todo para obter informa��es de um funcion�rio por nome
	 * 
	 * @param nome
	 * @return Todas informa��es do funcion�rio
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<FuncionarioBean> obterPorNome(String nome)
			throws SQLException, ClassNotFoundException, BusinessException {

		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f WHERE f.nome = :nome and f.ativo = 'S'", FuncionarioBean.class);
		query.setParameter("nome", nome);
		List<FuncionarioBean> funcionario = query.getResultList();
		return funcionario;
	}

	/**
	 * M�todo para obter informa��es espec�ficas funcion�rios por idEquipe
	 * 
	 * @param idEquipe
	 * @return Lista de funcion�rios com informa��es espec�ficas
	 * @throws ClassNotFoundException
	 * @throws SQLExceptionk
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

		try {
			TypedQuery<FuncionarioBean> query = entityManager.createQuery(
					"SELECT f FROM FuncionarioBean AS f JOIN f.equipes AS e WHERE f.ativo = 'S' and e.id = :idEquipe and f.id = :idFuncionario ORDER BY f.nome ASC",
					FuncionarioBean.class);
			query.setParameter("idEquipe", idEquipe);
			query.setParameter("idFuncionario", idFuncionario);

			FuncionarioBean funcionario = (FuncionarioBean) query.getSingleResult();
			return funcionario;

		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Lista todos os funcion�rios de uma tecnologia espec�fica
	 * 
	 * @param nomeTecnologias
	 * @return List<FuncionarioBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FuncionarioBean> listarPorTecnologias(String nomeTecnologias) throws BusinessException {

		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f JOIN f.listaTecnologia AS t WHERE t.nome in ("+ nomeTecnologias +") and f.ativo = 'S' AND t.ativo = 'S' GROUP BY f.cpf, f.rg,f.dataNascimento, f.email, f.cargoBean, "
						+ "f.id, f.nome, f.nomeUser, f.telefone, f.ativo HAVING COUNT(f.id) > 0 ",
				FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = query.getResultList();
		return listaFuncionario;

	}
	
	

	public List<FuncionarioBean> obterPorCpf(String cpf)
			throws SQLException, ClassNotFoundException, BusinessException {

		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f WHERE f.cpf = :cpf and f.ativo = 'S'", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = (List<FuncionarioBean>) query.setParameter("cpf", cpf).getResultList();
		return listaFuncionario;

	}

	public List<FuncionarioBean> listarPorNegocio(String nomeNegocio) throws BusinessException {

		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f JOIN f.listaNegocio as n WHERE n.areaAtuacao in ("+nomeNegocio+") and f.ativo = 'S' GROUP BY f.cpf, f.rg,f.dataNascimento, f.email, f.cargoBean, "
						+ "f.id, f.nome, f.nomeUser, f.telefone, f.ativo HAVING COUNT(f.id) > 0 ",
				FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = query.getResultList();
		return listaFuncionario;

	}

	

	public List<FuncionarioBean> obterPorEmail(String email)
			throws SQLException, ClassNotFoundException, BusinessException {

		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f WHERE f.email = :email and f.ativo = 'S'", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionario = query.setParameter("", email).getResultList();
		return listaFuncionario;

	}

	public List<FuncionarioBean> obterPorUser(String nomeUser)
			throws SQLException, ClassNotFoundException, BusinessException {

		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean as f WHERE f.nomeUser and f.ativo = 'S'", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionarios = query.setParameter("nomeUser", nomeUser).getResultList();
		return listaFuncionarios;

	}

	public List<FuncionarioBean> obterPorRg(String rg) throws SQLException, ClassNotFoundException, BusinessException {

		TypedQuery<FuncionarioBean> query = entityManager.createQuery(
				"SELECT f FROM FuncionarioBean AS f WHERE f.rg = :rg AND f.ativo = 'S'", FuncionarioBean.class);
		List<FuncionarioBean> listaFuncionarios = query.setParameter("rg", rg).getResultList();
		return listaFuncionarios;
	}

	public List<FuncionarioBean> listarPorNome(String nome)
			throws SQLException, ClassNotFoundException, BusinessException {
		Connection conexao = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Funcionario WHERE nomeFuncionario LIKE '%" + nome + "%' and ativo = 's'";
		PreparedStatement ps = conexao.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<FuncionarioBean> listaFuncionario = new ArrayList<>();
		FuncionarioBean funcionarioBean = null;
		CargoBusiness cargoBusiness = new CargoBusiness();
		while (rs.next()) {
			CargoBean cargoBean = cargoBusiness.obterPorId(rs.getInt("idCargo"));

			funcionarioBean = new FuncionarioBean();
			funcionarioBean.setId(rs.getInt("idFuncionario"));
			funcionarioBean.setCargo(cargoBean);
			funcionarioBean.setNome(rs.getString("nomeFuncionario"));
			funcionarioBean.setTelefone(rs.getString("telefone"));
			funcionarioBean.setNomeUser(rs.getString("nomeUser"));
			funcionarioBean.setEmail(rs.getString("email"));
			funcionarioBean.setCpf(rs.getString("CPF"));
			funcionarioBean.setRg(rs.getString("RG"));
			funcionarioBean.setDataNascimento(rs.getDate("dataNascimento"));

			listaFuncionario.add(funcionarioBean);
		}

		conexao.close();
		return listaFuncionario;
	}
}
