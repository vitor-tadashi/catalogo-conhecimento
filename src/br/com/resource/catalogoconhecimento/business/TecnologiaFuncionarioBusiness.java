package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.TecnologiaFuncionarioDAO;
@Component
public class TecnologiaFuncionarioBusiness {

	TecnologiaFuncionarioDAO tecnologiaFuncionarioDAO;

	public TecnologiaFuncionarioBusiness() throws ClassNotFoundException, SQLException {
		this.tecnologiaFuncionarioDAO = new TecnologiaFuncionarioDAO();
	}

	public int inserir(FuncionarioBean funcionario, List<TecnologiaBean> tecnologias) throws SQLException {
		int linhasAfetadas = 0;
		linhasAfetadas = tecnologiaFuncionarioDAO.adicionar(funcionario, tecnologias);
		return linhasAfetadas;
	}

	public List<TecnologiaBean> listar(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException {
		return tecnologiaFuncionarioDAO.listar(funcionario);
	}

	public List<TecnologiaBean> joinTecnologiaFuncionario(int idFuncionario)
			throws ClassNotFoundException, SQLException {
		TecnologiaFuncionarioDAO tecnologia = new TecnologiaFuncionarioDAO();
		return tecnologia.joinTecnologiaFuncionario(idFuncionario);
	}

	public void atualizar(FuncionarioBean funcionarioBean, List<TecnologiaBean> listaTecnologia) throws SQLException {
		tecnologiaFuncionarioDAO.atualizar(funcionarioBean, listaTecnologia);

	}

}
