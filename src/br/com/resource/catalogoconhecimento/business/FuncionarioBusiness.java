package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.FuncionarioDAO;

public class FuncionarioBusiness {

	// CRIA
	public int inserir(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException {
		
			FuncionarioDAO funcionarioDao = new FuncionarioDAO();

			return funcionarioDao.inserir(funcionario);
	}

	// LISTA
	public List<FuncionarioBean> listar() throws ClassNotFoundException, SQLException {
		
			FuncionarioDAO funcionario = new FuncionarioDAO();
			return funcionario.listar();
		
	}

	// LISTA POR ID
	public FuncionarioBean listarPorId(int idFuncionario)throws ClassNotFoundException, SQLException {
		
			FuncionarioDAO funcionario = new FuncionarioDAO();
			return funcionario.listarPorId(idFuncionario);
		
	}

	// ATUALIZA
	public boolean atualizar(FuncionarioBean funcionario)throws ClassNotFoundException, SQLException {
		

			FuncionarioDAO funcionariodao;
			funcionariodao = new FuncionarioDAO();

			FuncionarioBean funcionarioAux = funcionariodao.listarPorId(funcionario.getId());

			if (funcionarioAux == null) {
				return true;
			} else {
				funcionariodao.atualizar(funcionario);
				return false;
			}

		

	}

	// DELETA
	public boolean deletar(int id)throws ClassNotFoundException, SQLException {

		
			FuncionarioDAO funcionariodao = new FuncionarioDAO();

			FuncionarioBean funcionarioAux = funcionariodao.listarPorId(id);
			if (funcionarioAux == null) {
				return true;
			} else {
				funcionariodao.deletar(id);
				return false;
			}

		
	}

}
