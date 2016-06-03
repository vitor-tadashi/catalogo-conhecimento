package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.EquipeFuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class EquipeBusiness {

	// INSERIR NA BASE

	public void inserir(EquipeBean equipeBean) throws ClassNotFoundException, SQLException, Exception {

		EquipeDAO equipeDAO = new EquipeDAO();
		EquipeBean equipeigual = equipeDAO.listarPorNome(equipeBean.getNome());
		
		

		if (!validarNome(equipeBean.getNome())) {
			throw new TamanhoCampoException("Por Favor, digite um nome válido");
		} else if (equipeigual != null && equipeigual.getId() != equipeBean.getId()) {
			throw new NomeRepetidoException("Esta equipe já consta na base de dados");
		} else if (equipeBean.getObservacao().length() > 500) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.500)");
		} else {
			equipeDAO.inserir(equipeBean);
		}

	}

	//INSERIR O FUNCIONÁRIO NA BASE
	
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

		if (!validarNome(equipe.getNome()) || equipe.getNome().equals("")) {
			throw new TamanhoCampoException("Por Favor, digite um nome válido");
		} else if (equipeigual != null && equipeigual.getId() != equipe.getId()) {
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

	public EquipeBean obterPorId(int id) throws ClassNotFoundException, SQLException {

		EquipeDAO equipe = new EquipeDAO();
		return equipe.obterPorId(id);

	}
	
	// LISTAR POR NOME NA BASE
	
	public EquipeBean obterPorNome(String nome) throws ClassNotFoundException, SQLException{
		EquipeDAO equipe = new EquipeDAO();
		return equipe.obterPorNome(nome);
	}
 
	//DELETAR POR EQUIPE NA BASE
	
	public void deletarPorEquipe(int idEquipe, int idFuncionario) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		EquipeDAO equipeDAO = new EquipeDAO();
		equipeDAO.deletarPorEquipe(idEquipe, idFuncionario);
	}

	public List<EquipeBean> obterPorFuncionario(int idFuncionario) throws ClassNotFoundException, SQLException {
		EquipeDAO equipeDAO = new EquipeDAO();
		return equipeDAO.obterPorFuncionario(idFuncionario);
		
	}
	
	public boolean validarNome(String nome){
		return (nome.matches("[A-Za-zÀ-ú0-9'\\s]{1,50}"));
	}

}
