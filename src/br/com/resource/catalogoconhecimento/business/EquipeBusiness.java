package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.EquipeFuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class EquipeBusiness {
	
	private EquipeDAO equipeDAO;
	
	public EquipeBusiness(){
		equipeDAO = new EquipeDAO();
	}

	// INSERIR NA BASE
	public void inserir(EquipeBean equipeBean) throws ClassNotFoundException, SQLException, Exception {

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
		
		EquipeFuncionarioBean equipeFuncionario = equipeDAO.listarPorEquipe(equipe, funcionario);

		if (equipeFuncionario != null) {
			throw new NomeRepetidoException("Este nome já consta nessa Equipe");
		} else {
			equipeDAO.inserirPorEquipe(equipe, funcionario);
		}
	}

	// DELETAR NA BASE

	public void deletar(int id) throws ClassNotFoundException, SQLException, BusinessException {

		EquipeBean equipeExistente = new EquipeBean();
		equipeExistente = equipeDAO.obterPorId(id);
		
		if(equipeExistente != null){
			equipeDAO.deletar(equipeExistente);			
		}else{
			throw new BusinessException("Essa equipe não pode ser deletada");
		}
		

	}

	// ATUALIZAR NA BASE

	public void atualizar(EquipeBean equipe)
			throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {

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

	public List<EquipeBean> listar() throws ClassNotFoundException, SQLException, ConsultaNulaException {
		
		List<EquipeBean> listaEquipe = equipeDAO.listar();
		
		if (listaEquipe.isEmpty()) {
			throw new ConsultaNulaException("Não há equipes cadastradas");
		} else {
			return listaEquipe;
		}
	}

	// LISTAR POR ID NA BASE

	public EquipeBean obterPorId(int id) throws ClassNotFoundException, SQLException {

		return equipeDAO.obterPorId(id);

	}
	
	// LISTAR POR NOME NA BASE
	
	public EquipeBean obterPorNome(String nome) throws ClassNotFoundException, SQLException{
		
		return equipeDAO.obterPorNome(nome);
	}
 
	//DELETAR POR EQUIPE NA BASE
	
	public void deletarPorEquipe(int idEquipe, int idFuncionario) throws ClassNotFoundException, SQLException {

		equipeDAO.deletarPorEquipe(idEquipe, idFuncionario);
	}

	public List<EquipeBean> obterPorFuncionario(int idFuncionario) throws ClassNotFoundException, SQLException {
		
		return equipeDAO.obterPorFuncionario(idFuncionario);
		
	}
	
	public List<EquipeBean> obterPorProjeto(int idProjeto) throws ClassNotFoundException, SQLException {
		
		return equipeDAO.obterPorProjeto(idProjeto);
		
	}
	
	public boolean validarNome(String nome){
		return (nome.matches("[A-Za-zÀ-ú0-9'\\s]{1,50}"));
	}

}
