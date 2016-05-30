package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.FuncionarioDAO;

public class FuncionarioBusiness {

	private FuncionarioDAO funcionarioDao;
	
	public FuncionarioBusiness() {
		
		funcionarioDao = new FuncionarioDAO();
	}
	
	/**
	 * Adiciona um novo funionário na base
	 * @param funcionarioBean
	 * @return id, criado no bd, do novo funcionário adicionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int adicionar(FuncionarioBean funcionarioBean) throws ClassNotFoundException, SQLException {
		
			int id = funcionarioDao.adicionar(funcionarioBean);
			return id;
	}

	/**
	 * Lista todos os funcionários ativos
	 * @return Lista de funcionários
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FuncionarioBean> listar() throws ClassNotFoundException, SQLException {
		
			return funcionarioDao.listar();
	}

	/**
	 * Obtem todas informações de um funcinário por id 
	 * @param idFuncionario
	 * @return informações de um funcinário
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioBean obterPorId(int idFuncionario)throws ClassNotFoundException, SQLException {
		
			return funcionarioDao.obterPorId(idFuncionario);
	}

	/**
	 * Atualiza informações de um funcionário
	 * @param funcionario
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean atualizar(FuncionarioBean funcionario)throws ClassNotFoundException, SQLException {

			FuncionarioBean funcionarioAux = funcionarioDao.obterPorId(funcionario.getId());

			if (funcionarioAux != null) {
				return false;
			} else {
				funcionarioDao.alterar(funcionario);
				return true;
			}
	}

	/**
	 * Remove logicamente um funcionário
	 * @param id
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean deletar(int id)throws ClassNotFoundException, SQLException {

			FuncionarioBean funcionarioAux = funcionarioDao.obterPorId(id);
			if (funcionarioAux != null) {
				return false;
			} else {
				funcionarioDao.remover(id);
				return true;
			}
	}
	
	/**
	 * Obtem todas informações de um funcionário pelo nome
	 * @param nome
	 * @return todas informações de um funcionário
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioBean obterPorNome(String nome)throws ClassNotFoundException, SQLException {

		return funcionarioDao.obterPorNome(nome);
	}
	
	/**
	 * Obtem informações específicas de um funcionário pelo idEquipe
	 * @param id
	 * @return informações específicas de um funcionário
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<FuncionarioBean> obterPorEquipe(int id) throws ClassNotFoundException, SQLException{
		
		return funcionarioDao.obterPorEquipe(id);
	}
	
}
