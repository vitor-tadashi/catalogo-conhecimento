package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.EquipeFuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class EquipeBusiness {

	// INSERIR NA BASE

	public void inserir(EquipeBean equipe) throws ClassNotFoundException, SQLException, Exception {

		EquipeDAO equipeDAO = new EquipeDAO();
		EquipeBean equipeigual = equipeDAO.listarPorNome(equipe.getNome());

		if (equipe.getNome().length() > 100) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.100)");
		} else if (equipeigual != null) {
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		} else if (equipe.getObservacao().length() > 500) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.500)");
		} else {
			equipeDAO.inserir(equipe);
		}

	}

	public void inserirPorEquipe(int equipe, int funcionario)
			throws ClassNotFoundException, SQLException, NomeRepetidoException {
		EquipeDAO equipeDAO = new EquipeDAO();
		EquipeFuncionarioBean equipeFuncionario = equipeDAO.listarPorEquipe(equipe, funcionario);

		if (equipeFuncionario != null) {
			throw new NomeRepetidoException("Este nome já consta nessa Equipe");
		} else {
			equipeDAO.inserirPorEquipe(equipe, funcionario);
		}
	}

	// DELETAR NA BASE

	public void deletar(EquipeBean id) throws ClassNotFoundException, SQLException {

		EquipeDAO equipeDAO = new EquipeDAO();
		equipeDAO.deletar(id);

	}

	// ATUALIZAR NA BASE

	public void atualizar(EquipeBean equipe)
			throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {

		EquipeDAO equipeDAO = new EquipeDAO();
		EquipeBean equipeigual = equipeDAO.listarPorNome(equipe.getNome());

		if (equipe.getNome().length() > 100) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.100)");
		} else if (equipeigual != null) {
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		} else if (equipe.getObservacao().length() > 500) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.500)");
		} else {
			equipeDAO.atualizar(equipe);
		}

	}

	// LISTAR NA BASE

	public List<EquipeBean> listar() throws ClassNotFoundException, SQLException {

		EquipeDAO equipe = new EquipeDAO();
		return equipe.listar();

	}

	// LISTAR POR ID NA BASE

	public EquipeBean listarPorId(int id) throws ClassNotFoundException, SQLException {

		EquipeDAO equipe = new EquipeDAO();
		return equipe.listarPorId(id);

	}

	public void deletarPorEquipe(int idEquipe, int idFuncionario) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		EquipeDAO equipeDAO = new EquipeDAO();
		equipeDAO.deletarPorEquipe(idEquipe, idFuncionario);
	}
}
