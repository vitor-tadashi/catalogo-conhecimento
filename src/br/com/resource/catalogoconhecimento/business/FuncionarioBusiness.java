package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.FuncionarioDAO;

public class FuncionarioBusiness {

	// CRIA
	public void inserir(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException {
		try {
			FuncionarioDAO funcionariodao = new FuncionarioDAO();

			funcionariodao.inserir(funcionario);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// LISTA
	public List<FuncionarioBean> listar() throws ClassNotFoundException, SQLException {
		try {
			FuncionarioDAO funcionario = new FuncionarioDAO();
			return funcionario.listar();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// LISTA POR ID
	public FuncionarioBean listarPorId(int idFuncionario)throws ClassNotFoundException, SQLException {
		try {
			FuncionarioDAO funcionario = new FuncionarioDAO();
			return funcionario.listarPorId(idFuncionario);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// ATUALIZA
	public boolean atualizar(FuncionarioBean funcionario)throws ClassNotFoundException, SQLException {
		try {

			FuncionarioDAO funcionariodao;
			funcionariodao = new FuncionarioDAO();

			FuncionarioBean funcionarioAux = funcionariodao.listarPorId(funcionario.getIdFuncionario());

			if (funcionarioAux == null) {
				return true;
			} else {
				funcionariodao.atualizar(funcionario);
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return false;

	}

	// DELETA
	public boolean deletar(int id)throws ClassNotFoundException, SQLException {

		try {
			FuncionarioDAO funcionariodao = new FuncionarioDAO();

			FuncionarioBean funcionarioAux = funcionariodao.listarPorId(id);
			if (funcionarioAux == null) {
				return true;
			} else {
				funcionariodao.deletar(id);
				return false;
			}

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return false;
	}

}
