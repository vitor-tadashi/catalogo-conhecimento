package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.CaracteresEspeciaisException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class EquipeBusiness {

	@Autowired
	private EquipeDAO equipeDAO;

	@Autowired
	private ProjetoBusiness projetoBusiness;

	// INSERIR NA BASE
	@Transactional
	public void adicionar(EquipeBean equipeBean) throws BusinessException {
		try {
			if (equipeDAO.obterPorNome(equipeBean.getNome().trim()) != null) {
				throw new NomeRepetidoException("Este nome de equipe já exite na base de dados");
			} else if (!validarNome(equipeBean.getNome())) {
				throw new CaracteresEspeciaisException("Por favor, digite um nome de equipe válido");
			} else if (equipeBean.getObservacao().length() > 500) {
				throw new TamanhoCampoException("Número limite de caracteres excedido (max 500)");
			} else {
				equipeDAO.adicionar(equipeBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	// DELETAR NA BASE
	@Transactional
	public void remover(EquipeBean equipe) throws BusinessException {
		try {
			equipeDAO.remover(equipe);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// ATUALIZAR NA BASE
	@Transactional
	public void alterar(EquipeBean equipeBean) throws BusinessException {
		try {
			if (equipeDAO.obterPorNome(equipeBean.getNome().trim()) != null) {
				throw new NomeRepetidoException("Este nome de equipe já exite na base de dados");
			} else if (!validarNome(equipeBean.getNome())) {
				throw new CaracteresEspeciaisException("Por favor, digite um nome de equipe válido");
			} else if (equipeBean.getObservacao().length() > 500) {
				throw new TamanhoCampoException("Número limite de caracteres excedido (max 500)");
			} else {
				equipeDAO.alterar(equipeBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTAR NA BASE
	@Transactional
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
	@Transactional
	public EquipeBean obterPorId(int id) throws BusinessException {
		try {
			return equipeDAO.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTAR POR NOME NA BASE
	@Transactional
	public EquipeBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		return equipeDAO.obterPorNome(nome);
	}

	// DELETAR POR EQUIPE NA BASE
	@Transactional
	public void removerPorEquipe(int idEquipe, int idFuncionario) throws BusinessException {
		try {
			equipeDAO.removerPorEquipe(idEquipe, idFuncionario);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<EquipeBean> obterPorFuncionario(int idFuncionario) throws BusinessException {
		try {
			return equipeDAO.obterPorFuncionario(idFuncionario);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<EquipeBean> obterPorProjeto(int idProjeto) throws BusinessException {
		try {
			return equipeDAO.obterPorProjeto(projetoBusiness.obterPorId(idProjeto));
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{1,50}"));
	}

}
