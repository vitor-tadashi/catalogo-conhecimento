package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.EquipeFuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

@Component
public class EquipeBusiness {

	private EquipeDAO equipeDAO;

	public EquipeBusiness() {
		equipeDAO = new EquipeDAO();
	}

	// INSERIR NA BASE
	public void inserir(EquipeBean equipeBean) throws BusinessException {
		try {

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

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}

	}

	// INSERIR O FUNCIONÁRIO NA BASE

	public void inserirPorEquipe(int equipe, int funcionario) throws BusinessException {
		try {
			EquipeFuncionarioBean equipeFuncionario = equipeDAO.listarPorEquipe(equipe, funcionario);

			if (equipeFuncionario != null) {
				throw new NomeRepetidoException("Este nome já consta nessa Equipe");
			} else {
				equipeDAO.inserirPorEquipe(equipe, funcionario);
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// DELETAR NA BASE
	public void deletar(int id) throws BusinessException {
		try{
		if (equipeDAO.verificarPorFuncionarios(id)) {
			equipeDAO.deletar(id);
		} else {
			throw new RegistroVinculadoException(
					"Essa Equipe não pode ser removida, pois possui vínculos com Funcionários");
		}
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}
	}

	// ATUALIZAR NA BASE
	public void atualizar(EquipeBean equipe) throws BusinessException {
		try {

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
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTAR NA BASE

	public List<EquipeBean> listar() throws BusinessException {

		try {
			List<EquipeBean> listaEquipe = equipeDAO.listar();

			if (listaEquipe.isEmpty()) {
				throw new ConsultaNulaException("Não há equipes cadastradas");
			} else {
				return listaEquipe;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTAR POR ID NA BASE

	public EquipeBean obterPorId(int id) throws BusinessException {
		try {
			return equipeDAO.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTAR POR NOME NA BASE

	public EquipeBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {

		return equipeDAO.obterPorNome(nome);
	}

	// DELETAR POR EQUIPE NA BASE

	public void deletarPorEquipe(int idEquipe, int idFuncionario) throws BusinessException {
		try{
		equipeDAO.deletarPorEquipe(idEquipe, idFuncionario);
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<EquipeBean> obterPorFuncionario(int idFuncionario) throws ClassNotFoundException, SQLException {

		return equipeDAO.obterPorFuncionario(idFuncionario);

	}

	public List<EquipeBean> obterPorProjeto(int idProjeto) throws BusinessException {

		try {
			return equipeDAO.obterPorProjeto(idProjeto);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9'\\s]{1,50}"));
	}

}
